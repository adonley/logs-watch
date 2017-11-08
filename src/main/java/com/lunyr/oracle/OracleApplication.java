package com.lunyr.oracle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@EnableJpaRepositories
@SpringBootApplication
public class OracleApplication {
    public static void main(String[] args) {
        SpringApplication.run(OracleApplication.class, args);
    }
}
