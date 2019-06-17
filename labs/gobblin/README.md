Build Gobblin image:
```
$ git clone https://github.com/youngwookim/dockerized-gobblin -b gobblin-0.14.0
$ ./build-docker.sh

or

$ docker build --build-arg VERSION=0.14.0 -t gradiant/gobblin:0.14.0
```

Run Gobblin container:
```
$ docker run -it -v "$PWD"/etc/gobblin:/etc/gobblin gradiant/gobblin:0.14.0

# cd /gobblin-dist
# bin/gobblin-standalone.sh start
```
