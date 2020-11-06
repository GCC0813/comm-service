package com.mall.comm.constant;

/**
 * @author haonan
 * create on 2020/3/9 10:57
 */
public class GlobalVar {

    //手机号正则
    public static final String PHONE_REGULAR = "^(13[0-9]|14[5|7|9]|15[0|1|2|3|5|6|7|8|9]|16[6]|17[0|1|2|3|5|6|7|8]|18[0-9]|19[8|9])\\d{8}$";

    //邮箱正则
    public static final String MAIL_REGULAR = "^[0-9A-Za-z][\\.-_0-9A-Za-z]*@[0-9A-Za-z]+(?:\\.[0-9A-Za-z]+)+$";

    public static final String STATIC_RESOURCES_PREFIX= "http://static.gcc666.top/%s";

    public static final String DEFAULT_HEAD="default_head.jpg";

    public static final  String GOODS_REDIS_KEY = "goodsId_key_%s";

    public static final String STATIC_RESOURCES_PREFIX_TWO = "http://static.gcc666.top/";


}
