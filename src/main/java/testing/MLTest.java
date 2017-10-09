package testing;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pageobjects.ListSearchPage;
import pageobjects.MainPage;
import pageobjects.ProductPage;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MLTest {
    private WebDriver driver;
    private ExtentReports report;
    private static ExtentTest logger;
    private static Calendar DATE = Calendar.getInstance();
    private static int YEAR = DATE.get(Calendar.YEAR);
    private static int MONTH = DATE.get(Calendar.MONTH) + 1;
    private static int DAY = DATE.get(Calendar.DAY_OF_MONTH);
    private static int HOUR = DATE.get(Calendar.HOUR_OF_DAY);
    private static int MIN = DATE.get(Calendar.MINUTE);
    private static String SCREENSHOT_PATH = System.getProperty("user.dir")+"\\Screenshots\\screenshot.png";

    @BeforeSuite
    public void beforeSuite() throws InterruptedException {
        report = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\MLReport["+ MONTH +"-"+ DAY +"-"+ YEAR +"]["+ HOUR +""+ MIN +"].html", false);
        Thread.sleep(10000);
        String browser = "CHROME";
        switch(browser){
            case "FIREFOX":
                DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setJavascriptEnabled(true);
                desiredCapabilities.setCapability("marionette", false);
                desiredCapabilities.setCapability("applicationCacheEnabled", false);
                desiredCapabilities.setCapability("rotatable", true);
                driver = new FirefoxDriver(desiredCapabilities);
                break;
            case "CHROME":
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability("chrome.switches", Collections.singletonList("--no-default-browser-check"));
                HashMap<String, String> chromePreferences = new HashMap<>();
                chromePreferences.put("profile.password_manager_enabled", "false");
                capabilities.setCapability("chrome.prefs", chromePreferences);
                driver = new ChromeDriver(capabilities);
                break;
            default:
                desiredCapabilities = new DesiredCapabilities();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void search(){
        driver.get("https://www.mercadolibre.com.ar/");
        logger = report.startTest("searchIphone");
        logger.log(LogStatus.INFO, "Browser started ");
        driver.manage().window().maximize();
        MainPage loginPage = PageFactory.initElements(this.driver,MainPage.class);
        loginPage.isMainPageDisplayed();
        logger.log(LogStatus.INFO, "Verify if the correct page is displayed");
        loginPage.enterSearch("iphone");
        logger.log(LogStatus.INFO, "Enter search 'iphone' ");
        loginPage.clickSearchButton();
        logger.log(LogStatus.INFO, "Click search button ");
        ListSearchPage mainPage = PageFactory.initElements(this.driver, ListSearchPage.class);
        mainPage.clickFirstProduct();
        ProductPage productPage = PageFactory.initElements(this.driver, ProductPage.class);
        Assert.assertTrue(productPage.isProductPageDisplayed(), "The product page is not displayed.");
    }

    @AfterSuite
    public void afterSuite(){
        Utilities.takeScreenshot(driver, SCREENSHOT_PATH);
        logger.log(LogStatus.INFO, "Product page screenshot"+logger.addScreenCapture(SCREENSHOT_PATH));
        report.endTest(logger);
        report.flush();
        driver.quit();
    }

}
