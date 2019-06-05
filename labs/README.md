# SKBDA BDA, Hands-on Labs

## Data Flow

![Data flow](sdp_labs.png)

1. Source
- Kafka Client (Producer)
- Avro message

2. Message Broker (Kafka)

3. Stream Processing
- Apache Flink
- Apache Spark
- Kafka Streams

4. Data Store
- RDBMS
- S3 (minio)
- KV

5. Data processing and analytics
- Python (jupyter notebook)
- SQL (Hive, Presto, etc...)

## Software stack
```
Zookeeper version: 3.4.9
Kafka version: 1.1.1
Kafka Schema Registry 4.1.2
Kafka Schema Registry UI 0.9.4
Kafka Rest Proxy 4.1.2
```

Tools:
- kadmin, https://github.com/BetterCloud/kadmin

## Stream Data Platform

### Setup Kafka cluster via Docker
```
git clone https://github.com/youngwookim/kafka-stack-docker-compose
cd kafka-stack-docker-compose
git tag -l
git checkout tags/v4.1.2

docker-compose -f full-stack.yml up
docker ps

docker-compose -f full-stack.yml down
```

Services:
```
Single Zookeeper: $DOCKER_HOST_IP:2181
Single Kafka: $DOCKER_HOST_IP:9092
Kafka Schema Registry: $DOCKER_HOST_IP:8081
Kafka Schema Registry UI: $DOCKER_HOST_IP:8001
Kafka Rest Proxy: $DOCKER_HOST_IP:8082
Kafka Topics UI: $DOCKER_HOST_IP:8000
Kafka Connect: $DOCKER_HOST_IP:8083
Kafka Connect UI: $DOCKER_HOST_IP:8003
Zoonavigator Web: $DOCKER_HOST_IP:8004
```

Container ID for Kafka broker(kafka1):
```
docker ps --filter name=kafka1 --format={{.ID}}

```

