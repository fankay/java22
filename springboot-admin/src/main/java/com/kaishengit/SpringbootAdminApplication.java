package com.kaishengit;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringbootAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAdminApplication.class, args);
	}
}
