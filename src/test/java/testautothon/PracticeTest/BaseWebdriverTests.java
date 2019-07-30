package testautothon.PracticeTest;


import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import testautothon.pages.HomePage;
import testautothon.utils.ExcelDataReader;
import testautothon.pages.WikiPage;
import testautothon.utils.PropertyUtil;
import testautothon.utils.reporting.TestLogger;

import java.util.concurrent.TimeUnit;

public class BaseWebdriverTests {

    protected PropertyUtil propertyUtil= new PropertyUtil();
    WebDriver driver;
    TestLogger logger = new TestLogger();
    protected String serviceUrl;

    protected WebDriver getWebDriver() {

        String requiredDriver = this.propertyUtil.getProperty("driver");
        switch (requiredDriver) {
            case "htmlunitDriver":
                driver = new HtmlUnitDriver(true);
                break;
            case "chrome":
                String resolution = this.propertyUtil.getProperty("resolution");
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                if (resolution.equals("") || resolution.matches("[x*.]"))
                {
                    options.addArguments("start-maximized");
                }
                else
                {
                    options.addArguments("--window-size=" + resolution);
                }
                options.addArguments("--dom-automation");
//                options.addArguments("--disable-web-security");
                options.addArguments("--safebrowsing-disable-download-protection");
                options.setExperimentalOption("useAutomationExtension", false);
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);


                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);

                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", "D:\\PracticeTestProject\\IEDriverServer.exe");
                DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
                desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                driver = new InternetExplorerDriver();
                break;
            default:
                driver = new FirefoxDriver();

        }
        return driver;
    }

    public WikiPage openWikiHomePage(String movieName) {
        serviceUrl = this.propertyUtil.getProperty("test.loginUrl");
        HomePage homePage = new HomePage(serviceUrl, this.getWebDriver());
        WikiPage wikiPage = homePage.openMovieWikiPage(movieName);
        return new WikiPage(this.driver);
    }

    @DataProvider
    public static Object[][] ReadExcelData() {
        Object[][] obj = null;
        try {
            obj = ExcelDataReader.ReadExcelData();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }
}
