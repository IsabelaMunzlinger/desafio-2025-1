package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Curso;
import br.com.unoesc.desafiodev.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    private final Scanner leitura = new Scanner(System.in);

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }
   
    public Curso solicitarDadosCurso() {
        Curso curso = new Curso();

        System.out.println("Nome do curso: ");
        String nomeCurso = leitura.nextLine();

        System.out.println("Assunto do curso: ");
        String assuntoCurso = leitura.nextLine();

        System.out.println("Situação do curso: ATIVO / INATIVO");
        String situacaoCurso = leitura.nextLine(); 

       
        curso.setNome(nomeCurso);
        curso.setAssunto(assuntoCurso);
        curso.setSituacao(situacaoCurso);

        return curso;
    }

    /*
    Método para criar um curso e chamar o método que armazena no banco
    */
    public void criarCurso() {
        Curso curso = solicitarDadosCurso();
        save(curso);
        System.out.println("Curso criado: " + curso.getNome());
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    /*
    Método para salvar no banco de dados (MySQL)
    */
    public void save(Curso curso) {
        cursoRepository.save(curso);
    }
}
