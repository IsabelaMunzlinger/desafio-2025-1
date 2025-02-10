package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Curso;
import br.com.unoesc.desafiodev.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<Curso> solicitarCursosDisponiveis() {

        return cursoRepository.findBySituacao("ATIVO");
    }

    /*
     * Método para criar um curso e chamar o método que armazena no banco
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
     * Método para salvar no banco de dados (MySQL)
     */
    public void save(Curso curso) {
        cursoRepository.save(curso);
    }

    /*
     * Método para buscar um curso pelo ID
     */
    public Optional<Curso> buscarCursoPorId(Integer id) {
        return cursoRepository.findById(id);
    }

    /*
     * Método para editar o curso
     */
    public void editarCurso(Integer id) {
        Optional<Curso> optionalCurso = buscarCursoPorId(id);
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            System.out.println("Atualizando informações do curso: " + curso.getNome());

            System.out.print("Novo nome do curso: ");
            curso.setNome(leitura.nextLine());

            System.out.print("Novo assunto do curso: ");
            curso.setAssunto(leitura.nextLine());

            System.out.print("Nova situação do curso: ");
            curso.setSituacao(leitura.nextLine());

            save(curso);
            System.out.println("Curso atualizado: " + curso.getNome());
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    public void excluirCurso(Integer id) {
        Optional<Curso> optionalCurso = buscarCursoPorId(id);
        if (optionalCurso.isPresent()) {
            cursoRepository.delete(optionalCurso.get());
            System.out.println("Curso excluído com sucesso.");
        } else {
            System.out.println("Curso não encontrado.");
        }
    }


}
