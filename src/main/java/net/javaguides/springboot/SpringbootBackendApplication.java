package net.javaguides.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class SpringbootBackendApplication {
	@CrossOrigin("http://localhost:4200")
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

}
