package br.com.unoesc.desafiodev.model;

import jakarta.persistence.*;
import java.util.List;

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

    @Lob
    @Column(name = "situacao", nullable = false)
    private String situacao;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Encontro> encontros;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstudanteCurso> estudantecurso;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProfessorCurso> professorecurso;

    public Curso() {
    }


    // Getters e Setters

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

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


    public List<Encontro> getEncontros() {
        return encontros;
    }

    public void setEncontros(List<Encontro> encontros) {
        this.encontros = encontros;
    }

    public List<EstudanteCurso> getEstudantecurso() {
        return estudantecurso;
    }

    public void setEstudantecurso(List<EstudanteCurso> estudantecurso) {
        this.estudantecurso = estudantecurso;
    }

    public List<ProfessorCurso> getProfessorecurso() {
        return professorecurso;
    }

    public void setProfessorecurso(List<ProfessorCurso> professorecurso) {
        this.professorecurso = professorecurso;
    }
}