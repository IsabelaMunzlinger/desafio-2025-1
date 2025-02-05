package br.com.unoesc.desafiodev.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "pessoa", schema = "desafio")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = false;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    public Pessoa() {
    }

    @JsonProperty("name")
    private void unpackName(Name name) {
        this.nome = name.getFirst() + " " + name.getLast();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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
}
