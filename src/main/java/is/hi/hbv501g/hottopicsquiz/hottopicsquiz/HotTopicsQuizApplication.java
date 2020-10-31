package is.hi.hbv501g.hottopicsquiz.hottopicsquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories
public class HotTopicsQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotTopicsQuizApplication.class, args);
	}
	
}
