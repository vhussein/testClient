package com.azlan.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
public class TestMainApplication {

    public static void main(String[] args){
        log.debug("This is a test client");
        SpringApplication.run(TestMainApplication.class, args);
    }
}
