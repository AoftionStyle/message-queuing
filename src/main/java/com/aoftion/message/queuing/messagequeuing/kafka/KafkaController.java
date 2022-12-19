package com.aoftion.message.queuing.messagequeuing.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class KafkaController implements CommandLineRunner {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        // Message kafkaMessage = new Message
        String topic = "orders";
        String message = "aoftion101";
        System.out.println("message : " + message);
        // kafkaTemplate.send(message, message, message)
        kafkaTemplate.send(topic, 0, "5", message);
        // listen(message, message, message);
    }

    // @KafkaListener(topics = "orders", autoStartup = "false")
    // public void listen(String value,
    // @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
    // @Header(KafkaHeaders.RECEIVED_KEY) String key) {
    // System.out.println(String.format("Consumed event from topic %s: key = %-10s
    // value = %s", topic, key, value));
    // }

    @KafkaListener(topics = "orders", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }

}
