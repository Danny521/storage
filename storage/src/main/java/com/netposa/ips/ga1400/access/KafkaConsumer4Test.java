package com.netposa.ips.ga1400.access;

import com.netposa.iod.protobuf.DoorControl;
import com.netposa.ips.ga1400.enums.TopicEnum;
import com.netposa.ips.ga1400.service.DataRouteService;
import com.netposa.ips.ga1400.service.impl.KafkaPersistenceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangtao@netposa.com on 2019/3/25
 */
@ConditionalOnExpression("#{'${storage.dist-source.test.enable}'.equals('true')}")
@Component
@Slf4j
public class KafkaConsumer4Test {
    @Resource
    private DataRouteService dataRouteService;

    @Autowired
    private KafkaPersistenceServiceImpl kafkaPersistenceService;

    @PostConstruct
    private void init() {
        log.info("------------>access data save to kafka...");
    }

//    @KafkaListener(clientIdPrefix = "kafka-user", id = "kafka-user",
//            topics = "#{'${storage.access-kafka.consumer-topics.test}'.split(',')}",
//            containerFactory = "kafkaListenerContainerFactory",
//            concurrency = "10", //Listener的线程数，建议配置为跟对应的Topic的分区数
//            autoStartup = "#{!'${storage.access-kafka.consumer-topics.test}'.isEmpty()}")
//    public void faceBatchConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
//        if (!records.isEmpty()) {
//            records.forEach(record -> {
//                log.info("----->offset={}, record={}", record.offset(), record.value());
////                dataRouteService.handleDataWithConsumer(records, TopicEnum.FACE, kafkaPersistenceService, ack, consumer);
//            });
////            ack.acknowledge();
//        }
//    }

    @KafkaListener(clientIdPrefix = "door1", id = "mt4",
            topics = "#{'${storage.access-kafka.consumer-topics.test}'.split(',')}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "15", //Listener的线程数，建议配置为跟对应的Topic的分区数
            autoStartup = "#{!'${storage.access-kafka.consumer-topics.test}'.isEmpty()}")
//    public void doorControlBatchConsumer(List<ConsumerRecord<byte[], byte[]>> rawMessages) {
    public void doorControlBatchConsumer(ConsumerRecord<byte[], byte[]> rawMessage) {
        try {
            List<byte[]> msgList;
            String s = rawMessage.toString();
            log.info("--------------->data=" + s);
            if (rawMessage.value() != null && rawMessage.value().length > 0) {
                Object obj = byteArrayToObject(rawMessage.value());
                if (obj instanceof List<?>) {
                    msgList = (List<byte[]>) obj;
                    if (msgList != null && msgList.size() > 0) {
                        for (byte[] msg : msgList) {
                            DoorControl wifiData = DoorControl.parseFrom(msg);
                            log.info("--------------->msg=" + wifiData.toString());
                        }
                    }
                }
            }
        } catch (IOException e) {
            log.warn("Access Wifi data exception", e);
        }
    }

    private Object byteArrayToObject(byte[] bytes) {
        Object obj = null;
        if (bytes != null && bytes.length > 0) {
            ByteArrayInputStream in = null;
            ObjectInputStream ois = null;
            try {
                in = new ByteArrayInputStream(bytes);
                ois = new ObjectInputStream(in);
                obj = ois.readObject();
            } catch (Exception e) {
                log.error("解析数据异常:", e);
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    log.error("解析数据异常:", e);
                }
            }
        }
        return obj;
    }

    /**
     * 数组转对象
     *
     * @param bytes
     * @return
     */
    public Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    @KafkaListener(clientIdPrefix = "kafka-vehicle", id = "kafka-vehicle",
            topics = "#{'${storage.access-kafka.consumer-topics.vehicle}'.split(',')}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "10",
            autoStartup = "#{!'${storage.access-kafka.consumer-topics.vehicle}'.isEmpty()}")
    public void vehicleBatchConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        if (!records.isEmpty()) {
            dataRouteService.handleDataWithConsumer(records, TopicEnum.MOTOR_ViHICLE, kafkaPersistenceService, ack, consumer);
        }
    }

    @KafkaListener(clientIdPrefix = "kafka-body", id = "kafka-body",
            topics = "#{'${storage.access-kafka.consumer-topics.body}'.split(',')}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "10",
            autoStartup = "#{!'${storage.access-kafka.consumer-topics.body}'.isEmpty()}")
    public void bodyBatchConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        if (!records.isEmpty()) {
            dataRouteService.handleDataWithConsumer(records, TopicEnum.BODY, kafkaPersistenceService, ack, consumer);
        }
    }

    @KafkaListener(clientIdPrefix = "kafka-non-motor", id = "kafka-non-motor",
            topics = "#{'${storage.access-kafka.consumer-topics.non-motor}'.split(',')}",
            containerFactory = "kafkaListenerContainerFactory",
            concurrency = "10",
            autoStartup = "#{!'${storage.access-kafka.consumer-topics.non-motor}'.isEmpty()}")
    public void nonmotorBatchConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        if (!records.isEmpty()) {
            dataRouteService.handleDataWithConsumer(records, TopicEnum.NON_MOTOR_ViHICLE, kafkaPersistenceService, ack, consumer);
        }
    }
}
