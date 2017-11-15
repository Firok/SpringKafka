package com.firok.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
	
	private final Logger log = LoggerFactory.getLogger(Receiver.class);
	
	private CountDownLatch countDownLatch = new CountDownLatch(1);

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}
	
	@KafkaListener(topics = "${kafka.topic.boot}")
	public void get(ConsumerRecord<?, ?> consumerRecord){
		log.info("Received data ='{}'", consumerRecord.toString());
		countDownLatch.countDown();
	}

}
