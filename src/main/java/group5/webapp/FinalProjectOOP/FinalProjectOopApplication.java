package group5.webapp.FinalProjectOOP;

import group5.webapp.FinalProjectOOP.models.User;
import group5.webapp.FinalProjectOOP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class FinalProjectOopApplication {
	public static void main(String[] args) {
		SpringApplication.run(FinalProjectOopApplication.class, args);
	}

}
