package br.com.joice.api.support;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Cliente HTTP centralizado para a API ServeRest.
 * A URL base pode ser sobrescrita com -Dbase.url=...
 */
public class ApiClient {

    private static final String BASE_URL = System.getProperty("base.url", "https://serverest.dev");

    private final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .log(LogDetail.URI)
            .build();

    public Response post(String path, Object body) {
        return given().spec(spec).body(body).when().post(path);
    }

    public Response post(String path, Object body, String token) {
        return given().spec(spec).header("Authorization", token).body(body).when().post(path);
    }

    public Response get(String path) {
        return given().spec(spec).when().get(path);
    }

    public Response delete(String path) {
        return given().spec(spec).when().delete(path);
    }

    public Response delete(String path, String token) {
        return given().spec(spec).header("Authorization", token).when().delete(path);
    }
}
