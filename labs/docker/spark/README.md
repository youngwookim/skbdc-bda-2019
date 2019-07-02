# Apache Spark with Minio on Docker container

Spark 2.4+, Hadoop 3.0+

## Run docker container

```
$ docker run --rm -it --name spark1 --net docker_default -v "$PWD":/data -p 4040:4040 gettyimages/spark bash -l

#
```

Copy core-site.xml into container:
```
$ export SPARK=$(docker ps --filter name=spark1 --format={{.ID}})
$ docker cp core-site.xml "$SPARK":/usr/hadoop-3.0.0/etc/hadoop/core-site.xml
```

## Spark shell with S3 support
```
# export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
# export SPARK_HOME=/usr/spark-2.4.1
# export PATH=$PATH:$SPARK_HOME/bin
# export HADOOP_HOME=/usr/hadoop-3.0.0
# export PATH=$PATH:$HADOOP_HOME/bin
# export LD_LIBRARY_PATH=$HADOOP_HOME/lib/native
# export HADOOP_OPTIONAL_TOOLS="hadoop-aws"
# export SPARK_DIST_CLASSPATH=$(hadoop classpath)
```

```
# cd /usr/spark-2.4.1
# bin/spark-shell --master local[4]
```

## Reading a text file from Minio
```
scala> val b1 = sc.textFile("s3a://data/gutenberg/4300-0.txt")
scala> b1.collect().foreach(println)
```

## Word count
```
scala> val textFile = sc.textFile("s3a://data/gutenberg/4300-0.txt")
scala> val counts = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
scala> counts.saveAsTextFile("s3a://test/wc/result")

```

Refs.
- https://github.com/minio/cookbook/blob/master/docs/apache-spark-with-minio.md
- https://github.com/gettyimages/docker-spark
- https://hadoop.apache.org/docs/r3.2.0/hadoop-aws/tools/hadoop-aws/index.html
