package com.netposa.ips.ga1400.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: jiaobo
 * @Date: 2019/3/28 17:01
 * @Description:图像子对象,城市物联网数据集成标准：3.4.2人脸对象（Person）
 */
@Data
@ToString
public abstract class BodyBase extends ManBase {

    /**
     * 头部标识
     */
    private String headMarker;
    /**
     * 包类型
     */
    private Integer bagType;
    /**
     * 上身纹理
     */
    private Integer upTexture;
    /**
     * 上身颜色
     */
    private Integer upColor;

    /**
     * 下身类型
     */
    private Integer downType;
    /**
     * 下身颜色
     */
    private Integer downColor;


}
