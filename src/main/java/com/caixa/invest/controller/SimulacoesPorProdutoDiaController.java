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
        // TODO: Replace with actual service/repository logic
        // Example dynamic response using domain entities
        List<Map<String, Object>> result = new ArrayList<>();
        // Simulate fetching from database
        Map<String, Object> item1 = new HashMap<>();
        item1.put("produto", "CDB Caixa 2026");
        item1.put("data", "2025-10-30");
        item1.put("quantidadeSimulacoes", 15);
        item1.put("mediaValorFinal", 11050.00);
        result.add(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("produto", "Fundo XPTO");
        item2.put("data", "2025-10-30");
        item2.put("quantidadeSimulacoes", 8);
        item2.put("mediaValorFinal", 5700.00);
        result.add(item2);

        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
}
