import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by vyomkeshjha on 03/04/16.
 */
public class SeleniumLoginWinch {

    private WebDriver driver;
    private String baseUrl;
    private String Username;
    private String Password;
    private String[] Tokens;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        FileRead reader = new FileRead();
        Tokens = reader.getTokens();
        Username=Tokens[0];
        Password=Tokens[1];

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://172.16.16.16/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testIOnLogin() throws Exception {

        driver.get(baseUrl + "/24online/servlet/E24onlineHTTPClient");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(Username);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void testIonLogoutCode() throws Exception {
        driver.get(baseUrl + "/24online/servlet/E24onlineHTTPClient");
        driver.findElement(By.name("logout")).click();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
