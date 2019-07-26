package com.netposa.ips.ga1400.service.impl;

import com.alibaba.fastjson.JSON;
import com.netposa.ips.ga1400.modules.kafka.KafkaProducer;
import com.netposa.ips.ga1400.service.DataPersistenceService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
@Service
@Slf4j
public class KafkaPersistenceServiceImpl implements DataPersistenceService {

    @Resource
    private KafkaProducer kafkaProducer;
    private static long t1 = System.currentTimeMillis();

    @Value("${storage.dist-source.kafka.topics.face}")
    private String faceTopic;
    @Value("${storage.dist-source.kafka.topics.vehicle}")
    private String vehicleTopic;
    @Value("${storage.dist-source.kafka.topics.non-motor}")
    private String nonVehicleTopic;
    @Value("${storage.dist-source.kafka.topics.body}")
    private String vodyTopic;

    private List<ConsumerRecord<String, String>> faceTempList = new ArrayList<>();
    private List<ConsumerRecord<String, String>> vehicleTempList = new ArrayList<>();
    private List<ConsumerRecord<String, String>> nonVehicleTempList = new ArrayList<>();
    private List<ConsumerRecord<String, String>> bodyTempList = new ArrayList<>();

    @Override
    @Synchronized
    public void batchSaveFaceWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        sendDataToKafka(faceTopic, records, ack, consumer, faceTempList);
    }

    @Override
    @Synchronized
    public void batchSaveMotorVehicleWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        sendDataToKafka(vehicleTopic, records, ack, consumer, vehicleTempList);
    }

    @Override
    @Synchronized
    public void batchSaveNonMotorVehicleWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        sendDataToKafka(nonVehicleTopic, records, ack, consumer, nonVehicleTempList);
    }

    @Override
    @Synchronized
    public void batchSaveBodyWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        sendDataToKafka(vodyTopic, records, ack, consumer, bodyTempList);
    }

    private void sendDataToKafka(String topic, List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer, List<ConsumerRecord<String, String>> faceTempList) {
        if (!StringUtils.isEmpty(topic)) {
            for (ConsumerRecord<String, String> record : records) {
                faceTempList.add(record);
                try {
                    if ((System.currentTimeMillis() - t1) > 5 * 60 * 1000) {
                        t1 = System.currentTimeMillis();
                        log.info("------send record to kafka>{}", JSON.toJSONString(record));
                    }
                    kafkaProducer.sendMessage(topic, record.value());
                } catch (Exception e) {
                    commit(consumer, faceTempList);//把已经发出的部分消息提交
                    consumer.close();
                    faceTempList.clear();
                    log.error("----error----->{}", e);
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        return;
                    } catch (InterruptedException e1) {
                    }
                }
            }
            faceTempList.clear();
        }
        ack.acknowledge();
    }

    private void commit(Consumer<String, String> consumer, List<ConsumerRecord<String, String>> tempList) {
        Map<TopicPartition, OffsetAndMetadata> map = new HashMap<>();
        tempList.forEach(consumerRecord -> {
            TopicPartition topicPartition = new TopicPartition(consumerRecord.topic(), consumerRecord.partition());
            OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(consumerRecord.offset());
            map.put(topicPartition, offsetAndMetadata);
        });
        consumer.commitSync(map);
        tempList.clear();
    }
}
