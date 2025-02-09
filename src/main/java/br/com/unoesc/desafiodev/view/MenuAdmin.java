package br.com.unoesc.desafiodev.view;

import br.com.unoesc.desafiodev.model.Curso;
import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.service.PessoaService;
import br.com.unoesc.desafiodev.service.CursoService;
import br.com.unoesc.desafiodev.service.PessoaEnderecoService;

import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class MenuAdmin {

    private final Scanner leitura = new Scanner(System.in);
    private final PessoaService pessoaService;
    private final PessoaEnderecoService pessoaEnderecoService;
    private final CursoService cursoService;

    public MenuAdmin(PessoaService pessoaService, PessoaEnderecoService pessoaEnderecoService,
            CursoService cursoService) {
        this.pessoaService = pessoaService;
        this.pessoaEnderecoService = pessoaEnderecoService;
        this.cursoService = cursoService;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1. Criar Pessoa");
            System.out.println("2. Editar Pessoa");
            System.out.println("3. Excluir Pessoa");
            System.out.println("4. Listar Pessoas");
            System.out.println("5. Criar Curso");
            System.out.println("6. Editar Curso");
            System.out.println("7. Excluir Curso");
            System.out.println("8. Listar Cursos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    Pessoa pessoa = pessoaService.getDadosPessoa();
                    //Pessoa pessoaEndereco = pessoaEnderecoService.solicitarEndereco(pessoa);
                    if (pessoa != null) {
                        pessoaService.solicitarDadosManuais(pessoa);
                        System.out.println("*** Informações de endereço:\n ***");
                        pessoaEnderecoService.solicitarEndereco(pessoa);
                    } else {
                        System.out.println("❌ Não foi possível gerar os dados.");
                    }
                    break;


                    case 2:
                    System.out.println("\n ***Editar Pessoa ***");

                    System.out.print("Digite o ID da pessoa para editar: ");
                    Integer idEditar = leitura.nextInt();
                    leitura.nextLine(); 

                    Optional<Pessoa> optionalPessoaEditar = pessoaService.buscarPessoaPorId(idEditar);

                    if (optionalPessoaEditar.isPresent()) {
                        Pessoa pessoaEditar = optionalPessoaEditar.get();

                    pessoaService.editarPessoa(idEditar);

                    // System.out.println("Deseja editar o endereço? (S/N)");
                    // String resposta = leitura.nextLine().trim().toUpperCase();

                //     if (resposta.equals("S")) {
                //         pessoaEnderecoService.editarEndereco(pessoaEditar);
                //     }
                // } else {
                //     System.out.println("Pessoa não encontrada.");
                // }
                    break;

                    case 3:
                    System.out.println("\n ***Excluir Pessoa ***");
                    System.out.print("Digite o ID da pessoa para excluir: ");
                    Integer idExcluir = leitura.nextInt();
                    pessoaService.excluirPessoa(idExcluir);
                    break;

                    case 4:
                    System.out.println("\n ***Listar Pessoas ***");
                    pessoaService.listarPessoas();
                    List<Pessoa> pessoas = pessoaService.listarPessoas();
                    for (Pessoa p : pessoas) {
                        System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome());
                    }
                    break;

                case 5:
                    System.out.println("\n ***Criar Curso ***");
                    cursoService.criarCurso();
                    break;

                case 6:
                    System.out.println("\n ***Editar Curso ***");
                    System.out.print("Digite o ID do curso para editar: ");
                    idEditar = leitura.nextInt(); // Agora está correto
                    cursoService.editarCurso(idEditar);
                    break;

                case 7:
                    System.out.println("\n ***Excluir Curso ***");
                    System.out.print("Digite o ID do curso para excluir: ");
                    idExcluir = leitura.nextInt();
                    cursoService.excluirCurso(idExcluir);
                    break;

                case 8:
                    System.out.println("\n ***Listar Cursos ***");
                    cursoService.listarCursos();
                    List<Curso> cursos = cursoService.listarCursos();
                    for (Curso curso : cursos) {
                        System.out.println("ID: " + curso.getId() + ", Nome: " + curso.getNome());
                    }
                    break;


                case 0:
                    System.out.println("Saindo do menu...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
        
    }
}

}