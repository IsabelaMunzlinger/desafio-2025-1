package br.com.unoesc.desafiodev.controller;

import br.com.unoesc.desafiodev.model.Curso;
import br.com.unoesc.desafiodev.service.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController{

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public Curso criarCurso(@RequestBody Curso curso) {
        return cursoService.solicitarDadosCurso();
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }
}
