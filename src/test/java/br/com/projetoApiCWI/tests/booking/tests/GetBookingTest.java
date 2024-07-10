package br.com.projetoApiCWI.tests.booking.tests;

import br.com.projetoApiCWI.runners.E2e;
import br.com.projetoApiCWI.suites.Acceptance;
import br.com.projetoApiCWI.suites.Contract;
import br.com.projetoApiCWI.tests.base.tests.BaseTest;
import br.com.projetoApiCWI.tests.booking.requests.GetBookingRequest;
import br.com.projetoApiCWI.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.filter.log.LogDetail;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das Reservas")
    public void validarIdsDasReservas() throws Exception {
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato de retorno das listas de reservas")
    public void garantirContratoListaReserva() throws Exception {
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema
                        (new File(Utils.getContractsBasePath("booking","bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato de retorno das uma reserva específica")
    public void  garantirContratoReservaEspecifica() throws  Exception {

        int primeiroID =getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");

        getBookingRequest.reservaEspecifica(primeiroID).then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema
                        (new File(Utils.getContractsBasePath("booking","bookingIds"))));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das Reservas")
    public void listarIdDeReservaEspecifica() throws Exception {
        int primeiroID =getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");

        getBookingRequest.reservaEspecifica(primeiroID).then()
                .assertThat()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das reservas através do filtro firstname")
    public void listarIdDeReservaPeloPrimeiroNome() throws Exception {
        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");
        String primeiroNome = getBookingRequest.reservaEspecifica(primeiroID).then()
                .statusCode(200).extract().path("firstname");

        getBookingRequest.listarIdsDeReservasComUmFiltro("firstname", primeiroNome).then()
                .assertThat()
                .body("bookingid", hasItem(primeiroID));

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das reservas através do filtro lastname")
    public void listarIdDeReservaPeloSobrenome() throws Exception {
        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");
        String sobrenome = getBookingRequest.reservaEspecifica(primeiroID).then()
                .statusCode(200).extract().path("lastname");

        getBookingRequest.listarIdsDeReservasComUmFiltro("lastname", sobrenome).then()
                .assertThat()
                .body("bookingid", hasItem(primeiroID));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das reservas através do filtro checkin")
    public void listarIdDeReservaPeloCheckin() throws Exception {
        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");
        String checkin = getBookingRequest.reservaEspecifica(primeiroID).then()
                .statusCode(200).extract().path("bookingdates.checkin");

        getBookingRequest.listarIdsDeReservasComUmFiltro("checkin", checkin).then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das reservas através do filtro checkout")
    public void listarIdDeReservaPeloCheckout() throws Exception {
        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");
        String checkout = getBookingRequest.reservaEspecifica(primeiroID).then()
                .statusCode(200).extract().path("bookingdates.checkout");

        getBookingRequest.listarIdsDeReservasComUmFiltro("checkout", checkout).then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category(Acceptance.class)
        @DisplayName("Listar IDs das reservas utilizando os filtros checkin e checkout")
        public void listarIdDeReservaPorDoisFiltros() throws Exception {
            int primeiroID = getBookingRequest.allBookings().then()
                    .statusCode(200).extract().path("[0].bookingid");
            String checkout = getBookingRequest.reservaEspecifica(primeiroID).then()
                    .statusCode(200).extract().path("bookingdates.checkout");
            String checkin = getBookingRequest.reservaEspecifica(primeiroID).then()
                    .statusCode(200).extract().path("bookingdates.checkin");

            getBookingRequest.listarIdsDeReservasDoisFiltros("checkout", checkout,
                    "checkin", checkin).then()
                    .statusCode(200)
                    .time(lessThan(4L), TimeUnit.SECONDS)
                    .body("size()", greaterThan(0));// Teste está falhando pq o body está voltando
            //vazio, quando na realidade todos os ids possuem a mesma data de checkin e checkout
        }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das reservas utilizando os filtros firstname, checkin e checkout")
    public void listarIdDeReservaPorTresFiltros() throws Exception {
        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");
        String primeiroNome = getBookingRequest.reservaEspecifica(primeiroID).then()
                .statusCode(200).extract().path("firstname");
        String checkout = getBookingRequest.reservaEspecifica(primeiroID).then()
                .statusCode(200).extract().path("bookingdates.checkout");
        String checkin = getBookingRequest.reservaEspecifica(primeiroID).then()
                .statusCode(200).extract().path("bookingdates.checkin");

        getBookingRequest.listarIdsDeReservasTresFiltros("firstname", primeiroNome,
                "checkout", checkout,
                "checkin", checkin)
                .then()
                .statusCode(200)
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));// Teste está falhando pq o body está voltando
        //vazio, quando na realidade deveria voltar ao menos 01 id, que foi onde buscamos os dados
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Retornar Status 500 quando envio filtro mal formatado")
    public void retornarStatus500() throws Exception {
        getBookingRequest.enviarFiltroMalFormatado("checkout", "12H^").then()
                .statusCode(500);
    }











}
