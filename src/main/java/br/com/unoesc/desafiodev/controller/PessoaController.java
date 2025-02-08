package br.com.unoesc.desafiodev.controller;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // Endpoint para adicionar pessoa (POST)
    @PostMapping
    public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.adicionarPessoa(pessoa);
    }

    // Endpoint para listar todas as pessoas (GET)
    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }
}
