package testautothon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikiPage {
        private WebDriver driver;

        public WikiPage(final WebDriver webDriver) {
            this.driver = webDriver;
            PageFactory.initElements(webDriver, this);
        }

        @FindBy(xpath = "//table//tr/th[text()='Directed by']/../td/a")
        public WebElement lblDirectedBy;

        @FindBy(xpath = "//span[@id='External_links']")
        public WebElement externalLinksHeader;

        public String getDirectorName() {
            String directorName = "";
            try {

                    directorName = lblDirectedBy.getText();

            }catch(Exception e) {

            }
            return directorName;
        }

        public String getMovieImdbLink() {
            String movieImdbLink = null;
            try{
               movieImdbLink =  driver.findElement(By.xpath("//a[contains(@href,'https://www.imdb.com/title/')]")).getAttribute("href");
                }

            catch(Exception e) {

            }
            return movieImdbLink;
        }
}
