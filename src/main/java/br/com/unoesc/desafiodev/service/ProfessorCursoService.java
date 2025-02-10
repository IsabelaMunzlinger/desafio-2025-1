package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.model.Curso;
import br.com.unoesc.desafiodev.model.ProfessorCurso;
import br.com.unoesc.desafiodev.repository.ProfessorCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;


@Service
public class ProfessorCursoService {
    
    @Autowired
    private ProfessorCursoRepository professorCursoRepository;

    public List<Curso> obterCursosPorProfessor(Pessoa professor) {
        List<ProfessorCurso> professorCursos = professorCursoRepository.findByProfessor(professor);
        List<Curso> cursos = new ArrayList<>();
        for (ProfessorCurso professorCurso : professorCursos) {
            cursos.add(professorCurso.getCurso());  // Pega os cursos associados ao professor
        }
        return cursos;
    }

        // Vincula um professor a um curso
        public void vincularProfessorAoCurso(Pessoa professor, Curso curso) {
            if (curso.getSituacao().equals("ATIVO")) {
                ProfessorCurso professorCurso = new ProfessorCurso();
                professorCurso.setProfessor(professor);
                professorCurso.setCurso(curso);
                professorCursoRepository.save(professorCurso);
            }
        }
    
}
