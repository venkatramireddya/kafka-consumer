package com.ttech.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttech.kafka.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserConsumer {

	@KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}", containerFactory = "userKafkaListenerContainerFactory")
	public void listen(User  user,
			@Header(KafkaHeaders.GROUP_ID) String group_id,
			@Header(KafkaHeaders.OFFSET) String offset,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) String receivedPartitionId,
			@Header(KafkaHeaders.RECEIVED_TIMESTAMP) String receivedTimeStamp,
			@Header(KafkaHeaders.RECEIVED_TOPIC) String receivedTopic) {
		
		log.info("***");
		log.info("*** group_id = "+ group_id);
		log.info("*** offset = " + offset);
		log.info("*** receivedPartitionId = " + receivedPartitionId);
		log.info("*** receivedTimeStamp = " + receivedTimeStamp);
		log.info("*** receivedTopic = "+ receivedTopic);
		ObjectMapper objectMapper = new ObjectMapper();
	try {
		String jsonInString = objectMapper.writeValueAsString(user);
        System.out.println(jsonInString);
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}
}
