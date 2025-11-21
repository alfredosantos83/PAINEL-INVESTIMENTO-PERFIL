package com.caixa.invest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Path("/telemetria-volumes")
public class TelemetriaVolumesController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTelemetriaVolumes() {
        Map<String, Object> telemetria = new HashMap<>();
        List<Map<String, Object>> servicos = List.of(
            Map.of(
                "nome", "simular-investimento",
                "quantidadeChamadas", 120,
                "mediaTempoRespostaMs", 250
            ),
            Map.of(
                "nome", "perfil-risco",
                "quantidadeChamadas", 80,
                "mediaTempoRespostaMs", 180
            ),
            Map.of(
                "nome", "produtos-recomendados",
                "quantidadeChamadas", 60,
                "mediaTempoRespostaMs", 200
            ),
            Map.of(
                "nome", "investimentos",
                "quantidadeChamadas", 45,
                "mediaTempoRespostaMs", 220
            )
        );
        telemetria.put("servicos", servicos);
        telemetria.put("periodo", Map.of(
            "inicio", "2025-10-01",
            "fim", "2025-10-31"
        ));
        return Response.ok(telemetria).build();
    }
}
