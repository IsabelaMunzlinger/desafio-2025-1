package br.com.unoesc.desafiodev.repository;

import br.com.unoesc.desafiodev.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByUsuario(String usuario);
}


