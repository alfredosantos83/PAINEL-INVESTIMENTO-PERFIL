package com.caixa.invest.controller;

import java.util.Map;
import java.util.HashMap;

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

        Map<String, Object> perfilRisco = new HashMap<>();
        perfilRisco.put("clienteId", clienteId);
        perfilRisco.put("perfil", "Moderado");
        perfilRisco.put("pontuacao", 65);
        perfilRisco.put("descricao", "Perfil equilibrado entre segurança e rentabilidade.");
        return Response.ok(perfilRisco, MediaType.APPLICATION_JSON).build();
    }
}
