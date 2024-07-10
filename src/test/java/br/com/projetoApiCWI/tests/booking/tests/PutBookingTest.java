package br.com.projetoApiCWI.tests.booking.tests;

import br.com.projetoApiCWI.runners.E2e;
import br.com.projetoApiCWI.suites.Acceptance;
import br.com.projetoApiCWI.tests.base.tests.BaseTest;
import br.com.projetoApiCWI.tests.booking.requests.GetBookingRequest;
import br.com.projetoApiCWI.tests.booking.requests.PutBookingRequest;
import br.com.projetoApiCWI.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando token")
    public void alterarAlterarUmareservaUtilizandoToken() throws Exception {
        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComToken(primeiroID, Utils.validPayloadBooking())
                .then().statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando Basic Authorization")
    public void alterarAlterarUmareservaUtilizandoBasicAuthorization() throws Exception {
        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComAuth(primeiroID, Utils.validPayloadBooking())
                .then().statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Alterar uma reserva sem enviar token")
    public void alterarUmareservaSemEnvioDeToken() throws Exception {
        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaSemToken(primeiroID, Utils.validPayloadBooking())
                .then().statusCode(403);
    }


        @Test
        @Severity(SeverityLevel.NORMAL)
        @Category(E2e.class)
        @DisplayName("Alterar uma reserva com token inválido")
        public void alterarReservaComTokenInvalido() throws Exception {
            int primeiroID = getBookingRequest.allBookings().then()
                    .statusCode(200).extract().path("[0].bookingid");

            putBookingRequest.alterarUmaReservaComTokenInvalido(primeiroID, Utils.validPayloadBooking())
                    .then().statusCode(403);
        }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Alterar uma reserva inexistente")

    public void alterarReservaInexistente() throws Exception {

            putBookingRequest.alterarUmaReservaComToken(999, Utils.validPayloadBooking())
                .then().statusCode(405);
    }
}


