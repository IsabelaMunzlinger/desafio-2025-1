package br.com.unoesc.desafiodev;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafiodevApplication implements CommandLineRunner {

	@Autowired
	private Principal principal;

	Pessoa pessoa = new Pessoa();

	public static void main(String[] args) {
		SpringApplication.run(DesafiodevApplication.class, args);
	}


	@Override
	public void run(String... args) {
		System.out.println("Aplicação iniciada! Banco de dados conectado com sucesso.");
		principal.login();
		principal.exibirMenu(pessoa);
	}
}

