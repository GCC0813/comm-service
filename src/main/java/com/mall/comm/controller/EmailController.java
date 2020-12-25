package com.mall.comm.controller;

import com.mall.comm.config.constant.CodeMsg;
import com.mall.comm.service.EmailService;
import com.mall.comm.vo.in.email.SendEmailIn;
import com.mall.comm.vo.out.JsonOut;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("email")
public class EmailController {

    @Resource(name = "emailService")
    EmailService emailService;


    @PostMapping("send")
    public JsonOut<CodeMsg> sendEmail(SendEmailIn in){
        emailService.sendEmail();
        return JsonOut.ok(CodeMsg.CODE_200);
    }



}
