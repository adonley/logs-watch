package com.lunyr.oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OracleApplication {
    public static void main(String[] args) {
        SpringApplication.run(OracleApplication.class, args);
    }
}
