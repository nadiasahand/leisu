package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.mycompany.api", "com.mycompany.database"}, exclude = {SecurityAutoConfiguration.class})
public class LeisuApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeisuApiApplication.class, args);
    }

}
