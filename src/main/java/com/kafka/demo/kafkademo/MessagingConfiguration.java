package com.kafka.demo.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@EnableBinding(value = {Event.class, ProducerChannel.class, ConsumerChannel.class})
public class MessagingConfiguration {

    private final Logger logger = LoggerFactory.getLogger(MessagingConfiguration.class);

    @StreamListener(ConsumerChannel.CHANNEL)
    public void consume(@Payload Event event, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info("Received event: {}, from partition: '{}'", event.getEvent(), partition);
    }


}
