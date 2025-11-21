package com.caixa.invest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/perfil-risco")
public class PerfilRiscoController {

    @GET
    @Path("/{clienteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfilRisco(@PathParam("clienteId") int clienteId) {
        String json = """
        {
          "clienteId": %d,
          "perfil": "Moderado",
          "pontuacao": 65,
          "descricao": "Perfil equilibrado entre segurança e rentabilidade."
        }
        """.formatted(clienteId);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
}
