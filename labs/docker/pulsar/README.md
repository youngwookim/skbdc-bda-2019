## Start Pulsar in Docker
For MacOS and Linux:
```
$ docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  -v $PWD/data:/pulsar/data \
  apachepulsar/pulsar:2.3.2 \
  bin/pulsar standalone
```

For Windows:
```
$ docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  -v "$PWD/data:/pulsar/data".ToLower() \
  apachepulsar/pulsar:2.3.2 \
  bin/pulsar standalone
```

The aggregated broker metrics are also exposed in the Prometheus format at:
- http://localhost:8080/metrics/
