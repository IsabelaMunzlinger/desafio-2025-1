package br.com.unoesc.desafiodev.controller;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.model.Curso;
import br.com.unoesc.desafiodev.service.ProfessorCursoService;
import br.com.unoesc.desafiodev.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/professores")
public class ProfessorController {
    
    @Autowired
    private ProfessorCursoService professorCursoService;

    @Autowired
    private PessoaService pessoaService;  // Servico para buscar o professor

    @GetMapping("/{professorId}/cursos")
    public ResponseEntity<List<Curso>> getCursosPorProfessor(@PathVariable Integer professorId) {
        Pessoa professor = pessoaService.buscarPorId(professorId); // Busca o professor por ID
        if (professor == null) {
            return ResponseEntity.notFound().build();
        }
        List<Curso> cursos = professorCursoService.obterCursosPorProfessor(professor);
        return ResponseEntity.ok(cursos);
    }
}
