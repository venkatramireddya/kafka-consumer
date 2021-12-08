package com.ttech.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ttech.kafka.model.User;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

@Configuration
public class KafkaConfiguration {


	@Value("${kafka.bootstrap.servers}")
	private String bootStrapServer;
	
	
	public ConsumerFactory<String, Object> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		//props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		//props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringJsonMessageConverter.class);
		props.put("basic.auth.credentials.source","USER_INFO");
		props.put("schema.registry.basic.auth.user.info","RLPZMD54RZMMNIOK:xAseoJIW0xzxOg+QiJgi3USz7ItW8T2kcm/1vf/aHHiVYWlatpyAbm5CuyDaoygF");
		props.put("schema.registry.url", "https://psrc-yg906.us-east-2.aws.confluent.cloud");
		props.put("key.converter","io.confluent.connect.avro.AvroConverter");
		props.put("key.converter.schema.registry.url", "https://psrc-yg906.us-east-2.aws.confluent.cloud");
		props.put("value.converter", "io.confluent.connect.avro.AvroConverter");
		props.put("value.converter.schema.registry.url", "https://psrc-yg906.us-east-2.aws.confluent.cloud");
		//props.put("specific.avro.reader", "true");
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

		//return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Object.class) );
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new KafkaAvroDeserializer() );
	}
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
	}
	
	 
    public ConsumerFactory<String, Object> userConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
       // props.put(ConsumerConfig.GROUP_ID_CONFIG, userGroupId);
        props.put("basic.auth.credentials.source","USER_INFO");
		props.put("schema.registry.basic.auth.user.info","RLPZMD54RZMMNIOK:xAseoJIW0xzxOg+QiJgi3USz7ItW8T2kcm/1vf/aHHiVYWlatpyAbm5CuyDaoygF");
		props.put("schema.registry.url", "https://psrc-yg906.us-east-2.aws.confluent.cloud");
		props.put("key.converter","io.confluent.connect.avro.AvroConverter");
		props.put("key.converter.schema.registry.url", "https://psrc-yg906.us-east-2.aws.confluent.cloud");
		props.put("value.converter", "io.confluent.connect.avro.AvroConverter");
		props.put("value.converter.schema.registry.url", "https://psrc-yg906.us-east-2.aws.confluent.cloud");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        //props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new KafkaAvroDeserializer());
    }
    
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }
}
