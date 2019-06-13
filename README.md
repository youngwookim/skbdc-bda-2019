# skbdc-bda-2019

SK Big Data Course 2019 - Big Data Architecture (Fast Data Architecture)

Designing Modern Streaming Data Applications

2019. 06. 27 ~ 28

과정 개요:
- 스트리밍 데이터 처리 시스템을 구성하기 위해 필요한 구성 요소에 대하여 학습
- 실시간 데이터를 위한 오픈소스 프로젝트와 해당 프로젝트의 특징 학습
- 성공적인 데이터 응용 설계와 개발을 위한 고려사항
- 스트리밍 데이터 아키텍처 시나리오를 바탕으로 e2e 스트리밍 응용 개발 실습

목차:
- 빅데이터 아키텍처
  - 스트리밍 데이터 응용을 위한 빅데이터 아키텍처
- 스트리밍 데이터 플랫폼
  - Data Source
  - Event Hub / Message Broker
  - Data Ingestion / Data Integration
  - Data Storage
  - Stream Processing
  - Data Analytics / SQL / Search
- Hands-on Labs

## Prerequisites
- Git
  - https://git-scm.com/downloads
- Docker & Docker Compose
  - https://www.docker.com/products/docker-desktop
- Java SE Development Kit 8 (a.k.a Java 8 / JDK 8)
  - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- (optionally) Maven 3.3+
- (optionally) Preferred IDE for Java. e.g., Eclipse, IntelliJ
- (optionally) Python 2.x or 3.x

Sanity check for dev env:
```
# git
$ git --version
git version 2.19.2

# docker
$ docker --version
Docker version 18.09.0, build 4d60db4
$ docker-compose --version
docker-compose version 1.23.2, build 1110ad01

# Java8 (JDK)
$ java -version
java version "1.8.0_192"
Java(TM) SE Runtime Environment (build 1.8.0_192-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.192-b12, mixed mode)

# Maven
$ mvn -version
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-11T01:41:47+09:00)
Maven home: /usr/local/Cellar/maven@3.3/3.3.9/libexec
Java version: 1.8.0_192, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_192.jdk/Contents/Home/jre
Default locale: ko_KR, platform encoding: UTF-8
OS name: "mac os x", version: "10.14.1", arch: "x86_64", family: "mac"

# python
$ python --version
Python 2.7.10

# HOST IP address
$ ifconfig

```

## Hands-on Labs
https://github.com/youngwookim/skbdc-bda-2019
```
$ cd /path/to/workspace
$ git clone https://github.com/youngwookim/skbdc-bda-2019.git
$ cd skbdc-bda-2019
$ ls -als

```

labs/README.md or https://github.com/youngwookim/skbdc-bda-2019/tree/master/labs

## Links
TBD
