package br.com.tiagoiwamoto.testautomate.core.usecase;

import br.com.tiagoiwamoto.testautomate.adapter.ApiCallAdapter;
import com.github.javafaker.Faker;
import kong.unirest.JsonNode;
import kong.unirest.JsonObjectMapper;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiRestUsecase {

    private ApiCallAdapter apiCallAdapter;

    private ApiRestUsecase(){
        this.apiCallAdapter = ApiCallAdapter.of();
    }

    public static ApiRestUsecase of(){
        return new ApiRestUsecase();
    }

    public List<Object> call(ApiCallDto apiCallDto){
        List<ApiCallDto> testStress = new ArrayList<>();
        List<Object> responses = new ArrayList<>();
        ObjectMapper mapper = new JsonObjectMapper();
        if(apiCallDto.getQuantidadeChamadas() == 1){
            Map<String, Object> body =  generate();
            ApiCallDto dto = ApiCallDto.of(apiCallDto.getUrl(), apiCallDto.getMethod(), body, apiCallDto.getHeaders());
            var response = this.apiCallAdapter.call(dto).getObject();
            Map<String, Object> map = mapper.readValue(response.toString(), Map.class);
            responses.add(map);
            return responses;
        }

        for(int chamada = 1; chamada <= apiCallDto.getQuantidadeChamadas(); chamada++){
            Map<String, Object> body =  generate();
            ApiCallDto dto = ApiCallDto.of(apiCallDto.getUrl(), apiCallDto.getMethod(), body, apiCallDto.getHeaders());
            testStress.add(dto);
        }

        if(apiCallDto.getChamadasParalelas()){
            testStress.parallelStream().forEach(test -> {
                var response = this.apiCallAdapter.call(test).getObject();
                Map<String, Object> map = mapper.readValue(response.toString(), Map.class);
                responses.add(map);
            });
        }else{
            testStress.forEach(test -> {
                var response = this.apiCallAdapter.call(test).getObject();
                Map<String, Object> map = mapper.readValue(response.toString(), Map.class);
                responses.add(map);
            });
        }

        return responses;

    }

    private Map<String, Object> generate(){
        var faker = Faker.instance();
        Map<String, Object> body = new HashMap<>();
        return body;
    }
}
