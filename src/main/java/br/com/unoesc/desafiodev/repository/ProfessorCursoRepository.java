package br.com.unoesc.desafiodev.repository;

import br.com.unoesc.desafiodev.model.ProfessorCurso;
import br.com.unoesc.desafiodev.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorCursoRepository extends JpaRepository<ProfessorCurso, Integer> {
    
    List<ProfessorCurso> findByProfessor(Pessoa professor);
}
