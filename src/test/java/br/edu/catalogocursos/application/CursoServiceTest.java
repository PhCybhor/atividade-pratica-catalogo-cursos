package br.edu.catalogocursos.application;

import br.edu.catalogocursos.domain.Curso;
import br.edu.catalogocursos.domain.NivelCurso;
import br.edu.catalogocursos.repository.CursoRepository;
import br.edu.catalogocursos.repository.InstrutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private InstrutorRepository instrutorRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void deveRejeitarCadastroComInstrutorInexistente() {
        when(instrutorRepository.findById(999L)).thenReturn(Optional.empty());

        Curso curso = new Curso("APIs REST com Spring Boot", 40, NivelCurso.INTERMEDIARIO);

        assertThrows(
                RecursoNaoEncontradoException.class,
                () -> cursoService.cadastrar(curso, 999L));

        verify(cursoRepository, never()).save(any());
    }
}
