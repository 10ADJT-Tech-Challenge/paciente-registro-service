package com.adjt.pacienteregistroservice.configuration;

import jakarta.servlet.*;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * Classe de filtro responsável por gerar um identificador único de rastreamento (traceId)
 * para cada requisição HTTP processada. O traceId é armazenado no MDC (Mapped Diagnostic Context),
 * permitindo que as mensagens de log durante a execução da requisição possam ser correlacionadas.
 * Ao final do processamento, o traceId é removido do MDC.
 */
@Component
public class TraceIdFilter implements Filter {
    public static final String MDC_TRACE_ID_KEY = "traceId";

    /**
     * Método que intercepta requisições HTTP e adiciona um identificador único de rastreamento (traceId)
     * no MDC (Mapped Diagnostic Context). Esse identificador auxilia no rastreamento de logs associados
     * a uma mesma requisição. O método também garante que o traceId seja removido ao fim do processamento.
     *
     * @param request  Objeto da requisição HTTP.
     * @param response Objeto da resposta HTTP.
     * @param chain    Filtro de cadeia para passar a requisição e resposta ao próximo filtro/método.
     * @throws IOException      em caso de erros de I/O durante o processamento.
     * @throws ServletException em caso de erros no ciclo de vida do servlet.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String traceId = UUID.randomUUID().toString().substring(0, 8);
        MDC.put(MDC_TRACE_ID_KEY, traceId);
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove(MDC_TRACE_ID_KEY);
        }
    }
}
