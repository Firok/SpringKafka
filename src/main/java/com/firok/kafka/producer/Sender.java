package com.firok.kafka.producer;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
public class Sender {
	
	private final Logger log = LoggerFactory.getLogger(Sender.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void put(String topic, String data){
		log.info("Sending data='{}' to topic='{}'", data, topic);
		kafkaTemplate.send(topic, data);
	}

}
