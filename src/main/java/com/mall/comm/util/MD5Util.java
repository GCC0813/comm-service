package com.mall.comm.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * 密码加密处理（md5）
     *   @param enc 原密码
     *   @return 加密后的内容
     */
    public static String md5Encrypt(String enc) {
        try {
            return new BASE64Encoder().encodeBuffer(enc.getBytes()).trim();
        } catch(Exception e){
            return "";
        }
    }


    /**
     * 密码解密密处理（md5）
     * @param dec 原密码
     * @return 加密后的内容
     */
    public static String md5Decrypt(String dec){
        try {
            return new String(new BASE64Decoder().decodeBuffer(dec)).trim();
        } catch (IOException e) {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(md5Encrypt("4t90MPFFcygHzDS6tgMbXbtEVpvAG6"));;
        System.out.println(md5Decrypt("MTIzNA=="));
    }

}