Networking of (Kafka) docker compose:
![Kafka Docker network](https://github.com/wurstmeister/kafka-docker/wiki/kafka-single-broker.png)
https://github.com/wurstmeister/kafka-docker/wiki/Connectivity

### Sanity check
* CLI
```
# Basic Ops
$ export KAFKA_BROKER=$(docker ps --filter name=kafka1 --format={{.ID}})
$ docker exec -t -i "$KAFKA_BROKER" \
kafka-topics --create --topic foo --partitions 1 --replication-factor 1 \
--if-not-exists --zookeeper zoo1:2181

$ docker exec -t -i "$KAFKA_BROKER" \
kafka-topics --create --topic hello --partitions 4 --replication-factor 1 \
--if-not-exists --zookeeper zoo1:2181

$ docker exec -t -i "$KAFKA_BROKER" \
kafka-topics --create --topic world --partitions 8 --replication-factor 1 \
--if-not-exists --zookeeper zoo1:2181

$ docker exec -t -i "$KAFKA_BROKER" \
kafka-topics --describe --topic foo --zookeeper zoo1:2181

$ docker exec -t -i "$KAFKA_BROKER" \
kafka-topics --describe --topic hello --zookeeper zoo1:2181

$ docker exec -t -i "$KAFKA_BROKER" \
kafka-topics --describe --topic world --zookeeper zoo1:2181

$ docker exec -t -i "$KAFKA_BROKER" \
bash -c "seq 100 | kafka-console-producer --request-required-acks 1 \
--broker-list kafka1:9092 --topic foo && echo 'Produced 100 messages.'"

$ docker exec -t -i "$KAFKA_BROKER" \
kafka-console-consumer --bootstrap-server kafka1:9092 --topic foo --from-beginning --max-messages 100

```

or
```
$ docker ps --filter name=kafka1 --format={{.ID}}
c832ec907848

$  docker exec -t -i c832ec907848 bash -l

# ......

```

Kafka topic for test:
```
$ export KAFKA_BROKER=$(docker ps --filter name=kafka1 --format={{.ID}})

# topic1
$ docker exec -t -i "$KAFKA_BROKER" \
kafka-topics --create --topic topic1 --partitions 4 --replication-factor 1 \
--if-not-exists --zookeeper zoo1:2181

# eventcall
$ docker exec -t -i "$KAFKA_BROKER" \
kafka-topics --create --topic topic1 --partitions 4 --replication-factor 1 \
--if-not-exists --zookeeper zoo1:2181

```

* Web (kadmin)
https://github.com/BetterCloud/kadmin

Running kadmin (on localhost):
```
git clone https://github.com/youngwookim/kadmin
cd kadmin
cd dist
cp ../application.properties .
java -jar shared-kafka-admin-micro-*.jar --spring.profiles.active=kadmin,local
```

http://localhost:8080/kadmin/

1. Basic producer

String -> String

2. Avro producer

Schema Registry UI
- http://localhost:8001

EventCall Schema:
```
{
  "type": "record",
  "name": "EventCall",
  "namespace": "com.bettercloud.avro.workflow",
  "fields": [
    {
      "name": "header",
      "type": {
        "type": "record",
        "name": "Header",
        "namespace": "com.bettercloud.avro",
        "fields": [
          {
            "name": "senderId",
            "type": [
              {
                "type": "string",
                "avro.java.string": "String"
              },
              "null"
            ],
            "default": "null"
          },
          {
            "name": "domainId",
            "type": [
              {
                "type": "string",
                "avro.java.string": "String"
              },
              "null"
            ]
          },
          {
            "name": "tenantId",
            "type": {
              "type": "string",
              "avro.java.string": "String"
            }
          },
          {
            "name": "providerId",
            "type": [
              {
                "type": "string",
                "avro.java.string": "String"
              },
              "null"
            ]
          },
          {
            "name": "correlationId",
            "type": [
              {
                "type": "string",
                "avro.java.string": "String"
              },
              "null"
            ]
          },
          {
            "name": "externalCorrelationId",
            "type": [
              {
                "type": "string",
                "avro.java.string": "String"
              },
              "null"
            ]
          },
          {
            "name": "receivedDate",
            "type": [
              "long",
              "null"
            ]
          },
          {
            "name": "eventId",
            "type": [
              {
                "type": "string",
                "avro.java.string": "String"
              },
              "null"
            ]
          },
          {
            "name": "userId",
            "type": [
              {
                "type": "string",
                "avro.java.string": "String"
              },
              "null"
            ]
          },
          {
            "name": "eventMeta",
            "type": [
              "null",
              {
                "type": "array",
                "items": {
                  "type": "record",
                  "name": "MapKeyValueEntry",
                  "namespace": "com.bettercloud.avro.workflow",
                  "fields": [
                    {
                      "name": "key",
                      "type": {
                        "type": "string",
                        "avro.java.string": "String"
                      }
                    },
                    {
                      "name": "value",
                      "type": [
                        "boolean",
                        "int",
                        "long",
                        "float",
                        "double",
                        "bytes",
                        {
                          "type": "string",
                          "avro.java.string": "String"
                        },
                        "null",
                        {
                          "type": "array",
                          "items": [
                            "MapKeyValueEntry",
                            "boolean",
                            "int",
                            "long",
                            "float",
                            "double",
                            "bytes",
                            {
                              "type": "string",
                              "avro.java.string": "String"
                            },
                            "null",
                            {
                              "type": "array",
                              "items": "MapKeyValueEntry"
                            }
                          ]
                        }
                      ]
                    }
                  ]
                }
              }
            ],
            "default": null
          }
        ]
      }
    },
    {
      "name": "eventId",
      "type": {
        "type": "string",
        "avro.java.string": "String"
      }
    },
    {
      "name": "eventParams",
      "type": [
        "null",
        {
          "type": "map",
          "values": [
            "boolean",
            "int",
            "long",
            "float",
            "double",
            "bytes",
            {
              "type": "string",
              "avro.java.string": "String"
            },
            "null",
            {
              "type": "map",
              "values": [
                {
                  "type": "string",
                  "avro.java.string": "String"
                },
                "null"
              ],
              "avro.java.string": "String"
            }
          ],
          "avro.java.string": "String"
        }
      ],
      "default": null
    },
    {
      "name": "values",
      "type": [
        "null",
        {
          "type": "array",
          "items": "MapKeyValueEntry"
        }
      ],
      "default": null
    }
  ]
}
```

EventCall message:
```
{
  "header": {
    "senderId": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa",
    "domainId": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa",
    "tenantId": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa",
    "providerId": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa",
    "correlationId": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa",
    "externalCorrelationId": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa",
    "receivedDate": 1467378058627,
    "eventId": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa",
    "userId": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa",
    "eventMeta": [{
      "key": "workflow",
      "value": true
    }, {
      "key": "workflow_directory_integration",
      "value": false
    }]
  },
  "eventId": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa",
  "eventParams": null,
  "values": [{
    "key": "userId",
    "value": "f7d7e7c5-1a1f-4d2a-9ae0-ce07e83907fa"
  }]
}
```

## Data Processing
