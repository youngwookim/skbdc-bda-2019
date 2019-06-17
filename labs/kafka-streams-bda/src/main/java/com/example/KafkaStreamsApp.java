package com.example;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Predicate;
import org.apache.kafka.streams.kstream.Produced;
import com.example.avro.IexTrading;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

public class KafkaStreamsApp {

  public static void main(String[] args) {
    final String bootstrapServers = args.length > 0 ? args[0] : "localhost:9092";
    final String schemaRegistryUrl = args.length > 1 ? args[1] : "http://localhost:18081";
    final KafkaStreams streams =
        buildTradingQuoteFeed(bootstrapServers, schemaRegistryUrl, "/tmp/kafka-streams");

    streams.cleanUp();
    streams.start();

    // Add shutdown hook to respond to SIGTERM and gracefully close Kafka Streams
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      public void run() {
        streams.close();
      }
    }));

  }

  static KafkaStreams buildTradingQuoteFeed(final String bootstrapServers,
      final String schemaRegistryUrl, final String stateDir) {
    final Properties streamsConfiguration = new Properties();
    streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-avro-example");
    streamsConfiguration.put(StreamsConfig.CLIENT_ID_CONFIG, "kafka-streams-avro-example-client");
    streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    streamsConfiguration.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        schemaRegistryUrl);
    streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
        Serdes.String().getClass().getName());
    streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
        SpecificAvroSerde.class);
    streamsConfiguration.put(StreamsConfig.STATE_DIR_CONFIG, stateDir);

    final StreamsBuilder builder = new StreamsBuilder();

    // read the source stream
    final KStream<String, IexTrading> feeds = builder.stream("iextrading");

    final KStream<String, IexTrading> filtered = feeds.filter(new Predicate<String, IexTrading>() {
      public boolean test(final String dummy, final IexTrading value) {
        return value.getSymbol().equals("FB");
      }
    });

    // write to the result topic
    filtered.to("iextrading_filtered");

    return new KafkaStreams(builder.build(), streamsConfiguration);
  }
}
