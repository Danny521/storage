package com.netposa.ips.ga1400.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * Author: zhangtao@netposa.com on 2019/3/25
 */
@Component
@Slf4j
public class KafkaSendResultHandler implements ProducerListener {

    @Override
    public void onSuccess(String s, Integer integer, Object o, Object o2, RecordMetadata recordMetadata) {
//        log.info("Message send success : " + s);
    }

    @Override
    public void onError(String s, Integer integer, Object o, Object o2, Exception e) {
        log.info("Message send failed : " + s);
    }
}
