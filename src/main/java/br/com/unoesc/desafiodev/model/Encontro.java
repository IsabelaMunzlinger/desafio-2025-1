package br.com.unoesc.desafiodev.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "encontro", schema = "desafio")
public class Encontro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "nota")
    private Double nota;

    @Column(name = "presenca", nullable = false)
    private Boolean presenca;

    /*
     Getters e setters
     */
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }

    public Boolean getPresenca() { return presenca; }
    public void setPresenca(Boolean presenca) { this.presenca = presenca; }
}
