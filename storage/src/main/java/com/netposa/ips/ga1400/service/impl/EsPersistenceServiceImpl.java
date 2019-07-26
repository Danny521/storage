package com.netposa.ips.ga1400.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netposa.ips.ga1400.constants.StorageConsts;
import com.netposa.ips.ga1400.service.DataPersistenceService;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
@Service
@Slf4j
public class EsPersistenceServiceImpl implements DataPersistenceService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private static long t1 = System.currentTimeMillis();

    @Override
    @Synchronized
    public void batchSaveFaceWithConsumer(List<ConsumerRecord<String, String>> records, Acknowledgment ack, Consumer<String, String> consumer) {
        try {
            int counter = 0;
            List<IndexQuery> queries = new ArrayList<>();
            for (ConsumerRecord<String, String> record : records) {
                JSONObject josn = JSON.parseObject(record.value());
                JSONArray jsonArray = josn.getJSONObject("FaceListObject").getJSONArray("FaceObject");//此处需要考虑是否从kafka拉出的数据是多条的
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject faceObj = jsonArray.getJSONObject(i);
                    String faceID = faceObj.getString("FaceID");
                    IndexQuery indexQuery = new IndexQuery();
                    indexQuery.setId(faceID); //每条数据的唯一标识
                    JSONObject resJson = new JSONObject();
                    resJson.put("deviceId", faceObj.getString("DeviceID"));//设备ID
                    resJson.put("time", faceObj.getString("ShotTime")); //
                    indexQuery.setSource(resJson.toString());
                    indexQuery.setIndexName(StorageConsts.ES_INDEX_NAME);
                    indexQuery.setType(StorageConsts.ES_INDEX_TYPE_FACE);
                    queries.add(indexQuery);
                    //分批提交索引
                    if (counter % 500 == 0) {
                        elasticsearchTemplate.bulkIndex(queries);
                        queries.clear();
                        System.out.println("bulkIndex counter : " + counter);
                    }
                    counter++;
                    //不足批的索引最后不要忘记提交
                    if (queries.size() > 0) {
                        elasticsearchTemplate.bulkIndex(queries);
                    }
                }

            }
            elasticsearchTemplate.refresh(StorageConsts.ES_INDEX_NAME);
        } catch (Exception e) {
            log.error("------------bulkIndex e;" + e.getMessage());
        }
        t1 = System.currentTimeMillis();
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
}
