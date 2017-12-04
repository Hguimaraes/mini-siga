package br.ufrj.cos.minisiga.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import br.ufrj.cos.minisiga.domain.enumeration.DIAS;

/**
 * A Horario.
 */
@Entity
@Table(name = "horario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dia", nullable = false)
    private DIAS dia;

    @NotNull
    @Column(name = "hora_inicio", nullable = false)
    private Instant horaInicio;

    @NotNull
    @Column(name = "hora_fim", nullable = false)
    private Instant horaFim;

    @NotNull
    @Column(name = "desc_extenso", nullable = false)
    private String descExtenso;

    @ManyToMany(mappedBy = "horarios")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Turma> turmas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DIAS getDia() {
        return dia;
    }

    public Horario dia(DIAS dia) {
        this.dia = dia;
        return this;
    }

    public void setDia(DIAS dia) {
        this.dia = dia;
    }

    public Instant getHoraInicio() {
        return horaInicio;
    }

    public Horario horaInicio(Instant horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public void setHoraInicio(Instant horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Instant getHoraFim() {
        return horaFim;
    }

    public Horario horaFim(Instant horaFim) {
        this.horaFim = horaFim;
        return this;
    }

    public void setHoraFim(Instant horaFim) {
        this.horaFim = horaFim;
    }

    public String getDescExtenso() {
        return descExtenso;
    }

    public Horario descExtenso(String descExtenso) {
        this.descExtenso = descExtenso;
        return this;
    }

    public void setDescExtenso(String descExtenso) {
        this.descExtenso = descExtenso;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public Horario turmas(Set<Turma> turmas) {
        this.turmas = turmas;
        return this;
    }

    public Horario addTurma(Turma turma) {
        this.turmas.add(turma);
        turma.getHorarios().add(this);
        return this;
    }

    public Horario removeTurma(Turma turma) {
        this.turmas.remove(turma);
        turma.getHorarios().remove(this);
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
        Horario horario = (Horario) o;
        if (horario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), horario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Horario{" +
            "id=" + getId() +
            ", dia='" + getDia() + "'" +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFim='" + getHoraFim() + "'" +
            ", descExtenso='" + getDescExtenso() + "'" +
            "}";
    }
}
