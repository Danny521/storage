package com.netposa.ips.ga1400.domain.common;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Auther: jiaobo
 * @Date: 2019/3/28 17:01
 * @Description:图像子对象,城市物联网数据集成标准：3.4.7图像子对象（SubImageInfo）
 */
@Data
@ToString
public class SubImageInfoObject {
    private List<SubImageInfo> SubImageInfoObject;

    @Data
    @ToString
    public static class SubImageInfo {
        //图像标识
        private String imageId;
        //设备编码
        private String deviceId;
        //图像来源
        private String imageSource;
        //题名
        private String title;
        //图片数据
        private byte[] imageData;
        //图像文件格式
        private String fileFormat;
        //拍摄地点区划内详细地址
        private String shotPlaceFullAdress;
        //拍摄时间
        private String shotTime;
        //事件分类
        private Integer eventSort;
        //图像类型
        private Integer imageType;
        //信息分类
        private Integer infoKind;
        //存储路径
        private String storagePath;
        //内容描述
        private String contentDescription;
    }
}
