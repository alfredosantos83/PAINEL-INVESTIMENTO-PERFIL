package com.caixa.invest.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/simular-investimento")
public class SimularInvestimentoController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response simularInvestimento(String requestJson) {
        // Mock response conforme especificação
        String responseJson = """
        {
          "produtoValidado": {
            "id": 101,
            "nome": "CDB Caixa 2026",
            "tipo": "CDB",
            "rentabilidade": 0.12,
            "risco": "Baixo"
          },
          "resultadoSimulacao": {
            "valorFinal": 11200.00,
            "rentabilidadeEfetiva": 0.12,
            "prazoMeses": 12
          },
          "dataSimulacao": "2025-10-31T14:00:00Z"
        }
        """;
        return Response.ok(responseJson, MediaType.APPLICATION_JSON).build();
    }
}
