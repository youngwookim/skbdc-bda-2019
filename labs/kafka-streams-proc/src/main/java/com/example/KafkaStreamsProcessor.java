package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import com.example.avro.IexTrading;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

public class KafkaStreamsProcessor {

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
    // For Kafka Streams application.id will be group.id
    streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-proc");
    streamsConfiguration.put(StreamsConfig.CLIENT_ID_CONFIG, "kafka-streams-proc-client");
    streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    streamsConfiguration.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
        schemaRegistryUrl);
    streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
        Serdes.String().getClass().getName());
    streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
        SpecificAvroSerde.class);
    streamsConfiguration.put(StreamsConfig.STATE_DIR_CONFIG, stateDir);

    final StreamsBuilder builder = new StreamsBuilder();

    // read the source stream:
    final KStream<String, IexTrading> feeds = builder.stream("iextrading_filtered");

    // FIXME: Processor API
    feeds.foreach((key, value) -> saveFilteredRecord(value));

    return new KafkaStreams(builder.build(), streamsConfiguration);
  }

  private static void saveFilteredRecord(IexTrading value) {
    String SQL =
        "INSERT INTO iextrading_filtered(symbol, company_name, low, high, latest_update, latest_price) VALUES (?,?,?,?,?,?)";

    try (
        Connection conn =
            DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/stock", "root", "mypasswd");
        PreparedStatement ps = conn.prepareStatement(SQL)) {
      // Bind values
      ps.setString(1, value.getSymbol().toString());
      ps.setString(2, value.getCompanyName().toString());
      ps.setDouble(3, value.getLow());
      ps.setDouble(4, value.getHigh());
      ps.setLong(5, value.getLatestUpdate());
      ps.setDouble(6, value.getLatestPrice());

      int row = ps.executeUpdate();
      // rows affected
      System.out.println("Row(s) affected : " + row); // 1
    } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
