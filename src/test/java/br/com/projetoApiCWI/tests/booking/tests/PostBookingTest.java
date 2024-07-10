package br.com.projetoApiCWI.tests.booking.tests;

import br.com.projetoApiCWI.runners.E2e;
import br.com.projetoApiCWI.suites.Acceptance;
import br.com.projetoApiCWI.tests.base.tests.BaseTest;
import br.com.projetoApiCWI.tests.booking.requests.PostBookingRequest;
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
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void criarUmaNovaReserva() throws Exception {
        postBookingRequest.novaReserva().then()
                .statusCode(200)//o status code correto deveria ser 201 "created"
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva com payload inválido")
    public void criarUmaReservaComPayloadInvalido() throws Exception {
        postBookingRequest.novaReservaInvalida().then()
                .statusCode(500);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar criação de mais de uma reserva em sequêmcia")
    public void criarMaisDeUmaNovaReserva() throws Exception {
        postBookingRequest.novaReserva().then()
                .statusCode(200)//o status code correto deveria ser 201 "created"
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
        postBookingRequest.novaReserva().then()
                .statusCode(200)//o status code correto deveria ser 201 "created"
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva com parâmtros no payload ")
    public void criarUmaNovaReservaComParametroAdicional() throws Exception {
        postBookingRequest.novoPayloadComParametroAdicional().then()
                .statusCode(200)//o status code corretob2 /; deveria ser 201 "created"
                .time(lessThan(4L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Retornar status 418 ")
    public void retornarStatusCodeComHeaderAcceptInvalido() throws Exception {
        postBookingRequest.retornar418().then()
                .statusCode(418);
    }


}