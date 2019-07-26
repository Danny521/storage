package com.netposa.ips.ga1400.access;

import com.netposa.ips.ga1400.enums.TopicEnum;
import com.netposa.ips.ga1400.service.DataRouteService;
import com.netposa.ips.ga1400.service.impl.EsPersistenceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Author: zhangtao@netposa.com on 2019/3/25
 */
@ConditionalOnExpression("#{'${storage.dist-source.elasticsearch.enable}'.equals('true')}")
@Component
@Slf4j
public class KafkaConsumer4Es {

    @Resource
    private DataRouteService dataRouteService;
    @Autowired
    private EsPersistenceServiceImpl esPersistenceService;

    @PostConstruct
    private void init() {
        log.info("------------>access data save to elasticsearch...");
    }

    @KafkaListener(clientIdPrefix = "es-face", id = "es-face",
            topics = "#{'${storage.access-kafka.consumer-topics.face}'.split(',')}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "20", //Listener的线程数，建议配置为跟对应的Topic的分区数
            autoStartup = "#{!'${storage.access-kafka.consumer-topics.face}'.isEmpty()}")
    public void faceBatchConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer, Logger log) {
        if (!records.isEmpty()) {
            log.info("--------->thread name={}", Thread.currentThread().getName());
            dataRouteService.handleDataWithConsumer(records, TopicEnum.FACE, esPersistenceService, ack, consumer);
        }
    }

    @KafkaListener(clientIdPrefix = "es-vehicle", id = "es-vehicle",
            topics = "#{'${storage.access-kafka.consumer-topics.vehicle}'.split(',')}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "20",
            autoStartup = "#{!'${storage.access-kafka.consumer-topics.vehicle}'.isEmpty()}")
    public void vehicleBatchConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        if (!records.isEmpty()) {
            dataRouteService.handleDataWithConsumer(records, TopicEnum.MOTOR_ViHICLE, esPersistenceService, ack, consumer);
        }
    }

    @KafkaListener(clientIdPrefix = "es-body", id = "es-body",
            topics = "#{'${storage.access-kafka.consumer-topics.body}'.split(',')}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "20",
            autoStartup = "#{!'${storage.access-kafka.consumer-topics.body}'.isEmpty()}")
    public void bodyBatchConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        if (!records.isEmpty()) {
            dataRouteService.handleDataWithConsumer(records, TopicEnum.BODY, esPersistenceService, ack, consumer);
        }
    }

    @KafkaListener(clientIdPrefix = "es-non-motor", id = "es-non-motor",
            topics = "#{'${storage.access-kafka.consumer-topics.non-motor}'.split(',')}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "20",
            autoStartup = "#{!'${storage.access-kafka.consumer-topics.non-motor}'.isEmpty()}")
    public void nonmotorBatchConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        if (!records.isEmpty()) {
            dataRouteService.handleDataWithConsumer(records, TopicEnum.NON_MOTOR_ViHICLE, esPersistenceService, ack, consumer);
        }
    }
}
