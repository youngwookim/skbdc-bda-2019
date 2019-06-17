package com.example;

import java.io.PrintStream;
import java.time.Instant;
import java.util.Properties;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.Encoder;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.core.fs.Path;
import org.apache.flink.formats.avro.registry.confluent.ConfluentRegistryAvroDeserializationSchema;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
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
    final ParameterTool parameterTool = ParameterTool.fromArgs(args);

    Properties config = new Properties();
    config.setProperty("bootstrap.servers", parameterTool.getRequired("bootstrap.servers"));
    config.setProperty("group.id", parameterTool.getRequired("group.id"));
    String schemaRegistryUrl = parameterTool.getRequired("schema-registry-url");
    String outputPath = parameterTool.getRequired("output-path");

    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    // Use S3 as the backend store to store checkpoints for recovery
    env.setStateBackend(new FsStateBackend("s3://flink/checkpoints"));
    // Perform checkpoint every 10 secs
    env.enableCheckpointing(10000L);
    // Inject parameters to be globally available
    env.getConfig().setGlobalJobParameters(parameterTool);

    DataStreamSource<IexTrading> input = env.addSource(new FlinkKafkaConsumer<>(
        parameterTool.getRequired("input-topic"),
        ConfluentRegistryAvroDeserializationSchema.forSpecific(IexTrading.class, schemaRegistryUrl),
        config).setStartFromEarliest());

    // Flink stream operators
    DataStream<String> myFavorites = input.filter(new FilterFunction<IexTrading>() {

      private static final long serialVersionUID = 8572223950396068676L;

      @Override
      public boolean filter(IexTrading value) throws Exception {
        // Netflix or Facebook?
        if (value.getSymbol().toString().equals("NFLX") || value.getSymbol().toString().equals("FB")) {
          return true;
        }
        return false;
      }
    }).map(new MapFunction<IexTrading, String>() {

      private static final long serialVersionUID = -2765462581425595714L;

      @Override
      public String map(IexTrading value) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(Instant.now().getEpochSecond());
        sb.append("\001");
        sb.append(value.getSymbol());
        sb.append("\001");
        sb.append(value.getHigh());
        sb.append("\001");
        sb.append(value.getLow());
        sb.append("\001");
        sb.append(value.getLatestPrice());
        sb.append("\001");
        sb.append(value.getLatestUpdate());
        
        return sb.toString();
      }
    });

    // For debug
    myFavorites.print();

    // Flink Streaming Sink
    final SinkFunction<String> sink = StreamingFileSink
        .forRowFormat(new Path(outputPath), (Encoder<String>) (element, stream) -> {
          PrintStream out = new PrintStream(stream);
          out.println(element);
        }).withBucketAssigner(new DateTimeBucketAssigner("yyyy-MM-dd")).build();

    myFavorites.addSink(sink);

    env.execute("Flink Kakfa Streaming App");
  }
}
