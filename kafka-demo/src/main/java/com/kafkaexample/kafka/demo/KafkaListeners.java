package com.kafkaexample.kafka.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics="mynewtopic",groupId = "groupId")
    void listener(String data){
        System.out.println("Listener received ::"+data+"   !!!");
    }

}
