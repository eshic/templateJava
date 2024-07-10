package br.com.projetoApiCWI.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    @Step("Deletar uma reserva específica:")
    public Response deletarReservaEspecifica(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("booking/" + id);

    }

    @Step("Deletar uma reserva específica sem autorização ")
    public Response deletarReservaSemAutorizacao(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQX")
                .when()
                .delete("booking/" + id);

    }
    @Step("Deletar uma reserva semo campo authorization ")
    public Response deletarReservaSemoCampoAutorizacao(int id) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .delete("booking/" + id);

    }



}
