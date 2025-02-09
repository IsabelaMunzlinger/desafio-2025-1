package br.com.unoesc.desafiodev.view;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.service.PessoaService;

import java.util.Scanner;

public class MenuAdmin {

    private final Scanner leitura = new Scanner(System.in);
    private final PessoaService pessoaService;

    public MenuAdmin(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Menu Administrador ---");
            System.out.println("1. Criar Pessoa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = leitura.nextInt();
            leitura.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    Pessoa pessoa = pessoaService.getDadosPessoa();
                    if (pessoa != null) {
                        pessoaService.solicitarDadosManuais(pessoa);
                    } else {
                        System.out.println("❌ Não foi possível gerar os dados.");
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
