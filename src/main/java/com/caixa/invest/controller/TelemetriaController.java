package com.caixa.invest.controller;


import com.caixa.invest.domain.Telemetria;
import com.caixa.invest.repository.TelemetriaRepository;
import jakarta.inject.Inject;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/telemetria")
public class TelemetriaController {

    @Inject
    TelemetriaRepository telemetriaRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTelemetria() {
        List<Telemetria> telemetrias = telemetriaRepository.listAll();
        List<Map<String, Object>> servicos = telemetrias.stream().map(t -> {
            Map<String, Object> m = new HashMap<>();
            m.put("nome", t.getServico());
            m.put("quantidadeChamadas", t.getQuantidadeChamadas());
            m.put("mediaTempoRespostaMs", (int) Math.round(t.getMediaTempoRespostaMs()));
            return m;
        }).collect(Collectors.toList());
        Map<String, Object> response = new HashMap<>();
        response.put("servicos", servicos);
        // Determinar o período comum (maior início e menor fim)
        if (!telemetrias.isEmpty()) {
            String inicio = telemetrias.stream().map(t -> t.getPeriodoInicio().toLocalDate().toString())
                .min(String::compareTo).orElse("");
            String fim = telemetrias.stream().map(t -> t.getPeriodoFim().toLocalDate().toString())
                .max(String::compareTo).orElse("");
            Map<String, Object> periodo = new HashMap<>();
            periodo.put("inicio", inicio);
            periodo.put("fim", fim);
            response.put("periodo", periodo);
        }
        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }
}
