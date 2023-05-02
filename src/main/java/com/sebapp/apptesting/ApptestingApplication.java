package com.sebapp.apptesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ApptestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApptestingApplication.class, args);
    }

}
