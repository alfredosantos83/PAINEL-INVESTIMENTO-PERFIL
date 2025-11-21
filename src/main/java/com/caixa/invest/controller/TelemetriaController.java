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
        String json = """
        {
          "servicos": [
            {
              "nome": "simular-investimento",
              "quantidadeChamadas": 120,
              "mediaTempoRespostaMs": 250
            },
            {
              "nome": "perfil-risco",
              "quantidadeChamadas": 80,
              "mediaTempoRespostaMs": 180
            }
          ],
          "periodo": {
            "inicio": "2025-10-01",
            "fim": "2025-10-31"
          }
        }
        """;
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
