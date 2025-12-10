package com.caixa.invest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/investimentos")
public class InvestimentosController {

    @GET
    @Path("/{clienteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInvestimentos(@PathParam("clienteId") int clienteId) {
        // TODO: Replace with actual service/repository logic
        List<Map<String, Object>> investimentos = new ArrayList<>();

        Map<String, Object> investimento1 = new HashMap<>();
        investimento1.put("id", 1);
        investimento1.put("tipo", "CDB");
        investimento1.put("valor", 5000.00);
        investimento1.put("rentabilidade", 0.12);
        investimento1.put("data", "2025-01-15");
        investimentos.add(investimento1);

        Map<String, Object> investimento2 = new HashMap<>();
        investimento2.put("id", 2);
        investimento2.put("tipo", "Fundo Multimercado");
        investimento2.put("valor", 3000.00);
        investimento2.put("rentabilidade", 0.08);
        investimento2.put("data", "2025-03-10");
        investimentos.add(investimento2);

        return Response.ok(investimentos, MediaType.APPLICATION_JSON).build();
    }
}
