package com.example.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.example.admin")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        log.info(" admin service started! ");
    }

}
