package com.example.mydegign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.example.mydegign.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class MydegignApplication {

    public static void main(String[] args) {
        SpringApplication.run(MydegignApplication.class, args);
    }

}
