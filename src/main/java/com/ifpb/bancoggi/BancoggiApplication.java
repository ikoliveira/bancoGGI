package com.ifpb.bancoggi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class BancoggiApplication {

	@Value("${PORT}")
	private static String appPort;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BancoggiApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", appPort));
		app.run(args);
	}

}