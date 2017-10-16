package br.ufrj.cos.minisiga.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Aluno.
 */
@Entity
@Table(name = "aluno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @NotNull
    @Column(name = "dre", nullable = false)
    private String dre;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToOne(mappedBy = "aluno")
    @JsonIgnore
    private Nota aluno;

    @OneToOne(mappedBy = "aluno")
    @JsonIgnore
    private ListaEsperaAlocacao aluno;

    @ManyToMany(mappedBy = "inscritos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Turma> turmas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public Aluno cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDre() {
        return dre;
    }

    public Aluno dre(String dre) {
        this.dre = dre;
        return this;
    }

    public void setDre(String dre) {
        this.dre = dre;
    }

    public User getUser() {
        return user;
    }

    public Aluno user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Nota getAluno() {
        return aluno;
    }

    public Aluno aluno(Nota nota) {
        this.aluno = nota;
        return this;
    }

    public void setAluno(Nota nota) {
        this.aluno = nota;
    }

    public ListaEsperaAlocacao getAluno() {
        return aluno;
    }

    public Aluno aluno(ListaEsperaAlocacao listaEsperaAlocacao) {
        this.aluno = listaEsperaAlocacao;
        return this;
    }

    public void setAluno(ListaEsperaAlocacao listaEsperaAlocacao) {
        this.aluno = listaEsperaAlocacao;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public Aluno turmas(Set<Turma> turmas) {
        this.turmas = turmas;
        return this;
    }

    public Aluno addTurmas(Turma turma) {
        this.turmas.add(turma);
        turma.getInscritos().add(this);
        return this;
    }

    public Aluno removeTurmas(Turma turma) {
        this.turmas.remove(turma);
        turma.getInscritos().remove(this);
        return this;
    }

    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Aluno aluno = (Aluno) o;
        if (aluno.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aluno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aluno{" +
            "id=" + getId() +
            ", cpf='" + getCpf() + "'" +
            ", dre='" + getDre() + "'" +
            "}";
    }
}
