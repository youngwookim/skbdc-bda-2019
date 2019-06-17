https://ci.apache.org/projects/flink/flink-docs-stable/tutorials/local_setup.html

1. Download Flink

```
wget http://apache.tt.co.kr/flink/flink-1.8.0/flink-1.8.0-bin-scala_2.11.tgz

```

2. Edit Flink conf
conf/flink-conf.yaml
```
state.backend: filesystem
s3.endpoint: http://127.0.0.1:9000
s3.path-style: true
s3.access-key: V42FCGRVMK24JJ8DHUYG
s3.secret-key: bKhWxVF3kQoLY9kFmt91l+tDrEoZjqnWXzY9Eza
```

```
cp opt/flink-s3-fs-hadoop-1.7.2.jar lib/ 

```

3. Start a Local Flink Cluster
```
$ ./bin/start-cluster.sh  # Start Flink

```

for Windows: https://ci.apache.org/projects/flink/flink-docs-release-1.8/tutorials/flink_on_windows.html

4. Run Flink streaming application

```
$ cd flink-[VERSION]
$ bin/flink run /path/to/flink-kafka-streaming-1.0.0.jar \
--input-topic iextrading \
--output-topic mystock \
--output-path s3://trading/mystock/ \
--bootstrap.servers 172.16.0.68:9092 \
--schema-registry-url http://172.16.0.68:8081 \
--group.id cgrp1
```


