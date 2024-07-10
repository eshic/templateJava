package br.com.projetoApiCWI.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    @Step("Criar uma nova reserva")
    public Response novaReserva() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        payload.put("firstname", "Joelma");
        payload.put("lastname", "Prestes");
        payload.put("totalprice", "ddd");
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");

    }

    @Step("Criar uma  reserva com payload inválido")
    public Response novaReservaInvalida() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "5");
        payload.put("firstname", "Joelma");
        payload.put("bookingdates", bookingDates);

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }

    @Step("Criar uma  reserva com  parâmetros adicionais no payload")
    public Response novoPayloadComParametroAdicional() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        payload.put("firstname", "Joelma");
        payload.put("lastname", "Prestes");
        payload.put("totalprice", "555");
        payload.put("depositpaid", "true");
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");
        payload.put("additionalneeds", "lunch");
        payload.put("campo adicional", "campo a mais");

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking");
    }

    @Step("Criar uma nova reserva")
    public Response retornar418() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");
        payload.put("firstname","Ronaldo");
        payload.put("lastname", "Fenomeno");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds","Breakfast");

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "/***")
                .when()
                .body(payload.toString())
                .post("booking");

    }
}
