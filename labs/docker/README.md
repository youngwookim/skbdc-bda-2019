# Docker containers for SKBDC BDA

## Running containers

Start containers:
```
$ docker-compose up

```

Kill containers:
```
$ docker-compose down
```

## Services and ports
- Single Zookeeper: $DOCKER_HOST_IP:2181
- Single Kafka: $DOCKER_HOST_IP:9092
- Kafka Schema Registry: $DOCKER_HOST_IP:8081
- Kafka Schema Registry UI: $DOCKER_HOST_IP:8001
- Minio: $DOCKER_HOST_IP:9000
- Presto: $DOCKER_HOST_IP:8080

# Refs.
- https://github.com/youngwookim/kafka-stack-docker-compose
- https://github.com/youngwookim/presto-minio

# Misc.
Warning: This will destroy all your images and containers. There is no way to restore them!

Run those commands in a shell:
```
#!/bin/bash

# Stop all running containers
$ docker stop $(docker ps -aq)

# Delete all containers
$ docker rm $(docker ps -a -q)

# Delete all images
$ docker rmi $(docker images -q)
```
