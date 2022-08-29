package base;

import com.applitools.eyes.selenium.Eyes;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class BaseTest {

    protected static WebDriver driver;
    protected static Eyes eyes;
    private By locator;

    @BeforeClass
    public static void setUp() {
        Properties props = System.getProperties();
        try {
            props.load(new FileInputStream(new File("resources/test.properties")));
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        driver = new ChromeDriver();
        initiateEyes();

        driver.get(System.getProperty("site.url"));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        eyes.abortIfNotClosed();
    }

    private static void initiateEyes(){
        eyes = new Eyes();
        eyes.setApiKey(System.getProperty("applitools.api.key"));
    }

    public void validateWindow(){
        eyes.open(driver, "The Internet", Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.setForceFullPageScreenshot(true);
        eyes.checkWindow();
        eyes.close();
    }

    public void validateElement(By locator){
        eyes.open(driver, "Automation Bookstore",
                Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkFrame((WebElement) locator);
        eyes.close();
    }
    public void validateFrame(String locator){
        eyes.open(driver, "The Internet", Thread.currentThread().getStackTrace()[2].getMethodName());
        eyes.checkFrame(locator);
        eyes.close();
    }
}