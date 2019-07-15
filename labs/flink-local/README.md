https://ci.apache.org/projects/flink/flink-docs-stable/tutorials/local_setup.html

1. Download Flink tarball

```
wget http://mirror.apache-kr.org/flink/flink-1.8.1/flink-1.8.1-bin-scala_2.11.tgz

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

Copy required jars into 'lib' directory
- flink-s3-fs-hadoop
```
$ cp opt/flink-s3-fs-hadoop-1.8.1.jar lib/

```
- link-shaded-hadoop
https://repo.maven.apache.org/maven2/org/apache/flink/flink-shaded-hadoop-2-uber/2.8.3-7.0/flink-shaded-hadoop-2-uber-2.8.3-7.0.jar
`

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
--bootstrap.servers localhost:9092 \
--schema-registry-url http://localhost:8081 \
--group.id cgrp1
```
