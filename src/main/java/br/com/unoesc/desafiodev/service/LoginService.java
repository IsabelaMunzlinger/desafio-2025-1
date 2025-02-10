package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    public LoginService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    private PessoaService pessoaService;

    public Pessoa login(String usuario, String senha) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findByUsuario(usuario);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            // Verifica se a senha informada é igual a senha cadastrada
            if (pessoa.getSenha().equals(senha)) {
                System.out.println(" Login bem-sucedido! Bem-vindo, " + pessoa.getNome() + "!");
                return pessoa;
            }
        }
        return null;
    }
}
