package com.caixa.invest.controller;

import com.caixa.invest.domain.Product;
import com.caixa.invest.domain.Simulacao;
import com.caixa.invest.repository.SimulacaoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/simular-investimento")
public class SimularInvestimentoController {


    @Inject
    SimulacaoRepository simulacaoRepository;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response simularInvestimento(com.caixa.invest.dto.request.SimulacaoRequest request) {
        // Buscar simulação existente em simulacoes.json pelo produto e data
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        try {
            java.nio.file.Path path = java.nio.file.Paths.get("simulacoes/simulacoes.json");
            java.util.List<java.util.Map<String, Object>> todasSimulacoes = new java.util.ArrayList<>();
            if (java.nio.file.Files.exists(path)) {
                todasSimulacoes = mapper.readValue(path.toFile(), new com.fasterxml.jackson.core.type.TypeReference<java.util.List<java.util.Map<String, Object>>>() {});
            }
            // Parâmetros de busca: produto e data (data pode ser só yyyy-MM-dd)
            final String produtoNome;
            if (request.getTipoProduto() != null) {
                // Buscar nome do produto pelo tipo
                String tipoProduto = request.getTipoProduto();
                String[] arquivos = {"produtos/produto_101.json", "produtos/produto_102.json", "produtos/produto_103.json"};
                String nomeEncontrado = null;
                for (String arq : arquivos) {
                    java.io.File f = new java.io.File(arq);
                    if (f.exists()) {
                        java.util.Map<String, Object> prod = mapper.readValue(f, new com.fasterxml.jackson.core.type.TypeReference<java.util.Map<String, Object>>(){});
                        if (tipoProduto.equalsIgnoreCase(String.valueOf(prod.get("tipo")))) {
                            nomeEncontrado = String.valueOf(prod.get("nome"));
                            break;
                        }
                    }
                }
                produtoNome = nomeEncontrado;
            } else {
                produtoNome = null;
            }
            // Não existe getDataSimulacao em SimulacaoRequest, então não filtra por data
            final String dataBusca = null;
            java.util.Optional<java.util.Map<String, Object>> simulacaoExistente = todasSimulacoes.stream()
                .filter(s -> (produtoNome == null || produtoNome.equalsIgnoreCase(String.valueOf(s.get("produto")))))
                .filter(s -> {
                    if (dataBusca == null || dataBusca.isEmpty()) return true;
                    String dataSimulacao = String.valueOf(s.get("dataSimulacao"));
                    return dataSimulacao.startsWith(dataBusca);
                })
                .findFirst();
            if (simulacaoExistente.isPresent()) {
                return Response.ok(simulacaoExistente.get(), MediaType.APPLICATION_JSON).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Simulação não encontrada para os parâmetros informados").build();
            }
        } catch (Exception e) {
            return Response.status(500).entity("Erro ao buscar simulação: " + e.getMessage()).build();
        }
    }
}
