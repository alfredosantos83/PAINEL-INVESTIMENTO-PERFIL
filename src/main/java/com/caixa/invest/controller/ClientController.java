package com.caixa.invest.controller;

import com.caixa.invest.domain.Client;
import com.caixa.invest.repository.ClientRepository;
import com.caixa.invest.util.ClientFileUtil;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientController {

    @Inject
    ClientRepository clientRepository;

    @POST
    @Transactional
    public Response createClient(Client client) {
        clientRepository.persist(client);
        try {
            ClientFileUtil.saveClientToFile(client);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Cliente salvo no banco, mas falha ao salvar arquivo: " + e.getMessage()).build();
        }
        return Response.ok(client).build();
    }
}
