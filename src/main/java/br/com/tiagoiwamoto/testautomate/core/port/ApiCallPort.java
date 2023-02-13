package br.com.tiagoiwamoto.testautomate.core.port;

import br.com.tiagoiwamoto.testautomate.core.domain.HttpVerb;
import br.com.tiagoiwamoto.testautomate.core.usecase.ApiCallDto;
import kong.unirest.JsonNode;

import java.util.Map;

public interface ApiCallPort {

    JsonNode call(ApiCallDto apiCallDto);

}
