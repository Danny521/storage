package com.netposa.ips.ga1400.domain.common;

import lombok.Data;
import lombok.ToString;

/**
 * Author: zhangtao@netposa.com on 2019/3/27
 */
@Data
@ToString
public class NonMotorVehicleBase extends VehicleBase {

    /**
     * 非机动车位置
     */
    private String nonmotorLocation;
    /**
     * 人体头部位置
     */
    private String headLocation;
    /**
     * 车辆标识
     */
    private String nonmotorVehicleId;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 有无车牌
     */
    private Boolean hasPlate;
    /**
     * 非机动车车身颜色
     */
    private Integer nonmotorColor;
    /**
     * 非机动车类型
     */
    private Integer nonmotorType;
    /**
     * 上身类型
     */
    private Integer upType;
    /**
     * 上身颜色
     */
    private Integer upColor;
    /**
     * 上身纹理
     */
    private Integer upTexture;
    /**
     * 下身类型
     */
    private Integer downType;
    /**
     * 下身颜色
     */
    private Integer downColor;
    /**
     * 下身纹理
     */
    private Integer downTexture;
    /**
     * 包类型
     */
    private Integer bagType;
    /**
     * 包颜色
     */
    private Integer bagColor;
    /**
     * 包纹理
     */
    private Integer bagTexture;
    /**
     * 非机动车轨迹
     */
    private String traceInfo;
    /**
     * 人体特征
     */
    private String bodyFeature;
    /**
     * 非机动车出现时间
     */
    private Long startTime;
    /**
     * 非机动车离开时间
     */
    private Long endTime;
    /**
     * 特征图地址
     */
    private String traitImg;
    /**
     * 场景图地址
     */
    private String sceneImg;
    /**
     * 民族
     */
    private Integer ethnic;
    /**
     * 非机动车角度
     */
    private Integer nonmotorAngle;
    /**
     * 载人情况
     */
    private Integer illageBehavior;
    /**
     * 头部标识
     */
    private String headMarker;

}
