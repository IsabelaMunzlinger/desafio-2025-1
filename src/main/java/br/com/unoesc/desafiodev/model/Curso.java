package br.com.unoesc.desafiodev.model;

import jakarta.persistence.*;

@Entity
@Table(name = "curso", schema = "desafio")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "encontros", nullable = false)
    private Integer encontros;

    @Lob
    @Column(name = "situacao", nullable = false)
    private String situacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Integer getEncontros() {
        return encontros;
    }

    public void setEncontros(Integer encontros) {
        this.encontros = encontros;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

}