package com.caixa.invest.controller;


import com.caixa.invest.domain.Simulacao;
import com.caixa.invest.repository.SimulacaoRepository;
import com.caixa.invest.mapper.SimulacaoMapper;
import jakarta.inject.Inject;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/simulacoes")
public class SimulacoesController {

    @Inject
    SimulacaoRepository simulacaoRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimulacoes(@jakarta.ws.rs.QueryParam("clienteId") Long clienteId) {
        var stream = simulacaoRepository.listAll().stream();
        if (clienteId != null) {
            stream = stream.filter(s -> s.getClienteId().equals(clienteId));
        }
        var simulacoes = stream
            .map(SimulacaoMapper::toEnvelope)
            .toList();
        return Response.ok(simulacoes, MediaType.APPLICATION_JSON).build();
    }
}
