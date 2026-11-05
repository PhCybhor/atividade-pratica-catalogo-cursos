package br.edu.catalogocursos.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NivelCurso nivel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "instrutor_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_curso_instrutor"))
    private Instrutor instrutor;

    protected Curso() {
    }

    public Curso(String titulo, Integer cargaHoraria, NivelCurso nivel) {
        this.titulo = validarTitulo(titulo);
        this.cargaHoraria = validarCargaHoraria(cargaHoraria);
        this.nivel = Objects.requireNonNull(nivel, "Nível do curso é obrigatório");
    }

    void associarA(Instrutor instrutor) {
        Objects.requireNonNull(instrutor, "Instrutor é obrigatório");

        if (this.instrutor != null && this.instrutor != instrutor) {
            throw new IllegalStateException("Curso já pertence a outro instrutor");
        }

        this.instrutor = instrutor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public NivelCurso getNivel() {
        return nivel;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    private static String validarTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título do curso é obrigatório");
        }
        String valor = titulo.trim();
        if (valor.length() < 3 || valor.length() > 150) {
            throw new IllegalArgumentException("Título deve ter entre 3 e 150 caracteres");
        }
        return valor;
    }

    private static Integer validarCargaHoraria(Integer cargaHoraria) {
        Objects.requireNonNull(cargaHoraria, "Carga horária é obrigatória");
        if (cargaHoraria < 1 || cargaHoraria > 400) {
            throw new IllegalArgumentException("Carga horária deve estar entre 1 e 400 horas");
        }
        return cargaHoraria;
    }
}
