package com.caixa.invest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/telemetria")
public class TelemetriaController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTelemetria() {
        // TODO: Replace with actual service/repository logic
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> servicos = new ArrayList<>();

        Map<String, Object> servico1 = new HashMap<>();
        servico1.put("nome", "simular-investimento");
        servico1.put("quantidadeChamadas", 120);
        servico1.put("mediaTempoRespostaMs", 250);
        servicos.add(servico1);

        Map<String, Object> servico2 = new HashMap<>();
        servico2.put("nome", "perfil-risco");
        servico2.put("quantidadeChamadas", 80);
        servico2.put("mediaTempoRespostaMs", 180);
        servicos.add(servico2);

        response.put("servicos", servicos);
        Map<String, Object> periodo = new HashMap<>();
        periodo.put("inicio", "2025-10-01");
        periodo.put("fim", "2025-10-31");
        response.put("periodo", periodo);

        return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }
}
