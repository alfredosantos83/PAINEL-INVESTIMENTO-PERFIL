package com.caixa.invest.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/simulacoes")
public class SimulacoesController {

    @GET
    @Path("/por-produto-dia")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimulacoesPorProdutoDia(@jakarta.ws.rs.core.Context jakarta.ws.rs.core.UriInfo uriInfo) {
        // Espera-se que o usuário envie: ?CDB%20Caixa%202026=2025-10-30
        java.util.Map<String, java.util.List<String>> params = uriInfo.getQueryParameters();
        if (params.isEmpty()) {
            return Response.status(400).entity("Informe o produto como chave e a data como valor nos Query Params").build();
        }
        String produto = params.keySet().iterator().next();
        String data = params.get(produto).get(0);
        if (produto == null || produto.isEmpty() || data == null || data.isEmpty()) {
            return Response.status(400).entity("Parâmetros obrigatórios ausentes").build();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> todasSimulacoes = mapper.readValue(
                Paths.get("simulacoes/simulacoes.json").toFile(),
                new TypeReference<List<Map<String, Object>>>() {}
            );
            List<Map<String, Object>> filtradas = todasSimulacoes.stream()
                .filter(s -> produto.equalsIgnoreCase(String.valueOf(s.get("produto"))))
                .filter(s -> {
                    String dataSimulacao = String.valueOf(s.get("dataSimulacao"));
                    return dataSimulacao.startsWith(data);
                })
                .toList();
            int quantidadeSimulacoes = filtradas.size();
            double mediaValorFinal = 0.0;
            if (quantidadeSimulacoes > 0) {
                mediaValorFinal = filtradas.stream()
                    .mapToDouble(s -> Double.parseDouble(s.get("valorFinal").toString()))
                    .average().orElse(0.0);
            }
            java.util.Map<String, Object> resposta = new java.util.HashMap<>();
            resposta.put("produto", produto);
            resposta.put("data", data);
            resposta.put("quantidadeSimulacoes", quantidadeSimulacoes);
            resposta.put("mediaValorFinal", String.format("%.2f", mediaValorFinal));
            return Response.ok(resposta, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao ler simulacoes.json: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimulacoes(
        @jakarta.ws.rs.QueryParam("clienteId") Long clienteId,
        @jakarta.ws.rs.QueryParam("produto") String produto,
        @jakarta.ws.rs.QueryParam("data") String data
    ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> todasSimulacoes = mapper.readValue(
                Paths.get("simulacoes/simulacoes.json").toFile(),
                new TypeReference<List<Map<String, Object>>>() {}
            );
            List<Map<String, Object>> filtradas = todasSimulacoes;
            if (clienteId != null) {
                filtradas = filtradas.stream()
                    .filter(s -> clienteId.equals(Long.valueOf(s.get("clienteId").toString())))
                    .toList();
            }
            if (produto != null && !produto.isEmpty()) {
                filtradas = filtradas.stream()
                    .filter(s -> produto.equalsIgnoreCase(String.valueOf(s.get("produto"))))
                    .toList();
            }
            if (data != null && !data.isEmpty()) {
                filtradas = filtradas.stream()
                    .filter(s -> {
                        String dataSimulacao = String.valueOf(s.get("dataSimulacao"));
                        // Aceita tanto data completa quanto só a data (yyyy-MM-dd)
                        return dataSimulacao.startsWith(data);
                    })
                    .toList();
            }
            return Response.ok(filtradas, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao ler simulacoes.json: " + e.getMessage()).build();
        }
    }
}
