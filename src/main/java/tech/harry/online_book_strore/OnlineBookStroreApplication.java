package tech.harry.online_book_strore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OnlineBookStroreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineBookStroreApplication.class, args);
		System.out.println("this is online_store2....... ");
		System.out.println("this is online book store application");
		System.out.println("recursive use");
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
