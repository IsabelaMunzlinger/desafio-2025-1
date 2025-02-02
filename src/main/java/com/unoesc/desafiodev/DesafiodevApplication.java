package com.unoesc.desafiodev;

import com.unoesc.desafiodev.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafiodevApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafiodevApplication.class, args);
	}

	@Override
	public void run(String... args){
		Principal principal = new Principal();
		principal.executar();
	}
}