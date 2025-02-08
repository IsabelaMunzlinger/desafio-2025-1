package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Pessoaendereco;
import br.com.unoesc.desafiodev.repository.PessoaEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaEnderecoService {

    private final PessoaEnderecoRepository pessoaEnderecoRepository;

    @Autowired
    public PessoaEnderecoService(PessoaEnderecoRepository pessoaEnderecoRepository) {
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
    }

    public Pessoaendereco salvarEndereco(Pessoaendereco endereco) {
        return pessoaEnderecoRepository.save(endereco);
    }
}
