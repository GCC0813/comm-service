package com.mall.comm.config.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "alipay")
@PropertySource(value = "classpath:constant-config.properties",encoding="utf-8")
@Data
public class ConstantProperties {

    private String regionId;

    private String accessKeyId;

    private String secret;

    private List<String> accountName;
}
