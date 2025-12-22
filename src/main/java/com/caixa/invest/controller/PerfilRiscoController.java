
package com.caixa.invest.controller;

import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/perfil-risco")
public class PerfilRiscoController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfilRisco() {
        Map<String, Object> perfilRisco = new HashMap<>();
        perfilRisco.put("clienteId", 1);
        perfilRisco.put("perfil", "MODERADO");
        perfilRisco.put("pontuacao", 55);
        perfilRisco.put("descricao", "Perfil equilibrado entre segurança e rentabilidade.");
        return Response.ok(perfilRisco, MediaType.APPLICATION_JSON).build();
    }
}
