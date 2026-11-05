package br.edu.catalogocursos.api;

import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.domain.NivelCurso;
import br.edu.catalogocursos.repository.InstrutorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class CursoListagemApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Test
    void deveListarSomenteCursosDoInstrutorOrdenadosPorTitulo() throws Exception {
        Instrutor marina = instrutorRepository.save(
                new Instrutor("Marina Costa", "marina.listagem@example.com"));
        Instrutor outro = instrutorRepository.save(
                new Instrutor("Outro Instrutor", "outro.listagem@example.com"));

        cadastrarCurso("Java orientado a objetos", 60, NivelCurso.INICIANTE, marina.getId());
        cadastrarCurso("APIs REST com Spring Boot", 40, NivelCurso.INTERMEDIARIO, marina.getId());
        cadastrarCurso("Curso de outro instrutor", 20, NivelCurso.INICIANTE, outro.getId());

        mockMvc.perform(get("/api/instrutores/{id}/cursos", marina.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].titulo").value("APIs REST com Spring Boot"))
                .andExpect(jsonPath("$[1].titulo").value("Java orientado a objetos"));
    }

    private void cadastrarCurso(
            String titulo,
            int cargaHoraria,
            NivelCurso nivel,
            Long instrutorId) throws Exception {
        String json = """
                {
                  "titulo": "%s",
                  "cargaHoraria": %d,
                  "nivel": "%s",
                  "instrutorId": %d
                }
                """.formatted(titulo, cargaHoraria, nivel.name(), instrutorId);

        mockMvc.perform(post("/api/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }
}
