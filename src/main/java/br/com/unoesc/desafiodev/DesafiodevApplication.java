package com.unoesc.desafiodev;

import com.unoesc.desafiodev.principal.Principal;
import com.unoesc.desafiodev.telas.TelaLogin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class DesafiodevApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafiodevApplication.class, args);
	}

	@Override
	public void run(String... args){
		SwingUtilities.invokeLater(() -> {
			new TelaLogin().setVisible(true);
		});

//		Principal principal = new Principal();
//		principal.executar();
	}
}