package br.com.tiagoiwamoto.testautomate.adapter;

import br.com.tiagoiwamoto.testautomate.core.domain.HttpVerb;
import br.com.tiagoiwamoto.testautomate.core.port.ApiCallPort;
import br.com.tiagoiwamoto.testautomate.core.usecase.ApiCallDto;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.util.Map;

public class ApiCallAdapter implements ApiCallPort {

    private ApiCallAdapter(){}

    public static ApiCallAdapter of(){
        return new ApiCallAdapter();
    }


    @Override
    public JsonNode call(ApiCallDto apiCallDto) {

        JsonNode response;
        HttpResponse<JsonNode> serverResponse;
        switch (apiCallDto.getMethod()){
            case GET:
                serverResponse = Unirest.get(apiCallDto.getUrl())
                        .headers(apiCallDto.getHeaders())
                        .asJson();
                break;
            case POST:
                serverResponse = Unirest.post(apiCallDto.getUrl())
                        .headers(apiCallDto.getHeaders())
                        .body(apiCallDto.getBody())
                        .asJson();
                break;
            case PUT:
                serverResponse = Unirest.put(apiCallDto.getUrl())
                        .headers(apiCallDto.getHeaders())
                        .body(apiCallDto.getBody())
                        .asJson();
                break;
            case DELETE:
                serverResponse = Unirest.delete(apiCallDto.getUrl())
                        .headers(apiCallDto.getHeaders())
                        .asJson();
                break;
            case OPTIONS:
            default:
                serverResponse = Unirest.options(apiCallDto.getUrl())
                        .headers(apiCallDto.getHeaders())
                        .asJson();
                break;
        }
        response = serverResponse.getBody();
        return response;
    }
}
