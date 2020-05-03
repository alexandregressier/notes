# Apache Kafka Series - Learn Apache Kafka for Beginners v2, Udemy - Notes

## 1. Kafka Introduction

### Apache Kafka in 5 minutes

Kafka was created back in 2011

How companies start: _data_ transists from a _source system_ to a _target system_
How companies grow: many data transit from source systems to target systems
-> Things become complicated

Problems:
- Ex: if you have 4 source systems & 6 target systems, you need to write 24 _integrations_
- Each integration comes w/ difficulties around:
  - _Protocol_: how the data is exchanged (TCP, HTTP, REST, FTP, JDBC...)
  - _Data format_: how the data is parsed (Binary, csv, JSON, Avro, Rift...)
  - _Data schema & evolution_: how the data is shaped & is subject to change
- Each source system will have an **increased load** from the connections

Solution:
- Apache Kafka takes many source systems & target systems and is a **single integration**
  - Enables you to _decouple_ your **data streams** & your **systems**
  - Source systems will source their data straight from Apache Kafka
- Data streams examples:
  - Website events
  - Pricing data
  - Financial transactions
  - User interactions
  - ...
- Once the data is in Kafka, you can put it in for example:
  - Database
  - Analytics
  - Email systems
  - Audit

Apache Kafka:
- Created by LinkedIn
- Now Open Source
- Mainly maintained by the private company Confluent
- Under the Apache stewardship
- Distributed, resilient architecture, fault tolerant
- Horizontal scalability:
  - Can scale to hundreds of brokers
  - Can scale to millions of messages per second
- High performance (latency of less than 10 ms) -> _Real-time_
- Used by the 2000+ firms, 35% of the Fortune 500: LinkedIn, Airbnb, Netflix, Uber, Walmart...j

Use cases:
- Messaging system
- Activity tracking
- Gather metrics from many different locations
- Application logs gathering
- Stream processing (w/ the Kafka Streams API or Spark for example)
- Decoupling of system dependencies
- Integration w/ Spark, Flink, Storm, Hadoop...

-> Apache Kafka can be used as an enterprise _backbone_

Real world examples:
- Netflix uses Kafka to apply recommendations in real-time while you are watching TV shows
- Uber uses Kafka to gather user, taxi, & trip data in real-time to compute, forecast demand, & compute surge pricing in real-time
- LinkedIn uses Kafka to prevent spam, collect user interactions to make better connection recommendations in real-time

-> Kafka is used by theses companies to make real-time decisions, & provide real-time insights to their users

**IMPORTANT:** Kafka is only used as a transportation mechanism
-> Really good at making your data move


### Course objectives

Structure:
1. Fundamentals
  - Kafka Theory
  - Starting Kafka
  - Kafka CLI
  - Kafka & Java 101
2. Real world
  - Twitter producer
  - ElasticSearch consumer
  - Extended API intro + case studies + Kafka in the enterprise
3. Advanced & Annexes
  - Advanced topic configuration
  - Annexes

Kafka learning path:
1. **Kafka for beginners** (you are here): get a strong base for Kafka, basic operations, write your first producers & consumers
2. **Kafka Connect API**: understand how to import/export data to/from Kafka
3. **Kafka Streams API**: learn how to porcess & transform data w/in Kafka
4. **Kafka Cluster Setup & Administration**: get a deep understanding of how Kafka & Zookeeper works, how to setup Kafka & various administrations tasks
5. **Confluent components**: REST proxy & schema registry
6. **Kafka Security**: setup Kafka security in a cluster & integrate your applications w/ Kafka security
7. **Kafka monitoring & operations**: use Prometheus & Graphana to monitor Kafka, learn operations
8. **KSQL on ksqlDB**
...

## 2. Kafka Fundamentals

### A. Kafka Theory

#### Topics, Partitions & Offsets

_Topic_: a particular stream of data
  - Similar to a table in a DB (w/o all the constraints)
  - You can have as many topics as you want
  - A topic is identified by its **name**
  - Topics are split in _partitions_
  - The number of partitions has to be defined when you create a topic
    - Can be changed later on
- _Partitions_
  - Are ordered & zero-indexed
  - Each _message_ w/in a partitiion gets also a zero-indexed serial id, called _offset_

Example:
- Kafka topic
  - Partition 0 `[0,1,2,3,4,5,6,7,8,9,10,11,12]`
  - Partition 1 `[0,1,2,3,4,5,6,7,8]`
  - Partition 2 `[0,1,2,3,4,5,6,7,8,9,10]`
-> Each message leads to _writes_ to a data source

The number of messages w/in each partition (those messages indexed by the offset) is independent from other partitions

Topic example: `truck_gps`
- Say you have a fleet of trucks, each truck reports its GPS position to Kafka
- You can have a topic `truck_gps` that contains the positions of all trucks
  - The name of the topic indicates which data is streamed
- Each truck will send a message to Kafka every 20 seconds (arbitrary), each messae will contain the **truck ID** & the **truck position** (lat & long)
- **IMPORTANT**: you do not have 1 topic per truck, but the single topic `truck_gps` for all the trucks
- We choose to create that topic w/ 10 partiions (arbitrary)
- A location dashboard & a notification service serve as _consumers_ of the data

Gotchas:
- Offsets only have a meaning for a specific partition
  - Ex: offset 3 in partition 0 does not represent the same data as offset 3 in partiion 1
