package com.itstyle;

import com.itstyle.common.properties.AppProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
@MapperScan({"com.itstyle.dao"})
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(new Class[]{Application.class});
        application.run(args);
    }
}