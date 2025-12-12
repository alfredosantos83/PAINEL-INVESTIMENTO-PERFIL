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
    public Response getSimulacoesPorProdutoDia() {
        var simulacoes = simulacaoRepository.listAll();
        var agrupado = simulacoes.stream()
            .collect(Collectors.groupingBy(s -> s.getProduto().getNome() + "|" + s.getDataSimulacao().toLocalDate()));
        List<Map<String, Object>> result = new ArrayList<>();
        for (var entry : agrupado.entrySet()) {
            String[] chave = entry.getKey().split("\\|");
            String produto = chave[0];
            String data = chave[1];
            List<Simulacao> sims = entry.getValue();
            double media = sims.stream().mapToDouble(s -> s.getValorFinal().doubleValue()).average().orElse(0);
            Map<String, Object> item = new HashMap<>();
            item.put("produto", produto);
            item.put("data", data);
            item.put("quantidadeSimulacoes", sims.size());
            item.put("mediaValorFinal", media);
            result.add(item);
        }
        return Response.ok(result, MediaType.APPLICATION_JSON).build();
    }
}
