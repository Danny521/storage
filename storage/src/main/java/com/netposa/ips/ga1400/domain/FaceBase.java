package com.netposa.ips.ga1400.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: jiaobo
 * @Date: 2019/3/28 17:01
 * @Description:图像子对象,城市物联网数据集成标准：3.4.1人脸对象（Face）
 */
@Data
@ToString
public abstract class FaceBase extends ManBase {

    /**
     * 比中人员编号
     */
    private String personId;

    /**
     * 比中分数	score
     */
    private Double score;
    /**
     * 主副驾标识
     */
    private Integer driverFlag;
    /**
     * VID可读编号
     */
    private String vidNum;
    /**
     * 身份是否确认
     */
    private Integer isIdentify;
    /**
     * 删除标记
     */
    private Integer deleteFlag;


}
