package com.caixa.invest.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/simular-investimento")
public class SimularInvestimentoController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response simularInvestimento(com.caixa.invest.dto.request.SimulacaoRequest request) {
        // Buscar produto pelo tipo
        com.caixa.invest.domain.Product.TipoProduto tipoProdutoEnum;
        try {
            tipoProdutoEnum = com.caixa.invest.domain.Product.TipoProduto.valueOf(request.getTipoProduto());
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Tipo de produto inválido").build();
        }
        // Mock busca do produto (em produção, buscar do banco)
        com.caixa.invest.domain.Product produto = new com.caixa.invest.domain.Product();
        produto.setId(101L);
        produto.setNome("CDB Caixa 2026");
        produto.setTipo(tipoProdutoEnum);
        produto.setRentabilidade(new java.math.BigDecimal("0.12"));
        produto.setRisco(com.caixa.invest.domain.Product.NivelRisco.BAIXO);

        // Calcular resultado da simulação
        java.math.BigDecimal valorFinal = request.getValor().multiply(java.math.BigDecimal.ONE.add(produto.getRentabilidade()));
        java.time.LocalDateTime dataSimulacao = java.time.LocalDateTime.now();

        // Montar resposta
        String responseJson = String.format("""
        {
          "produtoValidado": {
            "id": %d,
            "nome": "%s",
            "tipo": "%s",
            "rentabilidade": %.2f,
            "risco": "%s"
          },
          "resultadoSimulacao": {
            "valorFinal": %.2f,
            "rentabilidadeEfetiva": %.2f,
            "prazoMeses": %d
          },
          "dataSimulacao": "%s"
        }
        """,
            produto.getId(),
            produto.getNome(),
            produto.getTipo().name(),
            produto.getRentabilidade().doubleValue(),
            produto.getRisco().getDescricao(),
            valorFinal.doubleValue(),
            produto.getRentabilidade().doubleValue(),
            request.getPrazoMeses(),
            dataSimulacao.toString()
        );
        return Response.ok(responseJson, MediaType.APPLICATION_JSON).build();
    }
}
