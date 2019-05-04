package com.daolin.ad.constant;

import lombok.Getter;

/**
 * @author Daolin
 * @date 2019/04/26
 */
@Getter
public enum CreativeType {

    IMAGE(1, "图片"),
    VIDEO(2, "视频"),
    TEXT(3, "文本");


    private Integer type;
    private String desc;

    CreativeType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
