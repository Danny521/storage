package com.netposa.ips.ga1400.schedules;

import com.alibaba.fastjson.JSON;
import com.netposa.ips.ga1400.domain.test.User;
import com.netposa.ips.ga1400.enums.TopicEnum;
import com.netposa.ips.ga1400.mapper.mysql.UserMapper;
import com.netposa.ips.ga1400.modules.kafka.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Author: zhangtao@netposa.com on 2019/3/14
 */
@Component
@Slf4j
public class Schedule {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Resource
    private UserMapper userMapper;

    private static Long count = 0L;
    private static Long count1 = 0L;
    private static Long count2 = 0L;
    private static Long count3 = 0L;

    private static Long t1 = System.currentTimeMillis();

    //    @Scheduled(cron = "0/5 * * * * ?")
    public void save2Mysql() {
        count++;
        User user = new User();
        user.setStatus(RandomUtils.nextInt(0, 2));
        user.setName("testName_" + count);
        user.setGender(RandomUtils.nextInt(0, 2));
        user.setCreateTime(new Date());
        user.setDepartment("this is " + count);
        userMapper.insert(user);
    }

    //    @Scheduled(fixedRate = 5)
    public void testMsg() {
        count1++;
        User user = new User();
        user.setName(System.currentTimeMillis() + "");
        user.setGender(RandomUtils.nextInt(0, 2));
        user.setCreateTime(new Date());
        user.setStatus(RandomUtils.nextInt(0, 1));
        user.setDepartment("this is " + count1);
        try {
//            log.info("------->send msg={}",JSON.toJSONString(user));
            kafkaProducer.sendMessage4Test("test-topic", JSON.toJSONString(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @Scheduled(fixedRate = 2)
    public void testMsg3() {
        count3++;
        User user = new User();
        user.setName(System.currentTimeMillis() + "");
        user.setGender(RandomUtils.nextInt(0, 2));
        user.setCreateTime(new Date());
        user.setStatus(RandomUtils.nextInt(0, 1));
        user.setDepartment("this is " + count3);
        try {
//            log.info("------->send msg={}",JSON.toJSONString(user));
            kafkaProducer.sendMessage4Test("mt", JSON.toJSONString(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @Scheduled(fixedRate = 10)
    public void testMsg2() {
        count++;
        User user = new User();
        user.setName(System.currentTimeMillis() + "");
        user.setGender(RandomUtils.nextInt(0, 2));
        user.setCreateTime(new Date());
        user.setStatus(RandomUtils.nextInt(0, 1));
        user.setDepartment("this is " + count);
        try {
//            log.info("------->send msg={}",JSON.toJSONString(user));
            kafkaProducer.sendMessage4Test("user-demo", JSON.toJSONString(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @KafkaListener(clientIdPrefix = "test-topic", id = "zt-group",
//            topics = {"test-topic", "user-demo","user-demo2","mt"},
//            containerFactory = "kafkaListenerContainerFactory",
//            concurrency = "3", //Listener的线程数，建议配置为跟对应的Topic的分区数
//            autoStartup = "true")
    public void faceBatchConsumer(String record) {
        if ((System.currentTimeMillis() - t1) > 3 * 1000) {
            t1 = System.currentTimeMillis();
            log.info("------receive record info={}", record);
        }
    }

    //    @Scheduled(fixedRate = 1)
    public void test2Msg() {
//        while (true){
        count2++;
        User user = new User();
        user.setName(System.currentTimeMillis() + "");
        user.setGender(RandomUtils.nextInt(0, 2));
        user.setStatus(RandomUtils.nextInt(0, 1));
        user.setCreateTime(new Date());
        user.setDepartment("this is " + count2);
        try {
//                log.info("------->send msg={}", JSON.toJSONString(user));
//                kafkaProducer.sendMessage4Test("user-demo", JSON.toJSONString(user));
            kafkaProducer.sendMessage4Test("user-demo2", JSON.toJSONString(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        }
    }
}
