package com.ifpb.bancoggi;

import com.ifpb.bancoggi.entidades.Conta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoggiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoggiApplication.class, args);
	}
	Conta conta = new Conta();

}
