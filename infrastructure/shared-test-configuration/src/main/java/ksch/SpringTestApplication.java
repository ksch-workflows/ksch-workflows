package ksch;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * By having this class on the class path during the execution of the Spring Boot tests, the Spring boot application
 * context is configured with the application's Spring Beans.
 */
@SpringBootApplication
@ComponentScan("ksch")
@EntityScan("ksch")
@EnableJpaRepositories("ksch")
public class SpringTestApplication {
}
