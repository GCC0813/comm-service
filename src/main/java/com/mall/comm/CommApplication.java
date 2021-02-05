package com.mall.comm;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableApolloConfig
public class CommApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommApplication.class, args);
    }

}
