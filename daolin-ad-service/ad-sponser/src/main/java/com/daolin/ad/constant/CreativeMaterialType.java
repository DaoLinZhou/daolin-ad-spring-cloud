package com.daolin.ad.constant;

/**
 * @author Daolin
 * @date 2019/04/26
 */
public enum  CreativeMaterialType {

    JPG(1, "jpg"),
    BMP(2, "bmp"),

    MP4(3, "mp4"),
    AVI(4, "avi"),

    TXT(5, "txt");

    private Integer type;
    private String desc;

    CreativeMaterialType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
