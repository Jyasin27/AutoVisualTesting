import base.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import pages.SearchPage;

import javax.lang.model.element.Element;

import static com.google.auto.common.SuperficialValidation.validateElement;

public class SearchTests extends BaseTest {

    private SearchPage page;

    @Test
    public void testSearchByFullTitle(){
        String title = "Agile Testing";
        page.search(title);
        validateWindow();
    }

    @Test
    public void testSearchByFullTitle_Element(){
        page.search("Agile Testing");
        validateElement( By.id("pid3"));
        Assert.assertEquals("Number of books returned",
                1, page.getNumberOfVisibleBooks());
    }


}