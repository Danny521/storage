package com.netposa.ips.ga1400.enums;

import lombok.Getter;

/**
 * Author: zhangtao@netposa.com on 2019/3/29
 */
@Getter
public enum TopicEnum {
    /**
     *
     */
    UNKNOWN("unknown", "unknown"),
    TEST("test", "test"),
    FACE("face", "人脸"),
    MOTOR_ViHICLE("", ""),
    NON_MOTOR_ViHICLE("", ""),
    BODY("", "");

    private String name;
    private String description;

    TopicEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static TopicEnum getEnumByName(String name) {
        for (TopicEnum anEnum : TopicEnum.values()) {
            if (anEnum.getName().equals(name)) {
                return anEnum;
            }
        }
        return UNKNOWN;
    }
}
