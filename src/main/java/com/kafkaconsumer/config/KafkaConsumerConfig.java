package com.kafkaconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafkaconsumer.model.User;
import com.kafkaconsumer.util.KafkaConstants;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

@Bean
public ConsumerFactory<String, User> consumerFactory(){
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,KafkaConstants.HOST);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG  ,StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG , JsonDeserializer.class);
    props.put(JsonDeserializer.TYPE_MAPPINGS, "User:com.kafkaconsumer.model.User,com.kafkaproducer.model.User:com.kafkaconsumer.model.User");
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(User.class));
}

@Bean
public ConcurrentKafkaListenerContainerFactory<String, User> kafkaListenerContainerFactory(){
    ConcurrentKafkaListenerContainerFactory<String, User>  factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;

}
}

    

