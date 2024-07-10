package br.com.projetoApiCWI.tests.booking.tests;

import br.com.projetoApiCWI.runners.E2e;
import br.com.projetoApiCWI.suites.Acceptance;
import br.com.projetoApiCWI.tests.base.tests.BaseTest;
import br.com.projetoApiCWI.tests.booking.requests.DeleteBookingRequest;
import br.com.projetoApiCWI.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Feature("Deletar reservas:")


public class DeleteBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Deletar uma reserva específica")
    public void  garantirCancelamentoDeReservaEspecifica() throws  Exception {

        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");

        deleteBookingRequest.deletarReservaEspecifica(primeiroID).then()
                .statusCode(201);//Retorna "Created" porém o mais correto neste caso seria um 200 (oK)

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Deletar uma reserva inexistente ")
    public void  deletarUmaReservaInexistente() throws  Exception {

        int id = 599;

        deleteBookingRequest.deletarReservaEspecifica(id).then()
                .statusCode(405);// status code deveria ser 404 not found

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Deletar uma reserva sem Autorização")
    public void  deletarUmaReservaComAutorizacaoInvalida() throws Exception {

        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");

        deleteBookingRequest.deletarReservaSemAutorizacao(primeiroID).then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Deletar uma reserva sem o campo authorization")
    public void  deletarUmaReservaSemAutorizacao() throws Exception {

        int primeiroID = getBookingRequest.allBookings().then()
                .statusCode(200).extract().path("[0].bookingid");

        deleteBookingRequest.deletarReservaSemoCampoAutorizacao(primeiroID).then()
                .statusCode(403);
    }
}
