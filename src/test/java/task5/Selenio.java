package task5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import task5.pom.GooglePage;

public class Selenio {
    WebDriver driver;
    @BeforeClass
    void setup(){
        WebDriverManager.firefoxdriver().setup();
    }
    @BeforeTest
    void navigate(){
        driver = new FirefoxDriver();
    }
    @Test
    void assertFirstResultisSelenium(){
        GooglePage g = new GooglePage(driver);
        driver.get(g.getURL());
        g.typeAndSearch("Selenium");
        g.assertFirstResult();
    }

    @AfterTest
    void teardown(){
        driver.quit();
    }
}
