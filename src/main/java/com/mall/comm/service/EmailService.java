package com.mall.comm.service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.mall.comm.config.constant.ConstantProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static com.mall.comm.util.MD5Util.md5Decrypt;

@Service("emailService")
public class EmailService {

    @Autowired
    private ConstantProperties constantProperties;


    public void sendEmail() {
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的”cn-hangzhou”替换为”ap-southeast-1”、或”ap-southeast-2”。
        IClientProfile profile = DefaultProfile.getProfile(constantProperties.getRegionId(), md5Decrypt(constantProperties.getAccessKeyId()), md5Decrypt(constantProperties.getSecret()));
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion(“2017-06-22”);// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("service@mail.gcc666.top");
            request.setFromAlias("旺仔牛奶不加糖");
            request.setAddressType(1);
            //request.setTagName("控制台创建的标签");
            request.setReplyToAddress(true);
            request.setToAddress("350929819@qq.com,gcc0813@163.com");
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress(“邮箱1,邮箱2”);
            request.setSubject("测试一下");
            //如果采用byte[].toString的方式的话请确保最终转换成utf-8的格式再放入htmlbody和textbody，若编码不一致则会被当成垃圾邮件。
            //注意：文本邮件的大小限制为3M，过大的文本会导致连接超时或413错误
            request.setHtmlBody("如果回忆容易 ，我会想你念你，");
            //SDK 采用的是http协议的发信方式, 默认是GET方法，有一定的长度限制。
            //若textBody、htmlBody或content的大小不确定，建议采用POST方式提交，避免出现uri is not valid异常
            request.setMethod(MethodType.POST);
            //开启需要备案，0关闭，1开启
            //request.setClickTrace(“0”);
            //如果调用成功，正常返回httpResponse；如果调用失败则抛出异常，需要在异常中捕获错误异常码；错误异常码请参考对应的API文档;
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            //捕获错误异常码
            System.out.println("ErrCode:"+e.getErrCode());
            e.printStackTrace();
        } catch (ClientException e) {
            //捕获错误异常码
            System.out.println("ErrCode:"+e.getErrCode());
            e.printStackTrace();
        }
    }

    //@PostConstruct
    public void main(){
        sendEmail();
    }


    public static void main(String[] args) {
        int a = 1;
        List<String> b = new ArrayList<>();

        for(int c=0;c<3;c++){
            for (; a<b.size()+2;a++){
                if(a/3!=0){
                    b.add("123");
                }
            }
        }
        System.out.println(a);
        System.out.println(b);
    }
}

