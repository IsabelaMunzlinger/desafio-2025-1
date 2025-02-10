package br.com.unoesc.desafiodev.principal;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.model.Papel;
import br.com.unoesc.desafiodev.view.MenuAdmin;
import  br.com.unoesc.desafiodev.view.MenuEstudante;
import br.com.unoesc.desafiodev.view.MenuProfessor;
import br.com.unoesc.desafiodev.repository.PessoaRepository;
import br.com.unoesc.desafiodev.service.PessoaService;
import br.com.unoesc.desafiodev.service.ProfessorCursoService;
import br.com.unoesc.desafiodev.service.CursoService;
import br.com.unoesc.desafiodev.service.EstudanteCursoService;
import br.com.unoesc.desafiodev.service.PessoaEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class Principal {

    private final Scanner leitura = new Scanner(System.in);
    private final PessoaRepository pessoaRepository;
    private final PessoaService pessoaService;
    private final PessoaEnderecoService pessoaEnderecoService;
    private final CursoService cursoService;

    @Autowired
    private EstudanteCursoService estudanteCursoService;

    @Autowired
    private ProfessorCursoService  professorCursoService;


    @Autowired
    public Principal(PessoaRepository pessoaRepository, PessoaService pessoaService, PessoaEnderecoService pessoaEnderecoService, CursoService cursoService) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
        this.pessoaEnderecoService = pessoaEnderecoService;
        this.cursoService = cursoService;
    }

    public void login() {
        System.out.println(" *** LOGIN ***");
        System.out.print("👤 Usuário: ");
        String usuario = leitura.nextLine();
        System.out.print(" Senha: ");
        String senha = leitura.nextLine();

        Optional<Pessoa> pessoaOptional = pessoaRepository.findByUsuario(usuario);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            if (pessoa.getSenha().equals(senha)) {
                System.out.println(" Login bem-sucedido! Bem-vindo, " + pessoa.getNome() + "!");
                exibirMenu(pessoa);
            } else {
                System.out.println("Senha incorreta!");
            }
        } else {
            System.out.println(" Usuário não encontrado!");
        }
    }

    public void exibirMenu(Pessoa pessoa) {
        if (pessoa.getPapel() == Papel.ADMIN) {
            MenuAdmin menuAdmin = new MenuAdmin(pessoaService, pessoaEnderecoService, cursoService);
            menuAdmin.exibirMenu();
        } else if (pessoa.getPapel() == Papel.ESTUDANTE){
            MenuEstudante menuEstudante = new MenuEstudante(estudanteCursoService);
            menuEstudante.exibirMenuEstudante(pessoa);
        }else if(pessoa.getPapel() == Papel.PROFESSOR){
            MenuProfessor menuProfessor = new MenuProfessor(cursoService, professorCursoService);
            menuProfessor.exibirMenuProfessor(pessoa);
        }else{
            System.out.println("Saindo...");
        }
    }
}
