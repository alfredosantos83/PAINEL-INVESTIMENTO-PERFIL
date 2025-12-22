package com.caixa.invest.controller;


import com.caixa.invest.domain.Simulacao;
import com.caixa.invest.repository.SimulacaoRepository;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/simulacoes/por-produto-dia")
public class SimulacoesPorProdutoDiaController {

    @Inject
    SimulacaoRepository simulacaoRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimulacoesPorProdutoDia(@jakarta.ws.rs.QueryParam("data") String data,
                                               @jakarta.ws.rs.QueryParam("produtos") String produtos) {
        if (data == null || produtos == null || produtos.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Parâmetros 'data' e 'produtos' são obrigatórios.").build();
        }
        var nomesProdutos = List.of(produtos.split(","));
        var simulacoes = simulacaoRepository.listAll().stream()
            .filter(s -> s.getDataSimulacao().toLocalDate().toString().equals(data))
            .filter(s -> nomesProdutos.contains(s.getProduto().getNome()))
            .collect(Collectors.toList());

        var agrupado = simulacoes.stream()
            .collect(Collectors.groupingBy(s -> s.getProduto().getNome()));

        List<Map<String, Object>> result = new ArrayList<>();
        for (var entry : agrupado.entrySet()) {
            var lista = entry.getValue();
            double media = lista.stream().mapToDouble(s -> s.getValorFinal().doubleValue()).average().orElse(0.0);
            Map<String, Object> item = new HashMap<>();
            item.put("produto", entry.getKey());
            item.put("data", data);
            item.put("quantidadeSimulacoes", lista.size());
            item.put("mediaValorFinal", Math.round(media * 100.0) / 100.0);
            result.add(item);
        }
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
}
