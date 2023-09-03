package com.kafkaconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafkaconsumer.model.User;
import com.kafkaconsumer.util.KafkaConstants;


@Service
public class KafkaConsumerService {

    @KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void consumeNewUser(User user){
        System.out.println("Data Received From Kafka Topic " +user);
    }
    
}


// @KafkaListener will continuosly listening to the topic 