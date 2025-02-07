package br.com.unoesc.desafiodev.repository;

import br.com.unoesc.desafiodev.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByUsuario(String usuario);
}
