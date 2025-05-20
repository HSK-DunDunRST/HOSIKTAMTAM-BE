package com.goormthonuniv.hoseo.hosiktamtam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HosiktamtamApplication {

    public static void main(String[] args) {
        SpringApplication.run(HosiktamtamApplication.class, args);
    }
}
