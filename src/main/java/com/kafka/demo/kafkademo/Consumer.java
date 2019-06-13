package com.kafka.demo.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final String TOPIC = "events";

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @KafkaListener(topics = TOPIC, groupId = "group_id"
//            ,topicPartitions = @TopicPartition(topic = TOPIC, partitions = {"0"})
    )
    public void consume(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info(String.format("#### -> Consumed message -> '%s' from partition: '%d'", message, partition));
    }
}
