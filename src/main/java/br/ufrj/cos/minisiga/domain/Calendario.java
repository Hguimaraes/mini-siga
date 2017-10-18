package br.ufrj.cos.minisiga.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Calendario.
 */
@Entity
@Table(name = "calendario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Calendario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "periodo", nullable = false)
    private String periodo;

    @NotNull
    @Column(name = "ini_periodo", nullable = false)
    private ZonedDateTime iniPeriodo;

    @NotNull
    @Column(name = "fim_periodo", nullable = false)
    private ZonedDateTime fimPeriodo;

    @NotNull
    @Column(name = "ini_insc_disc", nullable = false)
    private ZonedDateTime iniInscDisc;

    @NotNull
    @Column(name = "fim_ins_disc", nullable = false)
    private ZonedDateTime fimInsDisc;

    @NotNull
    @Column(name = "ini_tranc_disc", nullable = false)
    private ZonedDateTime iniTrancDisc;

    @NotNull
    @Column(name = "fim_tranc_disc", nullable = false)
    private ZonedDateTime fimTrancDisc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Calendario periodo(String periodo) {
        this.periodo = periodo;
        return this;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public ZonedDateTime getIniPeriodo() {
        return iniPeriodo;
    }

    public Calendario iniPeriodo(ZonedDateTime iniPeriodo) {
        this.iniPeriodo = iniPeriodo;
        return this;
    }

    public void setIniPeriodo(ZonedDateTime iniPeriodo) {
        this.iniPeriodo = iniPeriodo;
    }

    public ZonedDateTime getFimPeriodo() {
        return fimPeriodo;
    }

    public Calendario fimPeriodo(ZonedDateTime fimPeriodo) {
        this.fimPeriodo = fimPeriodo;
        return this;
    }

    public void setFimPeriodo(ZonedDateTime fimPeriodo) {
        this.fimPeriodo = fimPeriodo;
    }

    public ZonedDateTime getIniInscDisc() {
        return iniInscDisc;
    }

    public Calendario iniInscDisc(ZonedDateTime iniInscDisc) {
        this.iniInscDisc = iniInscDisc;
        return this;
    }

    public void setIniInscDisc(ZonedDateTime iniInscDisc) {
        this.iniInscDisc = iniInscDisc;
    }

    public ZonedDateTime getFimInsDisc() {
        return fimInsDisc;
    }

    public Calendario fimInsDisc(ZonedDateTime fimInsDisc) {
        this.fimInsDisc = fimInsDisc;
        return this;
    }

    public void setFimInsDisc(ZonedDateTime fimInsDisc) {
        this.fimInsDisc = fimInsDisc;
    }

    public ZonedDateTime getIniTrancDisc() {
        return iniTrancDisc;
    }

    public Calendario iniTrancDisc(ZonedDateTime iniTrancDisc) {
        this.iniTrancDisc = iniTrancDisc;
        return this;
    }

    public void setIniTrancDisc(ZonedDateTime iniTrancDisc) {
        this.iniTrancDisc = iniTrancDisc;
    }

    public ZonedDateTime getFimTrancDisc() {
        return fimTrancDisc;
    }

    public Calendario fimTrancDisc(ZonedDateTime fimTrancDisc) {
        this.fimTrancDisc = fimTrancDisc;
        return this;
    }

    public void setFimTrancDisc(ZonedDateTime fimTrancDisc) {
        this.fimTrancDisc = fimTrancDisc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Calendario calendario = (Calendario) o;
        if (calendario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), calendario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Calendario{" +
            "id=" + getId() +
            ", periodo='" + getPeriodo() + "'" +
            ", iniPeriodo='" + getIniPeriodo() + "'" +
            ", fimPeriodo='" + getFimPeriodo() + "'" +
            ", iniInscDisc='" + getIniInscDisc() + "'" +
            ", fimInsDisc='" + getFimInsDisc() + "'" +
            ", iniTrancDisc='" + getIniTrancDisc() + "'" +
            ", fimTrancDisc='" + getFimTrancDisc() + "'" +
            "}";
    }
}
