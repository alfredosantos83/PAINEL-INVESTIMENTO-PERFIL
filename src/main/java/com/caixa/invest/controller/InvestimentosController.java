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
        String json = """
        [
          {
            "id": 1,
            "tipo": "CDB",
            "valor": 5000.00,
            "rentabilidade": 0.12,
            "data": "2025-01-15"
          },
          {
            "id": 2,
            "tipo": "Fundo Multimercado",
            "valor": 3000.00,
            "rentabilidade": 0.08,
            "data": "2025-03-10"
          }
        ]
        """;
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
