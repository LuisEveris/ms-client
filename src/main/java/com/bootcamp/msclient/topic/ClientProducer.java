package com.bootcamp.msclient.topic;

import com.bootcamp.msclient.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ClientProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static final String CLIENT_TOPIC = "client-topic";

    public void sendClientToTopic(ClientDTO data) {
        kafkaTemplate.send(CLIENT_TOPIC, data);
    }

}
