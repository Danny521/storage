package com.netposa.ips.ga1400.service;

import com.netposa.ips.ga1400.enums.TopicEnum;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

/**
 * Author: zhangtao@netposa.com on 2019/3/29
 */
public interface DataRouteService {
    void handleDataWithConsumer(List<ConsumerRecord<String, String>> records, TopicEnum topicEnum, DataPersistenceService persistenceService, Acknowledgment ack, Consumer<String, String> consumer);
}
