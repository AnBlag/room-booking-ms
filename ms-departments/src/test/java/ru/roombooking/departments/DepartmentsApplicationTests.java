package ru.roombooking.departments;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootTest
@ComponentScan("ru.roombooking")
@EnableJpaRepositories(basePackages = "ru.roombooking.departments.repository")
@EntityScan(basePackages = "ru.roombooking.departments.model")
public class DepartmentsApplicationTests {

	@Test
	void contextLoads() {
	}

}
