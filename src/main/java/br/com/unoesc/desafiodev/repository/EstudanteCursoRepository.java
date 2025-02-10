package br.com.unoesc.desafiodev.repository;

import br.com.unoesc.desafiodev.model.Curso;
import br.com.unoesc.desafiodev.model.EstudanteCurso;
import br.com.unoesc.desafiodev.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstudanteCursoRepository extends JpaRepository<EstudanteCurso, Integer> {

    List<Curso> findCursosByEstudante(Pessoa estudante);

    Optional<EstudanteCurso> findByEstudanteAndCurso(Pessoa estudante, Curso curso);

    @Query("SELECT ec.curso FROM EstudanteCurso ec WHERE ec.estudante.id = :estudanteId")
    List<Curso> findCursosByEstudanteId(@Param("estudanteId") Integer estudanteId);


}
