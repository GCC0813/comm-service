package com.mall.comm.config.constant;

import lombok.Getter;

/**
 * @author haonan
 * create on 2020/1/8 20:00
 */
@Getter
public enum  CodeMsg {

    CODE_200(200, "成功"),

    SYSTEM_ERROR(500,"抱歉 系统开小差了！"),


    ;
    private final int code;

    private final String msg;

    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
