package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Curso;
import br.com.unoesc.desafiodev.model.EstudanteCurso;
import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.repository.CursoRepository;
import br.com.unoesc.desafiodev.repository.EstudanteCursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudanteCursoService {

    @Autowired
    private EstudanteCursoRepository estudanteCursoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    /*
     * Obtém os cursos disponíveis para o estudante, ou seja, os cursos com status
     * 'ATIVO'.
     */
    public List<Curso> getCursosDisponiveis() {
        return cursoRepository.findBySituacao("ATIVO");
    }

    /**
     * Obtém os cursos em que o estudante está matriculado.
     */

    public List<Curso> getCursosDoEstudante(Integer estudanteId) {
        return estudanteCursoRepository.findCursosByEstudanteId(estudanteId);
    }

    /**
     * Realiza a inscrição do estudante em um curso, caso o curso esteja disponível
     * (status 'ATIVO').
     */
    public String matricularEstudante(Pessoa estudante, Curso curso) {
        if (!curso.getSituacao().equals("ATIVO")) {
            return "O curso não está disponível para matrícula.";
        }

        // Verifica se o estudante já está matriculado no curso
        Optional<EstudanteCurso> estudanteCursoExistente = estudanteCursoRepository.findByEstudanteAndCurso(estudante,
                curso);
        if (estudanteCursoExistente.isPresent()) {
            return "O estudante já está matriculado neste curso.";
        }

        EstudanteCurso novoEstudanteCurso = new EstudanteCurso();
        novoEstudanteCurso.setCurso(curso);
        novoEstudanteCurso.setEstudante(estudante);
        estudanteCursoRepository.save(novoEstudanteCurso);

        return "Matrícula realizada com sucesso.";
    }
}
