/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import static java.lang.Thread.sleep;

@Path("acoes")
public class ClientesResource {

    ArrayList<Acao> acoes = new ArrayList<>();

    public ClientesResource() {
    }

    @GET
    @Produces({"application/json", "application/json"})
    public List<Acao> obtemTodos() {
        synchronized (acoes) {
            return acoes;
        }
    }

    @GET
    @Path("/getAcao/{sigla}")
    @Produces({"application/json", "application/json"})
    public Acao obtemCliente(@PathParam("sigla") String sigla) {
        synchronized (acoes) {
            Acao result = procuraAcao(sigla);
            return result;
        }
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response adicionaAcao(Acao acao) {
        synchronized (acoes) {
            for (Acao auxAcao : acoes) {
                if (auxAcao.getNome().equals(acao.getNome()) || auxAcao.getSigla().equals(acao.getSigla())) {
                    return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
                }
            }
            acoes.add(acao);
            acao.setValorAtual(acao.getValorAbertura());
            return Response.ok().build();
        }
    }

    @GET
    @Path("/maximaVariacao")
    @Produces("application/json")
    public Response getMaximaVariacao() {
        synchronized (acoes) {
            float variacao = 0;
            Acao acao = null;
            if (acoes.size() == 0) {
                return Response.ok(null).build();
            }
            for (int i = 0; i < acoes.size(); i++) {
                if (i == 0) {
                    variacao = acoes.get(i).getVariacao();
                    acao = acoes.get(i);
                } else {
                    if (variacao < acoes.get(i).getVariacao()) {
                        variacao = acoes.get(i).getVariacao();
                        acao = acoes.get(i);
                    }
                }
            }
            return Response.ok(acao).build();
        }
    }

    @PUT
    @Path("/atualizarAcao/{sigla}")
    @Consumes("application/json")
    public Response atualizarAcao(@PathParam("sigla") String sigla, Variacao variacao) throws InterruptedException {
        synchronized (acoes) {
            for (int i = 0; i < acoes.size(); i++) {
                if (acoes.get(i).getSigla().equals(sigla)) {
                    sleep(10000);
                    acoes.get(i).setValorAtual(variacao.getVariacao());
                    return Response.ok().build();
                }
            }
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }

    @DELETE
    @Path("/deleteAcao/{sigla}")
    public Response removeAcao(@PathParam("sigla") String sigla) {
        synchronized (acoes) {
            Acao result = procuraAcao(sigla);
            if (result == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                acoes.remove(result);
                return Response.ok().build();
            }
        }
    }

    public Acao procuraAcao(String sigla) {
        for (Acao acao : acoes) {
            if (acao.getSigla().equals(sigla)) {
                return acao;
            }
        }
        return null;
    }
}
