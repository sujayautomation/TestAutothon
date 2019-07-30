package testautothon.PracticeTest;

import testautothon.pages.ImdbLinkPage;
import testautothon.pages.WikiPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MovieVerificationTests extends BaseWebdriverTests {

    @Test(dataProvider = "ReadExcelData")
    public void verifyMovieDirectorNames(String movieNumber, String movieName) {
       logger.log("TestStarted");
       //String movieName="The Shawshank Redemption";
        WikiPage wikiPage = this.openWikiHomePage(movieName);
        String directorNameFromWiki = wikiPage.getDirectorName();

        logger.log("directorNameFromWiki "+directorNameFromWiki);

      ImdbLinkPage imdbLinkPage =  new ImdbLinkPage(wikiPage.getMovieImdbLink(),this.getWebDriver());

        //ImdbLinkPage imdbLinkPage =
        String directorNameFromImdb = imdbLinkPage.getDiectorName();
     logger.log("directorNameFromImdb "+directorNameFromImdb);
        Assert.assertEquals(directorNameFromImdb,directorNameFromWiki,"");
    }

}
