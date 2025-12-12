package com.caixa.invest.controller;

import com.caixa.invest.domain.Product;
import com.caixa.invest.repository.ProductRepository;
import com.caixa.invest.domain.Simulacao;
import com.caixa.invest.repository.SimulacaoRepository;
import com.caixa.invest.domain.Telemetria;
import com.caixa.invest.repository.TelemetriaRepository;
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
    ProductRepository productRepository;

    @Inject
    SimulacaoRepository simulacaoRepository;

    @Inject
    TelemetriaRepository telemetriaRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response simularInvestimento(com.caixa.invest.dto.request.SimulacaoRequest request) {
        Product.TipoProduto tipoProdutoEnum;
        try {
            tipoProdutoEnum = Product.TipoProduto.valueOf(request.getTipoProduto());
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Tipo de produto inválido").build();
        }
        long inicio = System.currentTimeMillis();
        Product produto = productRepository.find("tipo = ?1 and ativo = true", tipoProdutoEnum)
            .stream()
            .filter(p -> request.getValor().compareTo(p.getValorMinimo()) >= 0 && request.getValor().compareTo(p.getValorMaximo()) <= 0)
            .filter(p -> request.getPrazoMeses() >= p.getPrazoMinimoMeses() && request.getPrazoMeses() <= p.getPrazoMaximoMeses())
            .findFirst().orElse(null);
        if (produto == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Nenhum produto encontrado para os parâmetros informados").build();
        }
        java.math.BigDecimal valorFinal = request.getValor().multiply(java.math.BigDecimal.ONE.add(produto.getRentabilidade()));
        java.time.LocalDateTime dataSimulacao = java.time.LocalDateTime.now();
        // Persistir simulação
        Simulacao simulacao = Simulacao.builder()
            .clienteId(request.getClienteId())
            .produto(produto)
            .valorInvestido(request.getValor())
            .valorFinal(valorFinal)
            .prazoMeses(request.getPrazoMeses())
            .dataSimulacao(dataSimulacao)
            .build();
        simulacaoRepository.persist(simulacao);

        // Registrar telemetria
        long fim = System.currentTimeMillis();
        String servico = "simular-investimento";
        java.time.LocalDateTime agora = java.time.LocalDateTime.now();
        Telemetria telemetria = telemetriaRepository.find("servico = ?1 and periodoInicio <= ?2 and periodoFim >= ?2", servico, agora)
            .firstResult();
        if (telemetria == null) {
            telemetria = Telemetria.builder()
                .servico(servico)
                .quantidadeChamadas(1)
                .somaTempoRespostaMs(fim - inicio)
                .periodoInicio(agora.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0))
                .periodoFim(agora.withDayOfMonth(agora.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(999999999))
                .build();
            telemetriaRepository.persist(telemetria);
        } else {
            telemetria.setQuantidadeChamadas(telemetria.getQuantidadeChamadas() + 1);
            telemetria.setSomaTempoRespostaMs(telemetria.getSomaTempoRespostaMs() + (fim - inicio));
        }

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
