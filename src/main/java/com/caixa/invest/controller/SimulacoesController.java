package com.caixa.invest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/simulacoes")
public class SimulacoesController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimulacoes() {
        // TODO: Replace with actual service/repository logic
        List<Map<String, Object>> simulacoes = new ArrayList<>();

        Map<String, Object> simulacao1 = new HashMap<>();
        simulacao1.put("id", 1);
        simulacao1.put("clienteId", 123);
        simulacao1.put("produto", "CDB Caixa 2026");
        simulacao1.put("valorInvestido", 10000.00);
        simulacao1.put("valorFinal", 11200.00);
        simulacao1.put("prazoMeses", 12);
        simulacao1.put("dataSimulacao", "2025-10-31T14:00:00Z");
        simulacoes.add(simulacao1);

        Map<String, Object> simulacao2 = new HashMap<>();
        simulacao2.put("id", 2);
        simulacao2.put("clienteId", 123);
        simulacao2.put("produto", "Fundo XPTO");
        simulacao2.put("valorInvestido", 5000.00);
        simulacao2.put("valorFinal", 5800.00);
        simulacao2.put("prazoMeses", 6);
        simulacao2.put("dataSimulacao", "2025-09-15T10:30:00Z");
        simulacoes.add(simulacao2);

        return Response.ok(simulacoes, MediaType.APPLICATION_JSON).build();
    }
}
