# Kafka Message Generator

Build from source:
```
mvn clean package
```

Create a Kafka topic:
```
kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic iextrading
```

Running Kafka producer:
```
java -jar target/kafka-message-gen-1.0.0.jar
```
