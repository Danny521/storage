package com.netposa.ips.ga1400.domain.common;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: jiaobo
 * @Date: 2019/4/2 09:59
 * @Description:
 */
@Data
@ToString
public abstract class VehicleBase {

    /**
     * 记录编号
     */
    private String recordId;
    /**
     * 业务ID
     */
    private String businessId;
    /**
     * 信息分类
     */
    private Integer infoKind;
    /**
     * 来源标识
     */
    private String sourceId;
    /**
     * 抓拍时间
     */
    private Long absTime;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 推送时间    (数据入kafka时间	dataInKafkaTime)
     * 企标文档为数据入Kafka时间，这里修改为pushtime
     */
    private Long pushTime;
    /**
     * 来源类型
     */
    private Integer sourceType;

    /**
     * 入库时间	entryTime
     */
    private Long entryTime;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 数据来源厂商
     */
    private String infoSource;
    /**
     * 算法版本
     */
    private String algorithmVersion;
    /**
     * 算法厂商
     */
    private String algorithmVendor;
    /**
     * 特征向量
     */
    private String eigenvector;
    /**
     * 是否二次结构化
     */
    private Integer isSecondaryStructure;
    /**
     * 结构化时间
     */
    private Long structureTime;
    /**
     * 全目标关联关系ID
     */
    private String collectionId;
    /**
     * 渗透字段信息
     */
    private String additionalInfo;
    /**
     * 数据来源	source
     */
    private Integer source;
    /**
     * 图像列表（json结构体里为subimageinfo）
     */
    private String subImageList;
    /**
     * 号牌号码	plateNo
     */
    private String plateNo;
    /**
     * 号牌种类
     */
    private String plateClass;
    /**
     * 移动方向
     */
    private Integer moveDirection;
    /**
     * 移动速度
     */
    private Integer moveSpeed;
    /**
     * 车牌颜色
     */
    private Integer plateColor;


}
