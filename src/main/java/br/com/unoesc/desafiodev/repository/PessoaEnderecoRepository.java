package br.com.unoesc.desafiodev.repository;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.model.Pessoaendereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
Procura o endereço de uma pessoa, através da classe pessoa
 */

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<Pessoaendereco, Integer> {
    Optional<Pessoaendereco> findByPessoa (Pessoa pessoa);
}
