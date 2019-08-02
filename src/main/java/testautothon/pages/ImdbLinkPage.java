package testautothon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ImdbLinkPage {

    protected WebDriver driver;

    @FindBy(xpath = "//div[@class='credit_summary_item']//a")
    public WebElement directorsName;

    /**
     * Constructor to initialize instance variables and verify if current page is home Page.
     *
     * @param serviceUrl
     *            (service url)
     * @param webDriver
     *            (web driver)
     */
    public ImdbLinkPage(final String serviceUrl, final WebDriver webDriver) {
        this.driver = webDriver;
        this.driver.get(serviceUrl);
        Assert.assertTrue(this.driver.getTitle().contains(""),
                "Title does not contain home page text. Title of the page:" + this.driver.getTitle());

        PageFactory.initElements(this.driver, this);
    }

    public String getDiectorName() {
      return  directorsName.getText().trim();

    }
}
