package br.com.unoesc.desafiodev.service;

import br.com.unoesc.desafiodev.model.Pessoa;
import br.com.unoesc.desafiodev.model.Papel;
import br.com.unoesc.desafiodev.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;
import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoAPI consumo = new ConsumoAPI();   // Instância da classe ConsumoAPI
    private final ConverteDados conversor = new ConverteDados(); // Instância da classe ConverteDados
    private static final String ENDERECO = "https://randomuser.me/api/"; // Endereço da API

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa getDadosPessoa() {
        System.out.println("Pressione Enter para gerar uma pessoa aleatória...");
        leitura.nextLine();

        String json = consumo.obterDados(ENDERECO);

        ResultadoAPI resultado = conversor.obterDados(json, ResultadoAPI.class);

        if (resultado != null && resultado.getResults() != null && !resultado.getResults().isEmpty()) {
            return resultado.getResults().get(0);
        }
        return null;
    }

    public void solicitarDadosManuais(Pessoa pessoa) {
        // Solicita CPF e valida se tem 11 digitos
        String cpf;
        do {
            System.out.print(" Digite o CPF (somente números): ");
            cpf = leitura.nextLine().trim();
            if (!cpf.matches("\\d{11}")) {
                System.out.println("CPF inválido! Digite 11 números.");
            }
        } while (!cpf.matches("\\d{11}"));

        pessoa.setCpf(cpf);

        // Pergunta se a pessoa está ativa
        System.out.print(" A pessoa está ativa? (S/N): ");
        String resposta = leitura.nextLine().trim().toUpperCase();
        pessoa.setAtivo(resposta.equals("S"));

        // Solicita nome de usuário
        System.out.print(" Digite o nome de usuário: ");
        pessoa.setUsuario(leitura.nextLine());

        // Solicita senha
        System.out.print(" Digite a senha: ");
        pessoa.setSenha(leitura.nextLine());

        // Solicita o papel da pessoa
        System.out.println("Informe se a pessoa é ADMIN / PROFESSOR / ESTUDANTE");
        String papelInput = leitura.nextLine().toUpperCase(); // Converte para maiúsculas

        // Valida a entrada e atribui o papel
        try {
            // Verifica se a entrada corresponde a um valor do Enum
            Papel papel = Papel.valueOf(papelInput);
            pessoa.setPapel(papel); // Define o papel da pessoa
            System.out.println("Papel atribuído: " + papel);
        } catch (IllegalArgumentException e) {
            // Caso o papel seja inválido, exibe uma mensagem
            System.out.println("Papel inválido! Digite ADMIN, PROFESSOR ou ESTUDANTE.");
        }

        pessoaRepository.save(pessoa);
        System.out.println(" Pessoa salva com sucesso!");
    }

    // Método que será chamado para criar uma pessoa
    public void executar() {
        Pessoa pessoa = getDadosPessoa();
        if (pessoa != null) {
            solicitarDadosManuais(pessoa);

            System.out.println("\n Pessoa Criada:");
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("CPF: " + pessoa.getCpf());
            System.out.println("Ativo: " + (pessoa.isAtivo() ? "Sim" : "Não"));
            System.out.println("Usuário: " + pessoa.getUsuario());
        } else {
            System.out.println(" Erro ao criar pessoa.");
        }
    }

    // Encontrar pessoa por id
    public Optional<Pessoa> buscarPessoaPorId(Integer id) {
        return pessoaRepository.findById(id);
    }

    // Método para editar o pessoa
    public void editarPessoa(Integer id) {
        Optional<Pessoa> optionalPessoa = buscarPessoaPorId(id);
        if (optionalPessoa.isPresent()) {
            Pessoa pessoa = optionalPessoa.get();
            System.out.println("Atualizando informações da pessoa: " + pessoa.getNome());

            System.out.print("Novo nome da pessoa: ");
            pessoa.setNome(leitura.nextLine());

            System.out.print("Novo email: ");
            pessoa.setEmail(leitura.nextLine());

            System.out.println("Novo telefone: ");
            pessoa.setTelefone(leitura.nextLine());

            System.out.println("Novo usuário: ");
            pessoa.setUsuario(leitura.nextLine());

            System.out.println("Nova senha: ");
            pessoa.setSenha(leitura.nextLine());

            System.out.println("Novo papel: ");
            pessoa.setPapel(Papel.valueOf(leitura.nextLine().toUpperCase()));

            System.out.println("Nova situação (ATIVO / INATIVO): ");
            pessoa.setAtivo(leitura.nextLine().equalsIgnoreCase("S"));

            pessoaRepository.save(pessoa);

            System.out.println("Pessoa atualizada: " + pessoa.getNome());
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    public void excluirPessoa(Integer id) {
        Optional<Pessoa> optionalPessoa = buscarPessoaPorId(id);
        if (optionalPessoa.isPresent()) {
            pessoaRepository.delete(optionalPessoa.get());
            System.out.println("Pessoa excluída com sucesso.");
        } else {
            System.out.println("Pessoa não encontrado.");
        }
    }

    // Listar pessoas do banco de dados
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

}
