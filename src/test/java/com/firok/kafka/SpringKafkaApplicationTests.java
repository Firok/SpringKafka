package com.firok.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.context.junit4.SpringRunner;

import com.firok.kafka.consumer.Receiver;
import com.firok.kafka.producer.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringKafkaApplicationTests {
	
	private static String BOOT_TOPIC = "boot.t";
	
	@Autowired
	private Sender sender;
	
	@Autowired
	private Receiver receiver;
	
	@ClassRule
	public static KafkaEmbedded kafkaEmbedded = new KafkaEmbedded(1, true, BOOT_TOPIC);

	@Test
	public void testReceive() throws Exception{
		sender.put(BOOT_TOPIC, "My First Kafka App");
		
		receiver.getCountDownLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getCountDownLatch().getCount()).isEqualTo(1);
	}

}
