package com.adjt.pacienteregistroservice.service;

import com.adjt.pacienteregistroservice.dto.MedicaoRealizadaEvent;
import com.adjt.pacienteregistroservice.dto.generated.model.RegistroRequest;
import com.adjt.pacienteregistroservice.dto.generated.model.RegistroResponse;
import com.adjt.pacienteregistroservice.entity.RegistroMedicao;
import com.adjt.pacienteregistroservice.messaging.publisher.MedicaoPublisher;
import com.adjt.pacienteregistroservice.persistence.RegistroMedicaoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class RegistroMedicaoService {

    private final RegistroMedicaoRepository repository;
    private final MedicaoPublisher medicaoPublisher;

    public RegistroMedicaoService(RegistroMedicaoRepository repository, MedicaoPublisher medicaoPublisher) {
        this.repository = repository;
        this.medicaoPublisher = medicaoPublisher;
    }

    public RegistroResponse criarRegistroMedicao(RegistroRequest request) {
        final RegistroMedicao registroMedicao = salvaRegistro(request);
        notificaEventoRegistro(registroMedicao);

        return new RegistroResponse()
                .idRegistro(registroMedicao.getId())
                .mensagem("Registro salvo com sucesso.");
    }

    private RegistroMedicao salvaRegistro(RegistroRequest request) {
        final RegistroMedicao registroMedicao = new RegistroMedicao(
                UUID.randomUUID(),
                request.getCpfPaciente(),
                request.getIdEvento(),
                request.getValorMedicao(),
                OffsetDateTime.now(),
                request.getOrigemRegistro()
        );
        repository.save(registroMedicao);
        return registroMedicao;
    }

    private void notificaEventoRegistro(RegistroMedicao registroMedicao) {
        medicaoPublisher.enviarEventoDeMedicao(new MedicaoRealizadaEvent(
                registroMedicao.getId(),
                registroMedicao.getCpfPaciente(),
                registroMedicao.getIdEvento(),
                registroMedicao.getValorMedicao(),
                registroMedicao.getDataHora(),
                registroMedicao.getOrigemRegistro().toString()
        ));
    }
}
