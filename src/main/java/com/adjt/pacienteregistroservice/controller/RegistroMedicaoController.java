package com.adjt.pacienteregistroservice.controller;

import com.adjt.pacienteregistroservice.dto.generated.RegistrosDiariosApi;
import com.adjt.pacienteregistroservice.dto.generated.model.RegistroRequest;
import com.adjt.pacienteregistroservice.dto.generated.model.RegistroResponse;
import com.adjt.pacienteregistroservice.service.RegistroMedicaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class RegistroMedicaoController implements RegistrosDiariosApi {
    private final RegistroMedicaoService registroMedicaoService;

    public RegistroMedicaoController(RegistroMedicaoService registroMedicaoService) {
        this.registroMedicaoService = registroMedicaoService;
    }

    @Override
    public ResponseEntity<RegistroResponse> criarRegistroMedicao(RegistroRequest registroRequest) {
        RegistroResponse registroResponse = registroMedicaoService.criarRegistroMedicao(registroRequest);
        return ResponseEntity.status(CREATED).body(registroResponse);
    }
}
