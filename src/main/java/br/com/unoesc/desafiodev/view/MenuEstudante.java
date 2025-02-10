package br.com.unoesc.desafiodev.view;

import br.com.unoesc.desafiodev.model.Curso;
import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.service.EstudanteCursoService;

import java.util.List;
import java.util.Scanner;

public class MenuEstudante {

    private final EstudanteCursoService estudanteCursoService;

    public MenuEstudante(EstudanteCursoService estudanteCursoService) {
        this.estudanteCursoService = estudanteCursoService;
    }

    // Método para exibir o menu do estudante
    public void exibirMenuEstudante(Pessoa estudante) {
        Scanner leitura = new Scanner(System.in);
        int opcao = 0;
        do {
            System.out.println("\nMenu Estudante");
            System.out.println("1 - Cursos Disponíveis");
            System.out.println("2 - Meus Cursos Matriculados");
            System.out.println("3 - Matricular em Curso");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    mostrarCursosDisponiveis();
                    break;
                case 2:
                    mostrarCursosMatriculados(estudante);
                    break;
                case 3:
                    matricularEmCurso(estudante);
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    // Mostra cursos disponíveis para se matricular
    private void mostrarCursosDisponiveis() {
        List<Curso> cursosDisponiveis = estudanteCursoService.getCursosDisponiveis();
        if (cursosDisponiveis.isEmpty()) {
            System.out.println("Não há cursos disponíveis no momento.");
        } else {
            for (Curso curso : cursosDisponiveis) {
                System.out.println("Curso: " + curso.getNome());
            }
        }
    }

    // Mostra cursos matriculados pelo estudante
    private void mostrarCursosMatriculados(Pessoa estudante) {
        List<Curso> cursos = estudanteCursoService.getCursosDoEstudante(estudante.getId());
        if (cursos.isEmpty()) {
            System.out.println("Você não está matriculado em nenhum curso.");
        } else {
            for (Curso curso : cursos) {
                System.out.println("Curso: " + curso.getNome());
            }
        }
    }

    // Método para matricular o estudante em um curso
    private void matricularEmCurso(Pessoa estudante) {
        Scanner leitura = new Scanner(System.in);
        System.out.print("Informe o nome do curso para matrícula: ");
        String nomeCurso = leitura.nextLine();
        // Busca o curso pelo nome
        Curso curso = estudanteCursoService.getCursosDisponiveis()
                .stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nomeCurso)) // Ignora case
                .findFirst() // Retorna o primeiro curso encontrado
                .orElse(null); // Retorna null se não encontrar

        if (curso == null) {
            System.out.println("Curso não encontrado ou não disponível.");
        } else {
            String resultado = estudanteCursoService.matricularEstudante(estudante, curso);
            System.out.println(resultado);
        }
    }
}
