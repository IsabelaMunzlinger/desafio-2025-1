package br.com.unoesc.desafiodev.principal;


import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.repository.PessoaRepository;
import br.com.unoesc.desafiodev.service.ConsumoAPI;
import br.com.unoesc.desafiodev.service.ConverteDados;
import br.com.unoesc.desafiodev.service.ResultadoAPI;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;
import java.util.Scanner;

//@Service
public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final ConverteDados conversor = new ConverteDados();
    private final ConsumoAPI consumo = new ConsumoAPI();
    private static final String ENDERECO = "https://randomuser.me/api/";

    private final PessoaRepository pessoaRepository;

    @Autowired
    public Principal(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void executar() {
        Pessoa pessoa = getDadosPessoa();
        if (pessoa != null) {
            solicitarDadosManuais(pessoa);

            System.out.println("\n✅ Pessoa Criada:");
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("CPF: " + pessoa.getCpf());
            System.out.println("Ativo: " + (pessoa.isAtivo() ? "Sim" : "Não"));
            System.out.println("Usuário: " + pessoa.getUsuario());
        } else {
            System.out.println("❌ Erro ao criar pessoa.");
        }
    }

    private Pessoa getDadosPessoa() {
        System.out.println("🔄 Pressione Enter para gerar uma pessoa aleatória...");
        leitura.nextLine();

        String json = consumo.obterDados(ENDERECO);

        ResultadoAPI resultado = conversor.obterDados(json, ResultadoAPI.class);

        if (resultado != null && resultado.getResults() != null && !resultado.getResults().isEmpty()) {
            return resultado.getResults().get(0);
        }
        return null;
    }

    public void login() {
        System.out.println("🔐 LOGIN:");
        System.out.print("👤 Usuário: ");
        String usuario = leitura.nextLine();
        System.out.print("🔑 Senha: ");
        String senha = leitura.nextLine();

        Optional<Pessoa> pessoaOptional = pessoaRepository.findByUsuario(usuario);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            if (pessoa.getSenha().equals(senha)) {
                System.out.println("✅ Login bem-sucedido! Bem-vindo, " + pessoa.getNome() + "!");
            } else {
                System.out.println("❌ Senha incorreta!");
            }
        } else {
            System.out.println("❌ Usuário não encontrado!");
        }
    }

    private void solicitarDadosManuais(Pessoa pessoa) {
        // Solicita CPF e valida
        String cpf;
        do {
            System.out.print("🔢 Digite o CPF (somente números): ");
            cpf = leitura.nextLine().trim();
            if (!cpf.matches("\\d{11}")) {
                System.out.println("❌ CPF inválido! Digite 11 números.");
            }
        } while (!cpf.matches("\\d{11}"));

        pessoa.setCpf(cpf);
        //pessoaRepository.save(pessoa);
        System.out.println(cpf);

        // Pergunta se a pessoa está ativa
        System.out.print("✅ A pessoa está ativa? (S/N): ");
        String resposta = leitura.nextLine().trim().toUpperCase();
        pessoa.setAtivo(resposta.equals("S"));

        // Solicita nome de usuário
        System.out.print("👤 Digite o nome de usuário: ");
        pessoa.setUsuario(leitura.nextLine());

        // Solicita senha
        System.out.print("🔑 Digite a senha: ");
        pessoa.setSenha(leitura.nextLine());

        System.out.println("CPF salvo no objeto: " + pessoa.getCpf());
        pessoaRepository.save(pessoa);
        System.out.println("✅ Pessoa salva com sucesso!");
    }

}
