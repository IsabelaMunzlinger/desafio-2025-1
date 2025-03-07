package br.com.unoesc.desafiodev.repository;

import br.com.unoesc.desafiodev.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    List<Curso> findBySituacao(String situacao);
}

