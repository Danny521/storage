package com.netposa.ips.ga1400.domain.common;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: jiaobo
 * @Date: 2019/3/28 17:01
 * @Description:城市物联网数据集成标准：3.4.3机动车对象（motorvehicle）
 */
@Data
@ToString
public class MotorVehicleBase extends VehicleBase {

    /**
     * 近景照片
     */
    private String storageUrl;
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
     * 车牌左上角y坐标
     */
    private Integer plateLeftTopY;
    /**
     * 车牌左上角X坐标
     */
    private Integer plateLeftTopX;
    /**
     * 车牌右下角X坐标
     */
    private Integer plateRightBtmX;
    /**
     * 车牌右下角y坐标
     */
    private Integer plateRightBtmY;
    /**
     * 车牌置信度
     */
    private Double plateNoConfidence;
    /**
     * 行驶状态
     */
    private String trafficStatus;
    /**
     * 车辆类型
     */
    private String vehicleType;
    /**
     * 车身颜色
     */
    private Integer vehicleColor;
    /**
     * 车辆品牌
     */
    private String vehicleBrand;
    /**
     * 车辆品牌置信度
     */
    private Double vehicleBrandConfidence;
    /**
     * 车辆子品牌
     */
    private String vehicleSubBrand;
    /**
     * 车辆子品牌置信度
     */
    private Double vehicleSubBrandConfidence;
    /**
     * 人脸数
     */
    private Integer faceNum;
    /**
     * 遮阳板
     */
    private Integer sunVisor;
    /**
     * 是否系安全带
     */
    private Integer seatBeltStatus;
    /**
     * 车辆年检标数
     */
    private Integer annualInspectionNum;
    /**
     * 挂件个数
     */
    private Integer pendantsNum;
    /**
     * 摆件个数
     */
    private Integer ornamentsNum;
    /**
     * 车辆年款
     */
    private String vehicleYear;
    /**
     * 纸巾盒数
     */
    private Integer tissueBoxNum;
    /**
     * 是否打电话
     */
    private Integer callStatus;
    /**
     * 违章类型
     */
    private String violationType;
    /**
     * 车辆速度
     */
    private Double vehicleSpeed;
    /**
     * 标志物
     */
    private Integer markerType;
    /**
     * 车头车尾
     */
    private Integer vehicleHeadend;
    /**
     * 是否遮挡面部
     */
    private Integer shieldFace;
    /**
     * 车道编号
     */
    private String drivewayNo;
    /**
     * 车辆位置
     */
    private String vehicleLocation;
    /**
     * 车牌位置
     */
    private String plateLocation;
    /**
     * 主驾遮阳板位置
     */
    private String mainVisorPosition;
    /**
     * 副驾遮阳板位置
     */
    private String viceVisorPosition;
    /**
     * 主驾安全带位置
     */
    private String mainSeatBeltPosition;
    /**
     * 副驾安全带位置
     */
    private String viceSeatBeltPosition;
    /**
     * 年检标位置
     */
    private String annualInspectionPosition;
    /**
     * 挂件位置
     */
    private String pendantLocation;
    /**
     * 纸巾盒位置
     */
    private String tissueBoxLocation;
    /**
     * 驾驶员开车打电话
     */
    private Integer drivingCall;
    /**
     * 全景图片
     */
    private String panoramicPic;
    /**
     * 特殊车辆
     */
    private Integer specialCar;


}
