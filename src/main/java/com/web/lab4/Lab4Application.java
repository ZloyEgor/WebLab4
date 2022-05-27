package com.web.lab4;

import com.web.lab4.entity.Dot;
import com.web.lab4.entity.User;
import com.web.lab4.repository.DotRepository;
import com.web.lab4.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Lab4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab4Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, DotRepository dotRepository) {
		return args -> {
			User vasya = new User("Vasya", "$2a$10$nMEenu6y4GpjjqAGKSGrwu9hNvteArdAMiqr2Ql4kXqBXamTXroEC");
			vasya = userRepository.save(vasya);
			User petya = userRepository.save(new User("Petya", "$2a$10$r0uPl3MHLn/tnZXY723.e.wfdbNdLiiJr1SnbgDJs45DXA/ocN6xi"));

			Dot vasyaDot1 = new Dot(1, 2, 3, '1', "segodnya", vasya);
			Dot vasyaDot2 = new Dot(7, 7, 7, '0', "vchera", vasya);
			Dot petyaDot = new Dot(0, 1, 0, '1', "chto-to tam", petya);
			dotRepository.save(vasyaDot1);
			dotRepository.save(vasyaDot2);
			dotRepository.save(petyaDot);

		};
	}

}
