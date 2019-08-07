package testautothon.PracticeTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import testautothon.pages.ImdbLinkPage;
import testautothon.pages.WikiPage;

public class MovieVerificationTests extends BaseWebdriverTests {

    @Test(dataProvider = "ReadExcelData", timeOut = 50000, description = "Simple test")
    public void verifyMovieDirectorNames(String movieNumber, String movieName) {

        //String movieName="The Shawshank Redemption";
        WikiPage wikiPage = this.openWikiHomePage(movieName);
        String directorNameFromWiki = wikiPage.getDirectorName();

        ImdbLinkPage imdbLinkPage = new ImdbLinkPage(wikiPage.getMovieImdbLink(), this.getWebDriver());

        //ImdbLinkPage imdbLinkPage =
        String directorNameFromImdb = imdbLinkPage.getDiectorName();
        Assert.assertEquals(directorNameFromImdb, directorNameFromWiki, "");

        driver.quit();
    }

}
