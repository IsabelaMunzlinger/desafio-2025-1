package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.model.PessoaEndereco;
import br.com.unoesc.desafiodev.repository.PessoaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class PessoaEnderecoService {

    @Autowired
    private PessoaEnderecoRepository pessoaEnderecoRepository;

    private final Scanner leitura = new Scanner(System.in);

    // Solicita os dados de endereço de uma pessoa
    public void solicitarEndereco(Pessoa pessoa) {
        PessoaEndereco endereco = new PessoaEndereco();

        System.out.print("Cidade: ");
        endereco.setCidade(leitura.nextLine());

        System.out.print("CEP: ");
        endereco.setCep(leitura.nextLine());

        System.out.print("Rua: ");
        endereco.setRua(leitura.nextLine());

        System.out.print("Número: ");
        endereco.setNumero(leitura.nextInt());
        leitura.nextLine(); // Consumir a quebra de linha

        endereco.setPessoa(pessoa);
        pessoa.setPessoaEndereco(endereco);

        pessoaEnderecoRepository.save(endereco); // Salva no banco
    }
}
