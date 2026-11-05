package br.edu.catalogocursos.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "instrutor",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_instrutor_email",
                columnNames = "email"))
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 160)
    private String email;

    @OneToMany(mappedBy = "instrutor", fetch = FetchType.LAZY)
    private List<Curso> cursos = new ArrayList<>();

    protected Instrutor() {
    }

    public Instrutor(String nome, String email) {
        this.nome = validarNome(nome);
        this.email = validarEmail(email);
    }

    public void adicionarCurso(Curso curso) {
        Objects.requireNonNull(curso, "Curso é obrigatório");

        curso.associarA(this);

        if (!cursos.contains(curso)) {
            cursos.add(curso);
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Curso> getCursos() {
        return List.copyOf(cursos);
    }

    private static String validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do instrutor é obrigatório");
        }
        String valor = nome.trim();
        if (valor.length() < 3 || valor.length() > 120) {
            throw new IllegalArgumentException("Nome deve ter entre 3 e 120 caracteres");
        }
        return valor;
    }

    private static String validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail do instrutor é obrigatório");
        }
        return email.trim().toLowerCase();
    }
}
