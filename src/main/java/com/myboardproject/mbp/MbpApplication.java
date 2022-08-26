package com.myboardproject.mbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MbpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbpApplication.class, args);
	}

}
