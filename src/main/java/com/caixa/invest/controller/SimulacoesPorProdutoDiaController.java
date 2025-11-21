package com.caixa.invest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/simulacoes/por-produto-dia")
public class SimulacoesPorProdutoDiaController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimulacoesPorProdutoDia() {
        String json = """
        [
          {
            "produto": "CDB Caixa 2026",
            "data": "2025-10-30",
            "quantidadeSimulacoes": 15,
            "mediaValorFinal": 11050.00
          },
          {
            "produto": "Fundo XPTO",
            "data": "2025-10-30",
            "quantidadeSimulacoes": 8,
            "mediaValorFinal": 5700.00
          }
        ]
        """;
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
