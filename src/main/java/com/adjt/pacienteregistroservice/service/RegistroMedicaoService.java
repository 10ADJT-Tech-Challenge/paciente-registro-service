package com.adjt.pacienteregistroservice.service;

import com.adjt.pacienteregistroservice.dto.generated.model.RegistroRequest;
import com.adjt.pacienteregistroservice.dto.generated.model.RegistroResponse;
import com.adjt.pacienteregistroservice.entity.RegistroMedicao;
import com.adjt.pacienteregistroservice.persistence.RegistroMedicaoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class RegistroMedicaoService {

    private final RegistroMedicaoRepository repository;

    public RegistroMedicaoService(RegistroMedicaoRepository repository) {
        this.repository = repository;
    }

    public RegistroResponse criarRegistroMedicao(RegistroRequest request) {
        final RegistroMedicao registroMedicao = new RegistroMedicao(
                UUID.randomUUID(),
                request.getCpfPaciente(),
                request.getIdEvento(),
                request.getValorMedicao(),
                OffsetDateTime.now(),
                request.getOrigemRegistro()
        );
        repository.save(registroMedicao);
        // todo: implementar busca dos valores de referencia para avaliar o alerta
        return new RegistroResponse()
                .idRegistro(UUID.randomUUID())
                .statusAlerta(RegistroResponse.StatusAlertaEnum.NORMAL)
                .mensagem("Registro salvo com sucesso.");
    }

}
