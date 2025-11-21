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
        String json = """
        [
            {
                "id": 1,
                "clienteId": 123,
                "produto": "CDB Caixa 2026",
                "valorInvestido": 10000.00,
                "valorFinal": 11200.00,
                "prazoMeses": 12,
                "dataSimulacao": "2025-10-31T14:00:00Z"
            },
            {
                "id": 2,
                "clienteId": 123,
                "produto": "Fundo XPTO",
                "valorInvestido": 5000.00,
                "valorFinal": 5800.00,
                "prazoMeses": 6,
                "dataSimulacao": "2025-09-15T10:30:00Z"
            }
        ]
        """;
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
