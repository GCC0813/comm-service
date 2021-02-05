package com.mall.comm.controller;

import com.mall.comm.config.constant.CodeMsg;
import com.mall.comm.service.EmailService;
import com.mall.comm.vo.in.email.SendEmailIn;
import com.mall.comm.vo.out.JsonOut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@RestController
@RequestMapping("email")
public class EmailController {

    @Resource(name = "emailService")
    EmailService emailService;

    @Value("${abc}")
    private String abc ;


    @PostMapping("send")
    public JsonOut<CodeMsg> sendEmail(SendEmailIn in){
        emailService.sendEmail();
        return JsonOut.ok(CodeMsg.CODE_200);
    }

    @PostConstruct
    public void main() throws InterruptedException {
        while (true){
            Thread.sleep(1000);
            System.out.println("我在这里呀->>>"+ abc);
        }
    }
}
