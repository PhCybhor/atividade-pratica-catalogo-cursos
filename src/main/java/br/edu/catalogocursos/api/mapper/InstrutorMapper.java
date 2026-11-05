package br.edu.catalogocursos.api.mapper;

import br.edu.catalogocursos.api.dto.InstrutorRequest;
import br.edu.catalogocursos.api.dto.InstrutorResponse;
import br.edu.catalogocursos.api.dto.InstrutorResumoResponse;
import br.edu.catalogocursos.domain.Instrutor;
import org.springframework.stereotype.Component;

@Component
public class InstrutorMapper {

    public Instrutor toEntity(InstrutorRequest request) {
        return new Instrutor(request.nome(), request.email());
    }

    public InstrutorResponse toResponse(Instrutor instrutor) {
        return new InstrutorResponse(
                instrutor.getId(),
                instrutor.getNome(),
                instrutor.getEmail());
    }

    public InstrutorResumoResponse toResumo(Instrutor instrutor) {
        return new InstrutorResumoResponse(
                instrutor.getId(),
                instrutor.getNome());
    }
}
