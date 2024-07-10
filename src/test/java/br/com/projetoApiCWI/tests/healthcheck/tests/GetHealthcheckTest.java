package br.com.projetoApiCWI.tests.healthcheck.tests;

import br.com.projetoApiCWI.runners.Healthcheck;
import br.com.projetoApiCWI.tests.base.tests.BaseTest;
import br.com.projetoApiCWI.tests.healthcheck.requests.GetHealthcheckRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Feature("Healthcheck")

public class GetHealthcheckTest extends BaseTest {

    GetHealthcheckRequest getHealthcheckRequest = new GetHealthcheckRequest();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Healthcheck.class)
    @DisplayName("Verificar Status da Api")
    public void verificarStatusDaAPI() throws Exception{
        getHealthcheckRequest.statusAPI().then()
                .statusCode(201);// Este retorno deveria ser 200, uma vez que nada foi criado.
    }
}


