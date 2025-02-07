package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class PessoaService {
    private final Scanner leitura = new Scanner(System.in);
    //private final PessoaRepository pessoaRepository;

    @Autowired
    private final PessoaRepository pessoaRepository;

    public Pessoa adicionarPessoa(Pessoa pessoa) {

        System.out.println("CPF antes de salvar: " + pessoa.getCpf());
        return pessoaRepository.save(pessoa); // Salva no banco
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }


    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void solicitarDadosManuais(Pessoa pessoa) {
        pessoa.setCpf(validarCPF());

        System.out.print("✅ A pessoa está ativa? (S/N): ");
        pessoa.setAtivo(leitura.nextLine().trim().equalsIgnoreCase("S"));

        System.out.print("👤 Digite o nome de usuário: ");
        pessoa.setUsuario(leitura.nextLine());

        System.out.print("🔑 Digite a senha: ");
        pessoa.setSenha(leitura.nextLine());

        pessoaRepository.save(pessoa);
        System.out.println("✅ Pessoa salva com sucesso!");
    }

    private String validarCPF() {
        String cpf;
        do {
            System.out.print("🔢 Digite o CPF (somente números): ");
            cpf = leitura.nextLine().trim();
            if (!cpf.matches("\\d{11}")) {
                System.out.println("❌ CPF inválido! Digite 11 números.");
            }
        } while (!cpf.matches("\\d{11}"));
        return cpf;
    }

    public Optional<Pessoa> buscarPorUsuario(String usuario) {
        return pessoaRepository.findByUsuario(usuario);
    }
}

