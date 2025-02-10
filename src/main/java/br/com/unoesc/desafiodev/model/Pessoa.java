package br.com.unoesc.desafiodev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "pessoa", schema = "desafio")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pessoa {

    /*
    Tabelas dos atributos
     */

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Id
    private Integer id;

    @Transient  // Esse campo será preenchido, mas não será salvo no banco
    private Name name;

    @Column
    private String nome; // Esse é o campo real que vai ser armazenado

    @JsonProperty("email")
    @Column
    private String email;

    @Column(nullable = false, unique = true)
    @NonNull
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @Column
    private boolean ativo;

    @JsonIgnore
    @Column(nullable = false)
    private String usuario;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Papel papel;


    //Relacionamento com a classe Pessoaendereco
    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaEndereco pessoaEndereco;

    /*
    Atributos vindos da API
     */


    @JsonProperty("phone") // Mapeia o campo JSON "phone" para a variável "telefone"
    @Column(nullable = true)
    private String telefone;

    // Método que extrai os nomes do objeto "name"
    @JsonProperty("name")
    public void unpackName(Name name) {
        this.nome = name.getFirst() + " " + name.getLast();
    }

    // public void setPessoaEndereco(PessoaEndereco endereco) {
    // }


    // Classe interna para mapear o objeto "name"
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Name {
        private String first;
        private String last;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }

    /*
    Getters e setters
     */

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @JsonIgnore
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //@JsonIgnore
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @NonNull
    public String getCpf() {
        return cpf;
    }

    public void setCpf(@NonNull String cpf) {
        this.cpf = cpf;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public PessoaEndereco getPessoaEndereco() {
        return pessoaEndereco;
    }

    public void setPessoaEndereco(PessoaEndereco pessoaEndereco) {
        this.pessoaEndereco = pessoaEndereco;
    }
    

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }
}
