package com.netposa.ips.ga1400.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netposa.ips.ga1400.util.CommonUtils;
import com.netposa.ips.ga1400.domain.mysql.Face;
import com.netposa.ips.ga1400.mapper.mysql.MysqlFaceMapper;
import com.netposa.ips.ga1400.service.DataPersistenceService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
@Service
@Slf4j
public class MysqlPersistenceServiceImpl implements DataPersistenceService {

    @Resource
    private MysqlFaceMapper faceMapper;

    @Override
    @Synchronized
    public void batchSaveFaceWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        if (!addFace(records)) {
            consumer.close();
            return;
        }
        consumer.commitSync();
    }

    @Override
    @Synchronized
    public void batchSaveMotorVehicleWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {

    }

    @Override
    @Synchronized
    public void batchSaveNonMotorVehicleWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {

    }

    @Override
    @Synchronized
    public void batchSaveBodyWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {

    }

    private boolean addFace(List<ConsumerRecord<String, String>> records) {
        boolean flag = true;
        List<Face> faces = new ArrayList<>();
        Long absTime = System.currentTimeMillis();
        for (ConsumerRecord<String, String> record : records) {
            try {
                JSONArray array = JSONObject.parseObject(record.value()).getJSONObject("FaceListObject").getJSONArray("FaceObject");
                for (int i = 0; i < array.size(); i++) {
                    Face face = JSONObject.toJavaObject(array.getJSONObject(i), Face.class);
                    face.setRecordId(CommonUtils.createUUID(true, false));
                    face.setAbsTime(absTime);
                    faces.add(face);
                }
            } catch (Exception e) {
                log.error("----------------->Json解析错误: {}", record.value());
            }
        }

        try {
            if (!CommonUtils.arrayIsNullOrEmpty(faces)) {
                faceMapper.batchInsert(faces);
            }
        } catch (Exception e) {
            flag = false;
            log.error("----------------->批量插入失败", e);
        }

        return flag;
    }
}
