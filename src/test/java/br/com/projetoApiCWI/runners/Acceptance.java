package br.com.projetoApiCWI.runners;

import br.com.projetoApiCWI.tests.auth.tests.PostAuthTest;
import br.com.projetoApiCWI.tests.booking.tests.DeleteBookingTest;
import br.com.projetoApiCWI.tests.booking.tests.GetBookingTest;
import br.com.projetoApiCWI.tests.booking.tests.PutBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.projetoApiCWI.suites.Acceptance.class)
@Suite.SuiteClasses({
        GetBookingTest.class,
        PutBookingTest.class,
        DeleteBookingTest.class,
        PostAuthTest.class
})



public class Acceptance {
}
