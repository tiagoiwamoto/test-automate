package br.com.tiagoiwamoto.testautomate.entrypoint;

import br.com.tiagoiwamoto.testautomate.adapter.KafkaPublisherAdapter;
import br.com.tiagoiwamoto.testautomate.core.usecase.ApiCallDto;
import br.com.tiagoiwamoto.testautomate.core.usecase.ApiRestUsecase;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path(value = "/automate/apis")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApiAutomateResource {

    private final ApiRestUsecase apiRestUsecase;
    @Inject
    private KafkaPublisherAdapter kafkaPublisherAdapter;

    public ApiAutomateResource() {
        this.apiRestUsecase = ApiRestUsecase.of();
    }

    @POST
    public Response index(ApiCallDto apiCallDto){
        var response = this.apiRestUsecase.call(apiCallDto);
        return Response.ok(response).header("content-type", "application/json").build();
    }

    @GET
    public Response sendMessage(){
        this.kafkaPublisherAdapter.send(UUID.randomUUID().toString());
        return Response.ok().build();
    }

}
