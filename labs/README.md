# SKBDA BDA, Hands-on Labs

* [Dataflow](#data-flow)
* [Software Stack](#software-stack)
* [Stream Data Platform](#stream-data-platform)
  * [Setup Kafka cluster via Docker](#setup-kafka-cluster-via-docker)
* [Sanity check](#sanity-check)
* [Data Source](#data-source)
* [Data Processing](#data-processing)

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
git clone https://github.com/youngwookim/kafka-stack-docker-compose.git -b skbda2019
cd kafka-stack-docker-compose

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

![Kafka Docker network](https://github.com/wurstmeister/kafka-docker/wiki/kafka-single-broker.png)

Networking of (Kafka) docker compose, https://github.com/wurstmeister/kafka-docker/wiki/Connectivity

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
cd /path/to/workspace
git clone https://github.com/youngwookim/kadmin.git
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

* Kafka Manager
A tool for managing Apache Kafka.
- https://github.com/yahoo/kafka-manager

http://localhost:9090 (9000 -> 9090)

## Data Source
NASDAQ symbols:
- https://datahub.io/core/nasdaq-listings

nasdaq-listed
- https://datahub.io/core/nasdaq-listings/r/nasdaq-listed.csv
```
Field information
Field Name	Order	Type (Format)	Description
Symbol	1	string
Company Name	2	string
Security Name	3	string
Market Category	4	string
Test Issue	5	string
Financial Status	6	string
Round Lot Size	7	number
```

nasdaq-listed-symbols
- https://datahub.io/core/nasdaq-listings/r/nasdaq-listed-symbols.csv
```
Field information
Field Name	Order	Type (Format)	Description
Symbol	1	string
Company Name	2	string
```

IEX Trading API, https://iextrading.com/developer/docs/
The IEX API is a set of services offered by The Investors Exchange (IEX)
to provide access to data from the Exchange to developers and engineers for free.

https://github.com/WojciechZankowski/iextrading4j


IEX trading, "Quote -- 주식시세":
```
    private final String symbol;
    private final String companyName;
    private final String primaryExchange;
    private final String sector;
    private final String calculationPrice;
    private final BigDecimal open;
    private final Long openTime;
    private final BigDecimal close;
    private final Long closeTime;
    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal latestPrice;
    private final String latestSource;
    private final String latestTime;
    private final Long latestUpdate;
    private final BigDecimal latestVolume;
    private final BigDecimal iexRealtimePrice;
    private final BigDecimal iexRealtimeSize;
    private final Long iexLastUpdated;
    private final BigDecimal delayedPrice;
    private final Long delayedPriceTime;
    private final BigDecimal extendedPrice;
    private final BigDecimal extendedChange;
    private final BigDecimal extendedChangePercent;
    private final Long extendedPriceTime;
    private final BigDecimal previousClose;
    private final BigDecimal change;
    private final BigDecimal changePercent;
    private final BigDecimal iexMarketPercent;
    private final BigDecimal iexVolume;
    private final BigDecimal avgTotalVolume;
    private final BigDecimal iexBidPrice;
    private final BigDecimal iexBidSize;
    private final BigDecimal iexAskPrice;
    private final BigDecimal iexAskSize;
    private final BigDecimal marketCap;
    private final BigDecimal peRatio;
    private final BigDecimal week52High;
    private final BigDecimal week52Low;
    private final BigDecimal ytdChange;
    private final BigDecimal bidPrice;
    private final BigDecimal bidSize;
    private final BigDecimal askPrice;
    private final BigDecimal askSize;
```

Avro schema:
```
{
  "type":"record",
  "name":"IexTrading",
  "namespace":"com.example.avro",
  "doc": "Avro schema for IEX Trading API",
  "fields":[
    {
      "name":"symbol",
      "type":"string",
      "doc":"Stock Symbol"
    },
    {
      "name":"companyName",
      "type": ["null", "string"],
      "avro.java.string":"String",
      "doc":"Company Name"
    },
    {
      "name":"primaryExchange",
      "type": ["null", "string"],
      "avro.java.string":"String"
    },
    {
      "name":"sector",
      "type": ["null", "string"],
      "avro.java.string":"String"
    },
    {
      "name":"calculationPrice",
      "type": ["null", "string"],
      "avro.java.string":"String"
    },
    {
      "name":"open",
      "type" : ["null", "long"]
    },
    {
      "name":"openTime",
      "type": ["null", "long"]
    },
    {
      "name":"close",
      "type" : ["null", "long"]
    },
    {
      "name":"closeTime",
      "type": ["null", "long"]
    },
    {
      "name":"high",
      "type" : ["null", "long"]
    },
    {
      "name":"low",
      "type" : ["null", "long"]
    },
    {
      "name":"latestPrice",
      "type" : ["null", "long"]
    }
  ]
}
```

## Data Processing
1. Kafka Streams
2. Apache Flink

## Data Analytics / SQL / Dashboard

### SQL
Running Presto & Minio(S3):
```
cd /path/to/workspace
git clone https://github.com/youngwookim/presto-minio.git
cd presto-minio
```

```
MINIO_ACCESS_KEY: V42FCGRVMK24JJ8DHUYG
MINIO_SECRET_KEY: bKhWxVF3kQoLY9kFmt91l+tDrEoZjqnWXzY9Eza
```

2. Data Analytics    
https://github.com/youngwookim/my-docker-stacks

```
cd /path/to/workspace
git clone https://github.com/youngwookim/my-docker-stacks.git
cd my-docker-stacks
cd jupyter-ds
docker build --rm -t youngwookim/my-datascience-notebook .

```

Running JupyterLab:
```
cd /path/to/workspace/skbda2019/labs
docker run --rm --user root -p 8888:8888 -e GRANT_SUDO=yes -e JUPYTER_ENABLE_LAB=yes -v "$PWD":/home/jovyan youngwookim/my-datascience-notebook:latest

......

Copy/paste this URL into your browser when you connect for the first time,
    to login with a token:
        http://(be1ee3cbaf72 or 127.0.0.1):8888/?token=42a319245ef11fc8b5fbae2480fd3b3da557489b05f4f357
```

http://localhost:8888/?token=42a319245ef11fc8b5fbae2480fd3b3da557489b05f4f357

### Dashboard

1. Superset
```
docker run -d --name superset -p 8088:8088 tylerfowler/superset
```

Login with a default username and password of:
```
username: admin
password: superset
```

Sources:
```
presto://[HOST_IP]:8080/minio

e.g., presto://172.16.0.68:8080/minio

```

SQL Lab:
```

```

2. metatron discovery
https://metatron.app/download/installation-guide-docker/

```
docker run -i -d --rm -m 6G -p 8180:8180 --name metatron-discovery metatronapp/discovery:latest

```

Login:
```
admin / admin
```
