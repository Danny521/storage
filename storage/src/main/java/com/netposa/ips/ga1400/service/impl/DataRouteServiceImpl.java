package com.netposa.ips.ga1400.service.impl;

import com.netposa.ips.ga1400.enums.TopicEnum;
import com.netposa.ips.ga1400.service.DataPersistenceService;
import com.netposa.ips.ga1400.service.DataRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: zhangtao@netposa.com on 2019/3/29
 */
@Service
@Slf4j
public class DataRouteServiceImpl implements DataRouteService {

    @Override
    public void handleDataWithConsumer(List<ConsumerRecord<String, String>> records, TopicEnum topicEnum, DataPersistenceService persistenceService, Acknowledgment ack, Consumer<String, String> consumer) {
        switch (topicEnum) {
            case FACE:
                persistenceService.batchSaveFaceWithConsumer(records, ack, consumer);
                break;
            case MOTOR_ViHICLE:
                persistenceService.batchSaveMotorVehicleWithConsumer(records, ack, consumer);
                break;
            case NON_MOTOR_ViHICLE:
                persistenceService.batchSaveNonMotorVehicleWithConsumer(records, ack, consumer);
                break;
            case BODY:
                persistenceService.batchSaveBodyWithConsumer(records, ack, consumer);
                break;
            case UNKNOWN:

                break;
            default:
        }
    }
}
