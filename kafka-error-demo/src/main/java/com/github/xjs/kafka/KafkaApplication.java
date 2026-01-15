package com.github.xjs.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
// 1.start zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

// 2.start kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties
.\bin\windows\kafka-server-stop.bat


 list topic
 .\bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092

 delete topic
 .\bin\windows\kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic test-topic

 list partition
 .\bin\windows\kafka-topics.bat --describe --bootstrap-server localhost:9092 --topic test-topic
 .\bin\windows\kafka-topics.bat --describe --bootstrap-server localhost:9092 --topic test-topic.DLT

 alter partition
 .\bin\windows\kafka-topics.bat --alter --bootstrap-server localhost:9092  --partitions 8 --topic test-topic
 .\bin\windows\kafka-topics.bat --alter --bootstrap-server localhost:9092  --partitions 8 --topic test-topic.DLT

 .\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092  --topic test-topic.DLT  --from-beginning

*/
@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

}
