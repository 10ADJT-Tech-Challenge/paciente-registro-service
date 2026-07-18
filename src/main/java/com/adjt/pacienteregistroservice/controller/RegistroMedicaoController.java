package com.adjt.pacienteregistroservice.controller;

import com.adjt.pacienteregistroservice.dto.generated.RegistrosDiariosApi;
import com.adjt.pacienteregistroservice.dto.generated.model.RegistroRequest;
import com.adjt.pacienteregistroservice.dto.generated.model.RegistroResponse;
import com.adjt.pacienteregistroservice.service.RegistroMedicaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
public class RegistroMedicaoController implements RegistrosDiariosApi {
    private final RegistroMedicaoService registroMedicaoService;

    public RegistroMedicaoController(RegistroMedicaoService registroMedicaoService) {
        this.registroMedicaoService = registroMedicaoService;
    }

    @Override
    public ResponseEntity<RegistroResponse> criarRegistroMedicao(RegistroRequest registroRequest) {
        log.info("Recebendo nova medição para o evento: {}", registroRequest.getIdEvento());

        try {
            RegistroResponse registroResponse = registroMedicaoService.criarRegistroMedicao(registroRequest);
            return ResponseEntity.status(CREATED).body(registroResponse);
        } catch (Exception e) {
            log.error("Erro ao salvar a medição do paciente", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
