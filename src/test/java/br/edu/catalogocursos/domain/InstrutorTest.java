package br.edu.catalogocursos.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InstrutorTest {

    @Test
    void deveAssociarCursoEManterOsDoisLadosConsistentes() {
        Instrutor instrutor = new Instrutor("Marina Costa", "marina.costa@example.com");
        Curso curso = new Curso("APIs REST com Spring Boot", 40, NivelCurso.INTERMEDIARIO);

        instrutor.adicionarCurso(curso);

        assertEquals(1, instrutor.getCursos().size());
        assertSame(curso, instrutor.getCursos().getFirst());
        assertSame(instrutor, curso.getInstrutor());
    }

    @Test
    void naoDeveAdicionarCursoNulo() {
        Instrutor instrutor = new Instrutor("Marina Costa", "marina.costa@example.com");

        assertThrows(NullPointerException.class, () -> instrutor.adicionarCurso(null));
    }

    @Test
    void naoDeveDuplicarAMesmaInstanciaNaColecao() {
        Instrutor instrutor = new Instrutor("Marina Costa", "marina.costa@example.com");
        Curso curso = new Curso("APIs REST com Spring Boot", 40, NivelCurso.INTERMEDIARIO);

        instrutor.adicionarCurso(curso);
        instrutor.adicionarCurso(curso);

        assertEquals(1, instrutor.getCursos().size());
    }
}
