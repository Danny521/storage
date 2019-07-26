package com.netposa.ips.ga1400.domain.common;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: jiaobo
 * @Date: 2019/3/28 17:54
 * @Description:
 */
@Data
@ToString
public abstract class ManBase {
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
     * 左上角y坐标
     */
    private Integer leftTopY;
    /**
     * 左上角X坐标
     */
    private Integer leftTopX;
    /**
     * 右下角X坐标
     */
    private Integer rightBtmX;
    /**
     * 右下角y坐标
     */
    private Integer rightBtmY;
    /**
     * 抓拍时间
     */
    private Long absTime;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 推送时间
     */
    private Long pushTime;
    /**
     * 来源类型
     */
    private Integer sourceType;
    /**
     * 特征图地址
     */
    private String traitImg;
    /**
     * 场景图地址
     */
    private String sceneImg;
    /**
     * 目标消失时间
     */
    private Long endTime;
    /**
     * 目标出现时间
     */
    private Long startTime;
    /**
     * 置信度
     */
    private Double confidence;
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
     * 人脸位置
     */
    private String location;
    /**
     * 比中人员编号
     */
    private String personId;
    /**
     * 比中分数	score
     */
    private Double score;
    /**
     * 人脸扩展位置
     */
    private String traitLocation;
    /**
     * 存储时间
     */
    private Long saveTime;
    /**
     * 颜值
     */
    private Integer attractive;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 年龄
     */
    private String age;
    /**
     * 微笑值
     */
    private Integer smile;
    /**
     * 表情
     */
    private Integer expression;
    /**
     * 是否戴眼镜
     */
    private Integer eyeglass;
    /**
     * 是否戴太阳镜
     */
    private Integer sunglass;
    /**
     * 是否戴口罩
     */
    private Integer mask;
    /**
     * 图像列表（json结构体里为subimageinfo）
     */
    private String subImageList;


}
