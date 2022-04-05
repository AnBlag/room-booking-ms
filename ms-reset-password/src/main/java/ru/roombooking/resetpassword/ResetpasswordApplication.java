package ru.roombooking.resetpassword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ResetpasswordApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResetpasswordApplication.class, args);
    }

}