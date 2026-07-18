package com.adjt.pacienteregistroservice.service;

import com.adjt.pacienteregistroservice.dto.MedicaoRealizadaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.adjt.pacienteregistroservice.configuration.RabbitMQConfig.EXCHANGE_NAME;

@Slf4j
@Service
public class MedicaoPublisherService {
    private final RabbitTemplate rabbitTemplate;

    private static final String ROUTING_KEY = "medicao.realizada";

    public MedicaoPublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarEventoDeMedicao(MedicaoRealizadaEvent evento) {
        log.info("Enviando evento de medição para o tópico. ID medição: {}", evento.id());

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, evento);

        log.info("Evento publicado com sucesso no Tópico: {} e rota: {}",
                EXCHANGE_NAME, ROUTING_KEY);
    }
}
