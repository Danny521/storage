package com.netposa.ips.ga1400.service;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
public interface DataPersistenceService {

    void batchSaveFaceWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer);

    void batchSaveMotorVehicleWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer);

    void batchSaveNonMotorVehicleWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer);

    void batchSaveBodyWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer);
}
