package com.mall.comm.vo.in.email;

import lombok.Data;

@Data
public class SendEmailIn {

    /**
     * 主题
     */
    private String subject;

    /**
     * 发信地址，多个用，隔开
     */
    private String toAddress;
    
}
