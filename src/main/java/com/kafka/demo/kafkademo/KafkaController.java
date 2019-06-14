package com.kafka.demo.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Logger logger = LoggerFactory.getLogger(KafkaController.class);


    private MessageChannel channel;

    public KafkaController(ProducerChannel channel) {
        this.channel = channel.messageChannel();
    }

    @PostMapping(value = "/publish-stream")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {

        logger.info(String.format("#### -> Producing message -> '%s'", message));

        var event = new Event();
        event.setEvent(message);

        channel.send(MessageBuilder
                .withPayload(event).build());

    }


}
