package com.web.lab4;

import com.web.lab4.entity.Dot;
import com.web.lab4.entity.User;
import com.web.lab4.repository.DotRepository;
import com.web.lab4.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab4Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, DotRepository dotRepository) {
		return args -> {
			User vasya = new User("Vasya", "qwerty");
			userRepository.save(vasya);
			userRepository.save(new User("Petya", "zalupka"));

			vasya = userRepository.findUserByName("Vasya");
			if (vasya != null) {
				Dot vasyaDot = new Dot(1, 2, 3, '1', "segodnya", vasya);

				dotRepository.save(vasyaDot);
			}



		};
	}

}
