package testautothon.pages;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage{

    protected WebDriver driver;
    /**
     * Constructor to initialize instance variables and verify if current page is home Page.
     *
     * @param serviceUrl
     *            (service url)
     * @param webDriver
     *            (web driver)
     */
    public HomePage(final String serviceUrl, final WebDriver webDriver) {
        this.driver = webDriver;
        this.driver.get(serviceUrl);
        Assert.assertTrue(this.driver.getTitle().contains(""),
                "Title does not contain home page text. Title of the page:" + this.driver.getTitle());

        PageFactory.initElements(this.driver, this);
    }

    private String getMovieWikiUrl(String movieName) {
        String movieWikiUrl = "";
        try {

            WebElement element = driver.findElement(By.name("q"));
            element.sendKeys(movieName); // send also a "\n"
            element.submit();

            // wait until the google page shows the result
            WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

            movieName= movieName.replace(" ","_");

            List<WebElement> movieWikiHeader = this.driver.findElements(By.xpath("//a[contains(@href,'https://en.wikipedia.org/wiki/"+movieName+"')]"));

            for (WebElement itm : movieWikiHeader) {
                if ((this.getParentElement(itm, 1).getAttribute("href").startsWith("https://en.wikipedia.org"))) {
                    movieWikiUrl = this.getParentElement(itm, 1).getAttribute("href");
                    break;
                }
            }

        } catch (Exception e) {

        }
        return movieWikiUrl;
    }


    public WikiPage openMovieWikiPage(String movieName)
    {
        try {
            this.driver.get(getMovieWikiUrl(movieName));

            Assert.assertTrue(this.driver.getTitle().contains(movieName + " - Wikipedia"),
                    "Title of wiki page should start with movie name");
        } catch (Exception e) {

        }
        return new WikiPage(this.driver);
    }

}

