package ru.roombooking.employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@ComponentScan("ru.roombooking")
@EnableJpaRepositories(basePackages = "ru.roombooking.employees.repository")
@EntityScan(basePackages = "ru.roombooking.employees.model")
public class EmployeeApplicationTests {

	@Test
	void contextLoads() {
	}

}
