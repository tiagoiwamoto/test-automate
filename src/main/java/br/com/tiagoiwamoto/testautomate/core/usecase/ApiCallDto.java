package br.com.tiagoiwamoto.testautomate.core.usecase;

import br.com.tiagoiwamoto.testautomate.core.domain.HttpVerb;

import java.util.Map;

public class ApiCallDto {

    private String url;
    private HttpVerb method;
    private Map<String, Object> body;
    private Map<String, String> headers;
    Long quantidadeChamadas;
    Boolean chamadasParalelas;

    public ApiCallDto() {
    }

    private ApiCallDto(String url, HttpVerb method, Map<String, Object> body, Map<String, String> headers) {
        this.url = url;
        this.method = method;
        this.body = body;
        this.headers = headers;
    }

    public static ApiCallDto of(){
        return new ApiCallDto();
    }

    public static ApiCallDto of(String url, HttpVerb method, Map<String, Object> body, Map<String, String> headers){
        return new ApiCallDto(url, method, body, headers);
    }

    public String getUrl() {
        return url;
    }

    public HttpVerb getMethod() {
        return method;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ApiCallDto{");
        sb.append("url='").append(url).append('\'');
        sb.append(", method=").append(method);
        sb.append(", body=").append(body);
        sb.append(", headers=").append(headers);
        sb.append('}');
        return sb.toString();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(HttpVerb method) {
        this.method = method;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Long getQuantidadeChamadas() {
        return quantidadeChamadas;
    }

    public void setQuantidadeChamadas(Long quantidadeChamadas) {
        this.quantidadeChamadas = quantidadeChamadas;
    }

    public Boolean getChamadasParalelas() {
        return chamadasParalelas;
    }

    public void setChamadasParalelas(Boolean chamadasParalelas) {
        this.chamadasParalelas = chamadasParalelas;
    }
}
