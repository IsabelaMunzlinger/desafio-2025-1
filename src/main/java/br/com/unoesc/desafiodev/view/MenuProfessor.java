package br.com.unoesc.desafiodev.view;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.service.CursoService;
import br.com.unoesc.desafiodev.service.ProfessorCursoService;
import br.com.unoesc.desafiodev.model.Curso;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuProfessor {

    private final Scanner leitura = new Scanner(System.in);
    private final CursoService cursoService;
    private final ProfessorCursoService professorCursoService;

    private boolean continuar = true;

    @Autowired
    public MenuProfessor(CursoService cursoService, ProfessorCursoService professorCursoService) {
        this.cursoService = cursoService;
        this.professorCursoService = professorCursoService;
    }


    //Método para exibir as opções do menu do professor
    public void exibirMenuProfessor(Pessoa professor) {
        // Exibe os cursos que o professor já está vinculado
        List<Curso> cursos = professorCursoService.obterCursosPorProfessor(professor);
        System.out.println("Cursos do professor " + professor.getNome() + ":");
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso vinculado.");
        } else {
            //Vai iterar por todos os cursos que o professor está vinculado
            cursos.forEach(curso -> System.out.println(curso.getNome()));
        }

        while (continuar) {
            // Menu de opções
            System.out.println("\nMenu Professor:");
            System.out.println("1 - Vincular-se a Cursos Disponíveis");
            System.out.println("2 - Publicar Notas dos Alunos");
            System.out.println("0 - Voltar");

            System.out.print("Escolha uma opção: ");
            int opcao = leitura.nextInt();

            switch (opcao) {
                case 1:
                    //Chama o método da classe para vincular o professor a um curso
                    vincularCursos(professor);
                    break;
                case 2:
                    System.out.println("Funcionalidade ainda não implementada.");
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }


    //Método para vincular o professor a um ou mais cursos
    private void vincularCursos(Pessoa professor) {
        System.out.println("\nCursos Disponíveis:");

        // Solicita os cursos disponíveis
        List<Curso> cursosDisponiveis = cursoService.solicitarCursosDisponiveis();

        if (cursosDisponiveis.isEmpty()) {
            System.out.println("Nenhum curso disponível no momento.");
            return;
        }

        // Exibe os cursos disponíveis
        for (int i = 0; i < cursosDisponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + cursosDisponiveis.get(i).getNome());
        }

        System.out.print("Escolha o número do curso para se vincular: ");
        int cursoEscolhido = leitura.nextInt();

        if (cursoEscolhido > 0 && cursoEscolhido <= cursosDisponiveis.size()) {
            Curso curso = cursosDisponiveis.get(cursoEscolhido - 1);

            // Verifica se o curso está ATIVO antes de vincular
            if (!curso.getSituacao().equals("ATIVO")) {
                System.out.println("Não é possível vincular ao curso " + curso.getNome() + ", pois o status não é 'ATIVO'.");
                return;
            }

            // Chama o serviço para vincular o professor ao curso
            professorCursoService.vincularProfessorAoCurso(professor, curso);
            System.out.println("Professor vinculado ao curso " + curso.getNome());
        } else {
            System.out.println("Opção inválida.");
        }
    }
}
