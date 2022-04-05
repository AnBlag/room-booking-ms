package ru.roombooking.history;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@ComponentScan("ru.roombooking")
@EnableJpaRepositories(basePackages = "ru.roombooking.history.repository")
@EntityScan(basePackages = "ru.roombooking.history.model")
@EnableFeignClients
public class HistoryApplicationTests {

    @Test
    void contextLoads() {
    }
}