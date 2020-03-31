# Vert.x, Devoxx - Notes

<https://vertx.io/>

Julien Viet is the Vert.x project lead

Eclipse Vert.x:
- Started in 2012
- Open source
- Created by Tim Fox while working at Pivotal (creator of HornetQ & contributor to RabbitMQ, based on actors)
- Actors (from RabbitMQ) + Messaging (HornetQ) + Node.js (popular at the time) = Vert.x
- Part of the Eclipse Foundation, where Red Hat sponsors <5 developers

Vert.x is a **toolkit** for building **reactive** applications for the JVM

Requires:
- Java 8

Vert.x is a set of JARs on Maven Central (no app server)
Unopinionated: your build, your IDE

Vert.x features the CLI `vertx` tool

What does _reactive_ means?

Provides starter projects for:
- Gradle
- Maven
- sbt
- Ant

Main dependency: `vertx-core`

W/ Vert.x, unit tests are asynchronous
-> B/c of the `vertx-unit` dependency

The main class is called a _verticle_
-> It inherits from `AbstractVerticle`

A _verticle_ is the unit of deployment in Vert.x

In the main verticle, create a `main`:
```java
public class App {
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MainVerticle.class.getName());
    }
}
```

Test the verticle:
```bash
curl http://localhost:8080
```

`wrk` is a tool to test load performance
ANY PERFORMANCE TEST MADE ON LOCALHOST IS ASININE
```bash
wrk      \
  -d 60  \ # Duration of test in secs
  -t 1   \ # Number of threads to use
  -c 100 \ # Number of connections to keep open
  http://localhost:8080
```

`jps` is the _JVM Process Status Tool_
```bash
jps
```

Download and use the `jvmtop` tool:
```bash
wget https://github.com/patric-r/jvmtop/releases/download/0.8.0/jvmtop-0.8.0.tar.gz
tar -xzvf jvmtop-0.8.0.tar.gz
rm -rf jvmtop-0.8.0.tar.gz
chmod +x jvmtop.sh
```

One particularity of Vert.x is that is manages a lot of concurrency through very few threads
-> For example, there is a single thread to manage the connections (which is the _event loop_)

For now, the Vert.x starter just looks like a servlet

Copy the the current verticle into a new one called `GreetingVerticle`

Change the response to a Vert.x `JsonObject` (XML could have been done as well)

Do not forget the `Content-Type` header in production

Change the port as well

Import the `vertx-web-client` dependency

Vert.x chose a very modular approach to have the fewest dependencies possible (w/o going extreme)

Vert.x swears by Fluent APIs

