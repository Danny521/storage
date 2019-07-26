package com.netposa.ips.ga1400.modules.kafka;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: zhangtao@netposa.com on 2019/3/25
 */
@Slf4j
@Service
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate4Test;

    private static long time = System.currentTimeMillis();

    public void sendMessage(String faceTopic, String msg) throws Exception {
        kafkaTemplate.send(faceTopic, "test", msg).get();
    }

    public void sendMessage4Test(String faceTopic, String msg) {
        if ((System.currentTimeMillis() - time) > 10 * 1000) {
            time = System.currentTimeMillis();
            log.info("----------->msg={}", JSON.toJSONString(msg));
        }
        kafkaTemplate4Test.send(faceTopic, msg);
    }

}
