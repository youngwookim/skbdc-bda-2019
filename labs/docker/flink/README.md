```
./build.sh --from-release --flink-version <x.x.x> --scala-version <x.xx> --hadoop-version <x.x> [--image-name <image>]

E.g.,

./build.sh --from-release --flink-version 1.8.1 --scala-version 2.11 --hadoop-version 2.8 --image-name flink-s3-local
```

Running Flink local cluster:
```
docker run -i -d -t --net docker_default flink-s3-local
```
