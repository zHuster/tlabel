package com.csdc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@MapperScan("com.csdc.mapper")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling  //开启定时器
public class TlabelApplication {

    public static void main(String[] args) {
        SpringApplication.run(TlabelApplication.class, args);
    }


}
