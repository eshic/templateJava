package br.com.projetoApiCWI.tests.healthcheck.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetHealthcheckRequest {



   @Step("Verificar se a API está OnLine")
    public Response statusAPI () {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("ping");

    }
}
