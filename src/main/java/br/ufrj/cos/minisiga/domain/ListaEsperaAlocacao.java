package br.ufrj.cos.minisiga.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A ListaEsperaAlocacao.
 */
@Entity
@Table(name = "lista_espera_alocacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ListaEsperaAlocacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private ZonedDateTime data;

    @OneToOne
    @JoinColumn(unique = true)
    private Aluno aluno;

    @ManyToOne
    private Turma turma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public ListaEsperaAlocacao data(ZonedDateTime data) {
        this.data = data;
        return this;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public ListaEsperaAlocacao aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public ListaEsperaAlocacao turma(Turma turma) {
        this.turma = turma;
        return this;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListaEsperaAlocacao listaEsperaAlocacao = (ListaEsperaAlocacao) o;
        if (listaEsperaAlocacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), listaEsperaAlocacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ListaEsperaAlocacao{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
