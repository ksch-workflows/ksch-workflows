package org.leanhis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"ksch", "org.leanhis"})
@EntityScan("org.leanhis")
@EnableJpaRepositories("org.leanhis")
public class ApplicationTest {
}
