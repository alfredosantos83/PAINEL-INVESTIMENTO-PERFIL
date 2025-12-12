package com.caixa.invest.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

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

        List<Map<String, Object>> produtos = new ArrayList<>();

        Map<String, Object> produto1 = new HashMap<>();
        produto1.put("id", 101);
        produto1.put("nome", "CDB Caixa 2026");
        produto1.put("tipo", "CDB");
        produto1.put("rentabilidade", 0.12);
        produto1.put("risco", "Baixo");
        produtos.add(produto1);

        Map<String, Object> produto2 = new HashMap<>();
        produto2.put("id", 102);
        produto2.put("nome", "Fundo XPTO");
        produto2.put("tipo", "Fundo");
        produto2.put("rentabilidade", 0.18);
        produto2.put("risco", "Alto");
        produtos.add(produto2);

        return Response.ok(produtos, MediaType.APPLICATION_JSON).build();
    }
}
