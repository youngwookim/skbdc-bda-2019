package com.example;

import java.io.PrintStream;
import java.util.Properties;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.Encoder;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.core.fs.Path;
import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroDeserializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink;
import org.apache.flink.streaming.api.functions.sink.filesystem.bucketassigners.DateTimeBucketAssigner;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import com.example.avro.IexTrading;

public class FlinkKakfaStreamingApp {

  public static void main(String[] args) throws Exception {
    // parse input arguments
    final ParameterTool parameterTool = ParameterTool.fromArgs(args);
    if (parameterTool.getNumberOfParameters() < 5) {
      System.out.println(
          "Missing parameters!\n" + "Usage: Kafka --input-topic <topic> --output-topic <topic> "
              + "--bootstrap.servers <kafka brokers> "
              + "--schema-registry-url <confluent schema registry> --group.id <some id>"
              + "--output-path <output path>");
      return;
    }
    Properties config = new Properties();
    config.setProperty("bootstrap.servers", parameterTool.getRequired("bootstrap.servers"));
    config.setProperty("group.id", parameterTool.getRequired("group.id"));
    String schemaRegistryUrl = parameterTool.getRequired("schema-registry-url");
    String outputPath = parameterTool.getRequired("output-path");

    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    env.getConfig().disableSysoutLogging();

    DataStreamSource<IexTrading> input = env.addSource(new FlinkKafkaConsumer<>(
        parameterTool.getRequired("input-topic"),
        ConfluentRegistryAvroDeserializationSchema.forSpecific(IexTrading.class, schemaRegistryUrl),
        config).setStartFromEarliest());

    // Flink stream operators,
    // https://ci.apache.org/projects/flink/flink-docs-release-1.8/dev/stream/operators/
    DataStream<String> myFavorites = input.filter(new FilterFunction<IexTrading>() {

      @Override
      public boolean filter(IexTrading value) throws Exception {
        // Apple, Facebook, Netflix
        if (value.getSymbol().equals("APPL") || value.getSymbol().equals("FB")
            || value.getSymbol().equals("NFLX")) {
          return true;
        }
        return false;
      }
    }).map(new GenRecord());

    // For debug
    myFavorites.print();

    // Flink Streaming Sink
    // https://ci.apache.org/projects/flink/flink-docs-stable/dev/connectors/streamfile_sink.html
    final SinkFunction<String> sink = StreamingFileSink
        .forRowFormat(new Path(outputPath), (Encoder<String>) (element, stream) -> {
          PrintStream out = new PrintStream(stream);
          out.println(element);
        }).withBucketAssigner(new DateTimeBucketAssigner("yyyy-MM-dd")).build();

    myFavorites.addSink(sink);

    env.execute("Flink Kakfa Streaming App");
  }

  public static final class GenRecord implements MapFunction<IexTrading, String> {

    @Override
    public String map(IexTrading value) throws Exception {
      return value.getSymbol() + "," + value.getLatestPrice();
    }

  }
}
