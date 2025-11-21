package com.caixa.invest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/produtos-recomendados")
public class ProdutosRecomendadosController {

    @GET
    @Path("/{perfil}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProdutosRecomendados(@PathParam("perfil") String perfil) {
        String json = """
        [
          {
            "id": 101,
            "nome": "CDB Caixa 2026",
            "tipo": "CDB",
            "rentabilidade": 0.12,
            "risco": "Baixo"
          },
          {
            "id": 102,
            "nome": "Fundo XPTO",
            "tipo": "Fundo",
            "rentabilidade": 0.18,
            "risco": "Alto"
          }
        ]
        """;
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
