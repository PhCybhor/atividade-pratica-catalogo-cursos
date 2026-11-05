package br.edu.catalogocursos.api.dto;

import br.edu.catalogocursos.domain.NivelCurso;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CursoRequest(
        @NotBlank(message = "Título é obrigatório")
        @Size(min = 3, max = 150, message = "Título deve ter entre 3 e 150 caracteres")
        String titulo,

        @NotNull(message = "Carga horária é obrigatória")
        @Min(value = 1, message = "Carga horária mínima é 1")
        @Max(value = 400, message = "Carga horária máxima é 400")
        Integer cargaHoraria,

        @NotNull(message = "Nível é obrigatório")
        NivelCurso nivel,

        @NotNull(message = "Instrutor é obrigatório")
        Long instrutorId
) {
}
