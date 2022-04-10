
 Sample-Kafka-Project:


This is a sample kafka project explaining producer and consumer in kafka with the help of spring boot

                                                   **APACHE KAFKA QUICKSTART**


# STEP 1: GET KAFKA

      Download the latest Kafka release and extract it:
      
      tar -xzf kafka_2.13-3.1.0.tgz
      cd kafka_2.13-3.1.0


# STEP 2: START THE KAFKA ENVIRONMENT

   **NOTE** : Your local environment must have Java 8+ installed.

Run the following commands in order to start all services in the correct order:

Start the ZooKeeper service

  **Note**: Soon, ZooKeeper will no longer be required by Apache Kafka.

1. bin/zookeeper-server-start.sh config/zookeeper.properties
 
 
Open another terminal session and run:

 
 Start the Kafka broker service

2. bin/kafka-server-start.sh config/server.properties

Once all services have successfully launched, you will have a basic Kafka environment running and ready to use.


 

# STEP 3: CREATE A TOPIC TO STORE YOUR EVENTS


 Kafka is a distributed event streaming platform that lets you read, write, store, and process events (also called records or messages in the documentation) across many machines.

 Example events are payment transactions, geolocation updates from mobile phones, shipping orders, sensor measurements from IoT devices or medical equipment, and much more. These events are organized and stored in topics. Very simplified, a topic is similar to a folder in a filesystem, and the events are the files in that folder.

 So before you can write your first events, you must create a topic.



**Create topic command:  **


bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092




All of Kafka's command line tools have additional options: run the kafka-topics.sh command without any arguments to display usage information. For example, it can also show you details such as the partition count of the new topic:

bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092


Topic:quickstart-events  PartitionCount:1    ReplicationFactor:1 Configs:
    Topic: quickstart-events Partition: 0    Leader: 0   Replicas: 0 Isr: 0
    
    
    
    
# STEP 4: WRITE SOME EVENTS INTO THE TOPIC


A Kafka client communicates with the Kafka brokers via the network for writing (or reading) events. Once received, the brokers will store the events in a durable and fault-tolerant manner for as long as you need—even forever.

Run the console producer client to write a few events into your topic. By default, each line you enter will result in a separate event being written to the topic.    



bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092

add this after consumer is ran

This is my first event
This is my second event

You can stop the producer client with Ctrl-C at any time.

# STEP 5: READ THE EVENTS


Open another terminal session and run the console consumer client to read the events you just created:

bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092

this will be printed :

This is my first event
This is my second event


# STEP 6: IMPORT/EXPORT YOUR DATA AS STREAMS OF EVENTS WITH KAFKA CONNECT

You probably have lots of data in existing systems like relational databases or traditional messaging systems, along with many applications that already use these systems. Kafka Connect allows you to continuously ingest data from external systems into Kafka, and vice versa. It is thus very easy to integrate existing systems with Kafka. To make this process even easier, there are hundreds of such connectors readily available.

Take a look at the Kafka Connect section to learn more about how to continuously import/export your data into and out of Kafka.


# STEP 7: PROCESS YOUR EVENTS WITH KAFKA STREAMS

Once your data is stored in Kafka as events, you can process the data with the Kafka Streams client library for Java/Scala. It allows you to implement mission-critical real-time applications and microservices, where the input and/or output data is stored in Kafka topics. Kafka Streams combines the simplicity of writing and deploying standard Java and Scala applications on the client side with the benefits of Kafka's server-side cluster technology to make these applications highly scalable, elastic, fault-tolerant, and distributed. The library supports exactly-once processing, stateful operations and aggregations, windowing, joins, processing based on event-time, and much more.

To give you a first taste, here's how one would implement the popular WordCount algorithm:

KStream<String, String> textLines = builder.stream("quickstart-events");

KTable<String, Long> wordCounts = textLines
            .flatMapValues(line -> Arrays.asList(line.toLowerCase().split(" ")))
            .groupBy((keyIgnored, word) -> word)
            .count();

wordCounts.toStream().to("output-topic", Produced.with(Serdes.String(), Serdes.Long()));


The Kafka Streams demo and the app development tutorial demonstrate how to code and run such a streaming application from start to finish.


# STEP 8: TERMINATE THE KAFKA ENVIRONMENT

Now that you reached the end of the quickstart, feel free to tear down the Kafka environment—or continue playing around.

Stop the producer and consumer clients with Ctrl-C, if you haven't done so already.
Stop the Kafka broker with Ctrl-C.
Lastly, stop the ZooKeeper server with Ctrl-C.
If you also want to delete any data of your local Kafka environment including any events you have created along the way, run the command:

rm -rf /tmp/kafka-logs /tmp/zookeeper


