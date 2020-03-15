package eventsg.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * A class representing the Spring Boot Application.
 */
@SpringBootApplication
public class BackendApplication {

	/**
	 * A private constructor that's never called.
	 */
	private BackendApplication() { }

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
