package com.ktu.haewooso_ver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HaewoosoVer2Application {

    public static void main(String[] args) {
        SpringApplication.run(HaewoosoVer2Application.class, args);
    }

}
