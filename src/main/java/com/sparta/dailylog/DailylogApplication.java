package com.sparta.dailylog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DailylogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailylogApplication.class, args);
    }

}
