# Spark, Kafka and Schema Registry example

## Building source
```
mvn clean package
```

## Running spark application
```
cd $SPARK_HOME
bin/spark-submit --master local[4] spark-kafka-streaming-1.0.0-jar-with-dependencies.jar
```

Browse http://localhost:4040
