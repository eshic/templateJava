package br.com.projetoApiCWI.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Buscar todas as reservas")
    public Response allBookings() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");

    }

   @Step ("Buscar uma reserva específica")
    public Response reservaEspecifica(int id){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking/"+id);



   }

   @Step ("Listar Ids de reservas utilizando um filtro ")
    public Response listarIdsDeReservasComUmFiltro(String filtro, String valor){
        return given()
                .queryParam(filtro, valor)
                .when()
                .get("booking");

   }

    @Step ("Listar Ids de reservas utilizando dois filtros")
    public Response listarIdsDeReservasDoisFiltros(String filtro1, String valor1,
                                                   String filtro2, String valor2){
        return given()
                .queryParam(filtro1, valor1)
                .queryParam(filtro2, valor2)
                .when()
                .get("booking");

    }

    @Step ("Listar Ids de reservas utilizando três filtros")
    public Response listarIdsDeReservasTresFiltros(String filtro1, String valor1,
                                                   String filtro2, String valor2,
                                                   String filtro3, String valor3){
        return given()
                .queryParam(filtro1, valor1)
                .queryParam(filtro2, valor2)
                .queryParam(filtro3, valor3)
                .when()
                .get("booking");

    }

    @Step ("Enviar filtro mal formatado")
    public Response enviarFiltroMalFormatado (String filtro, String valor){
        return given()
                .queryParam(filtro, valor)
                .when()
                .get("booking");

    }

}
