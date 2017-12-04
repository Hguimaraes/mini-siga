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
 * A Turma.
 */
@Entity
@Table(name = "turma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "max_inscritos", nullable = false)
    private Integer maxInscritos;

    @NotNull
    @Column(name = "sala", nullable = false)
    private String sala;

    @OneToOne
    @JoinColumn(unique = true)
    private Funcionario professor;

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Nota> notas = new HashSet<>();

    @OneToMany(mappedBy = "turma")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ListaEsperaAlocacao> listaesperas = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "turma_inscritos",
               joinColumns = @JoinColumn(name="turmas_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="inscritos_id", referencedColumnName="id"))
    private Set<Aluno> inscritos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "horario_turma",
               joinColumns = @JoinColumn(name="turmas_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="horarios_id", referencedColumnName="id"))
    private Set<Horario> horarios = new HashSet<>();

    @ManyToOne
    private Disciplina disciplina;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxInscritos() {
        return maxInscritos;
    }

    public Turma maxInscritos(Integer maxInscritos) {
        this.maxInscritos = maxInscritos;
        return this;
    }

    public void setMaxInscritos(Integer maxInscritos) {
        this.maxInscritos = maxInscritos;
    }

    public String getSala() {
        return sala;
    }

    public Turma sala(String sala) {
        this.sala = sala;
        return this;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Funcionario getProfessor() {
        return professor;
    }

    public Turma professor(Funcionario funcionario) {
        this.professor = funcionario;
        return this;
    }

    public void setProfessor(Funcionario funcionario) {
        this.professor = funcionario;
    }

    public Set<Nota> getNotas() {
        return notas;
    }

    public Turma notas(Set<Nota> notas) {
        this.notas = notas;
        return this;
    }

    public Turma addNota(Nota nota) {
        this.notas.add(nota);
        nota.setTurma(this);
        return this;
    }

    public Turma removeNota(Nota nota) {
        this.notas.remove(nota);
        nota.setTurma(null);
        return this;
    }

    public void setNotas(Set<Nota> notas) {
        this.notas = notas;
    }

    public Set<ListaEsperaAlocacao> getListaesperas() {
        return listaesperas;
    }

    public Turma listaesperas(Set<ListaEsperaAlocacao> listaEsperaAlocacaos) {
        this.listaesperas = listaEsperaAlocacaos;
        return this;
    }

    public Turma addListaespera(ListaEsperaAlocacao listaEsperaAlocacao) {
        this.listaesperas.add(listaEsperaAlocacao);
        listaEsperaAlocacao.setTurma(this);
        return this;
    }

    public Turma removeListaespera(ListaEsperaAlocacao listaEsperaAlocacao) {
        this.listaesperas.remove(listaEsperaAlocacao);
        listaEsperaAlocacao.setTurma(null);
        return this;
    }

    public void setListaesperas(Set<ListaEsperaAlocacao> listaEsperaAlocacaos) {
        this.listaesperas = listaEsperaAlocacaos;
    }

    public Set<Aluno> getInscritos() {
        return inscritos;
    }

    public Turma inscritos(Set<Aluno> alunos) {
        this.inscritos = alunos;
        return this;
    }

    public Turma addInscritos(Aluno aluno) {
        this.inscritos.add(aluno);
        aluno.getTurmas().add(this);
        return this;
    }

    public Turma removeInscritos(Aluno aluno) {
        this.inscritos.remove(aluno);
        aluno.getTurmas().remove(this);
        return this;
    }

    public void setInscritos(Set<Aluno> alunos) {
        this.inscritos = alunos;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public Turma horarios(Set<Horario> horarios) {
        this.horarios = horarios;
        return this;
    }

    public Turma addHorario(Horario horario) {
        this.horarios.add(horario);
        horario.getTurmas().add(this);
        return this;
    }

    public Turma removeHorario(Horario horario) {
        this.horarios.remove(horario);
        horario.getTurmas().remove(this);
        return this;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Turma disciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
        return this;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Turma turma = (Turma) o;
        if (turma.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), turma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Turma{" +
            "id=" + getId() +
            ", maxInscritos='" + getMaxInscritos() + "'" +
            ", sala='" + getSala() + "'" +
            "}";
    }
}
