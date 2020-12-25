package com.mall.comm.service;

import org.apache.commons.lang3.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class demo2 {
    private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
    private static final String ALIDM_SMTP_PORT = "25";//或"80"
    public static void main(String[] args) {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.port", "465");
        // 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
        props.put("mail.user", "gcc@mail.gcc666.top");
        // 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
        props.put("mail.password", "HaoNan3509");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
//        mailSession.setDebug(true);
        //UUID uuid = UUID.randomUUID();
        //final String messageIDValue = "<" + uuid.toString() + ">";
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession){
            //@Override
            //protected void updateMessageID() throws MessagingException {
            //设置自定义Message-ID值
            //setHeader("Message-ID", messageIDValue);
            //}
        };
        try {
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress(props.getProperty("mail.user"), "旺仔牛奶不加糖");
            message.setFrom(from);
            //可选。设置回信地址
            Address[] a = new Address[1];
            a[0] = new InternetAddress("gcc0813@163.com");
            message.setReplyTo(a);
            // 设置收件人邮件地址，比如yyy@yyy.com
            InternetAddress to = new InternetAddress("jianeng0922@163.com");
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            //如果同时发给多人，才将上面两行替换为如下（因为部分收信系统的一些限制，尽量每次投递给一个人；同时我们限制单次允许发送的人数是30人）：
            //InternetAddress[] adds = new InternetAddress[2];
            //adds[0] = new InternetAddress("xxxxx@qq.com");
            //adds[1] = new InternetAddress("xxxxx@qq.com");
            //message.setRecipients(Message.RecipientType.TO, adds);
            String ccUser = "";
            // 设置多个抄送地址
            if(StringUtils.isNotBlank(ccUser)){
                @SuppressWarnings("static-access")
                InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);
                message.setRecipients(Message.RecipientType.CC, internetAddressCC);
            }
            String bccUser = "";
            // 设置多个密送地址
            if(StringUtils.isNotBlank(bccUser)){
                @SuppressWarnings("static-access")
                InternetAddress[] internetAddressBCC = new InternetAddress().parse(bccUser);
                message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
            }
            // 设置邮件标题
            message.setSubject("测试邮件");

            /************************************************************/
            // 设置邮件的内容体
            Multipart multipart = new MimeMultipart();
            BodyPart html = new MimeBodyPart();
            html.setContent(
                    "<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <div>\n" +
                            "        <span>\n" +
                            "            您好，您在GoodSongs兑换的歌曲‘爱在西元前’，歌曲相关音频下载地址为‘\n" +
                            "            <a href=\"http://www.goodsongs.cn/21iuhqqeqwiu\">www.goodsongs.cn/21iuhqqeqwiu</a>\n" +
                            "            ’，请尽快下载，链接在30日后失效\n" +
                            "        </span>\n" +
                            "    </div>\n" +
                            "</body>\n" +
                            "</html>","text/html;charset=UTF-8");
            multipart.addBodyPart(html);
            message.setContent(multipart);
            /**********************************************************/

            //message.setContent("测试的HTML邮件", "text/html;charset=UTF-8");
            //若需要开启邮件跟踪服务，请使用以下代码设置跟踪链接头。首先域名需要备案，设置且已正确解析了CNAME配置；其次发信需要打Tag，此Tag在控制台已创建并存在，Tag创建10分钟后方可使用；
            //String tagName = "Test";
            //HashMap<String, String> trace = new HashMap<>();
            //trace.put("OpenTrace", "1");
            //trace.put("TagName", tagName);
            //String jsonTrace = JSON.toJSONString(trace);
            //String base64Trace = new String(Base64.encodeBase64(jsonTrace.getBytes()));
            //设置跟踪链接头
            //message.addHeader("X-AliDM-Trace", base64Trace);
            // 发送附件，总的邮件大小不超过15M，创建消息部分
           /* BodyPart messageBodyPart = new MimeBodyPart();
            // 消息
            messageBodyPart.setText("消息Text");
            // 创建多重消息
            Multipart multipart = new MimeMultipart();
            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);
            // 附件部分
            messageBodyPart = new MimeBodyPart();
            //设置要发送附件的文件路径
            String filename = "C:\\newbee_mall_schema.sql";
            FileDataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            //处理附件名称中文（附带文件路径）乱码问题
            messageBodyPart.setFileName(MimeUtility.encodeText(filename));
            messageBodyPart.addHeader("Content-Transfer-Encoding", "base64");
            multipart.addBodyPart(messageBodyPart);
            // 发送含有附件的完整消息
            message.setContent(multipart);*/
            // 发送附件代码，结束
            // 发送邮件
            Transport.send(message);
        }
        catch (MessagingException | UnsupportedEncodingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        }
    }
}
