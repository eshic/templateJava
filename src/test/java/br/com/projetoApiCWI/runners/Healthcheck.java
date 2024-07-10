package br.com.projetoApiCWI.runners;

import br.com.projetoApiCWI.tests.healthcheck.tests.GetHealthcheckTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.projetoApiCWI.suites.Healthcheck.class)
@Suite.SuiteClasses({
        GetHealthcheckTest.class
})

public class Healthcheck {
}