- Order is guaranteed only **w/in** a partition (not **across** partitions)
  - Ex: it is impossible in Kafka to dermine which message has been written first from one partition to another
- Data is kept only for a limited time (default: 1 week)
  - But the offset is never resetted to 0
- All data written to a partition is **immutable**
  - If you want to write a new message, you write it at the end of the partition
- Data is **assigned randomly** to a partition
  - Unless a _key_ is provided  


#### Brokers and Topics

A Kafka _cluster_:
- Is composed of multiple brokers (servers)

_Brokers_:
- Holds the topics & their partitions
- Each broker is basically a server
- Each broker is identified by an **ID** (int, as opposed to a string)
- Each broker contains **only certain topic partitions**
  - B/c Kafka is distributed
- After connecting to any broker (called a _bootstrap broker), you will be connected to the **entire cluster**
- 3 is good number of brokers to get started
  - Bigger cluster may get up over 100 brokers

Example (IDs are arbitrary):
- Broker 101
- Broker 102
- Broker 103

We create Topic-A w/ 3 partitions & Topic-B w/ 2 partitions:
- Broker 101:
  - Topic-A Partition 0
  - Topic-B Partition 1
- Broker 102
  - Topic-A Partition 2
  - Topic-B Partition 0
- Broker 103
  - Topic-A Partition 1
-> (Partitions can be interpreted as simple files for now)
-> Partition numbers & broker numbers are **not related**
-> The topic is spread/distributed equally accross brokers
  - **IMPORTANT**: when you create a topic, Kafka will automatically assign a topic & distribute it across all your brokers (the distribution is automatic)
  - If you create a topic w/ 4 partitions, one of the broker will have 2 of its partitions


#### Topic replication

Kafka is a distributed system thanks to brokers

When data is distributed, you generally also need replication:
-> Kafka's _topic replication factor_

- The replication factor is simply the number of copies of a partition
- When you create a topic, you need to set its **number of partitions** & its **replication factor**
- Topics should have a replication factor >1 (usually between 2 & 3)
   - 3 = gold standard, 2 = a bit risky
- If a broker is down, another broker can server the data

Example: Topic-A w/ 2 partitions & replication factor of 2
- Broker 101:
  - Topic-A Partition 0
- Broker 102
  - Topic-A Partition 1
  - Topic-A Partition 0
- Broker 103
  - Topic-A Partition 1
-> The replicated partitions are linked
-> Imagine any of the 3 brokers down: the 2 other brokers can still serve the data

Concept of _leader for a partition_:
- **GOLDEN RULE**: at any time, only **ONE broker** can be a leader for a given partition
- Only that leader can receive & serve data for a partition
-> Active/Passive replication: the other brokers will synchronize the data
-> Each partition has:
  - 1 leader
  - n _ISR_ (In-Sync Replica)
-> When a broker w/ leader partitions goes down, the leadership is passed onto the other synchroninzing brokers via an election process
  - When is the leader partition is back up, it will attempt to be the leader again
  -> This process happens in the background & is handled by Kafka

**IMPORTANT**: The entity deciding what is leader & what is ISR is _Apache Zookeeper_
 
> Zookeeper is essentially a service for distributed systems offering a _hierarchical key-value_ store, which is used to provide a _distributed configuration_ service, _synchronization service_, and _naming registry_


#### Producers and Message keys

How do we get data in Kafka?
-> The role of a _producer_

_Producers_:
- Write data to topics (which are made of partitions)
- Producers are somewhat "magical": they automatically know to which _broker & partition_ to write to
  - Removes a lot of the burden
- In case of broker failures, producer will _automatically recover_ 
- A producer is placed in front of all the brokers
  -  The load is _balanced_ to many brokers thanks to the _number of partitions_
  - The producer sends data & is the source of the writes that we had before w/ partitions

The producer understands to which broker you send data to:
- If you send data w/o a _key_, the data will be sent in a round robin manner to brokers
  -> This is how load balancing is done in Kafka

- Producers can choose to receive _acknowledgement of data writes_
- The 3 send modes:
  - `acks=0`: producer will not wait for acknowledgement (possible data loss)
    -> There are still use cases
  - `acks=1` (default): producer will wait for leader acknowledgement (limited data loss)
  - `acks=all`: leader + ISR acknowledgement (no data loss)

- Producers can choose to send a **key** w/ the message
  - Can be of any type: string, number...
  - If `key=null`, data is sent in a round robin fashion
  - If a key is sent, then all messages for that key will always go to the **same partition** (guaranteed by Kafka)
  - **IMPORTANT**: a key is basically sent if you need message ordering for a specific field (ex: `truck_id`)

Example w/ the truck fleet:
- We want each data for each truck to be in order
  - **Not across** the trucks but just for one specific truck we want the data **in order**
- We choose the key to be `truck_id` (a number, a string...)

Abitrarily:
- `truck_id_123` data will always be in partition 0
- `truck_id_234` data will always be in partition 0
- `truck_id_345` data will always be in partition 1
- `truck_id_456` data will always be in partition 1
-> The mechanism of key to partition is called _key hashing_
  - Depends on the number of partitions & whatever you choose
  - You don't specify "This key goes to this partition", but you know that "**This key will always go to the same partition**"
    - Example: we know that `truck_id_123` will always go to partition 0
