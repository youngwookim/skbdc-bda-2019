package com.example;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.CanCommitOffsets;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.HasOffsetRanges;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.apache.spark.streaming.kafka010.OffsetRange;

import com.example.avro.IexTrading;

import scala.Tuple2;

/*
 * @see https://spark.apache.org/docs/latest/streaming-kafka-0-10-integration.html
 */
public class SparkStreamingKafkaApp {

  public SparkStreamingKafkaApp() {
    // TODO Auto-generated constructor stub
  }

  public static void main(String[] args) throws InterruptedException {

    // FIXME
    String brokers = "kafka1:19092";
    Collection<String> topics = Arrays.asList("iextrading");

    // Create context with a 2 seconds batch interval
    SparkConf sparkConf = new SparkConf().setAppName("SparkStreamingKafkaApp");
    JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(2));

    Set<String> topicsSet = new HashSet<>(topics);
    // See, http://kafka.apache.org/documentation.html#newconsumerconfigs
    Map<String, Object> kafkaParams = new HashMap<>();
    kafkaParams.put("bootstrap.servers", brokers);
    kafkaParams.put("schema.registry.url", "http://kafka-schema-registry:8081");
    kafkaParams.put("group.id", "spark-app-01");
    kafkaParams.put("specific.avro.reader", "true");
    kafkaParams.put("auto.offset.reset", "earliest");
    kafkaParams.put("enable.auto.commit", false);
    kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    kafkaParams.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");

    // Create direct kafka stream with brokers and topics
    JavaInputDStream<ConsumerRecord<String, Object>> stream =
        KafkaUtils.createDirectStream(jssc, LocationStrategies.PreferConsistent(),
            ConsumerStrategies.Subscribe(topicsSet, kafkaParams));

    stream.foreachRDD(rdd -> {
      // https://spark.apache.org/docs/latest/streaming-kafka-0-10-integration.html
      OffsetRange[] offsetRanges = ((HasOffsetRanges) rdd.rdd()).offsetRanges();

      rdd.foreachPartition(iterator -> {
          while (iterator.hasNext()) {
            ConsumerRecord<String, Object> next = iterator.next();
            IexTrading iexTrding = (IexTrading) next.value();
            System.out.println(next.key() + " --> " + iexTrding.toString());
          }
        }
      );
      
//      rdd.foreach(record -> {
//        // Do something
//        IexTrading avroMsg = (IexTrading) record.value();
//        System.out.printf("Avro record = %s\n", avroMsg.toString());
//      });

      // some time later, after outputs have completed
      ((CanCommitOffsets) stream.inputDStream()).commitAsync(offsetRanges);
    });

    jssc.start();
    jssc.awaitTermination();
  }
}
