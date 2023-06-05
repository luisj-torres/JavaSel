package task5.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GooglePage {

    private String URL = "https://www.google.com";
    private By searchButton = By.cssSelector("div[jsaction*=\"mouseout\"] div > center > input[name=\"btnK\"]");
    private By searchTextArea = By.cssSelector("textarea[type=\"search\"]");
    private String firstResultChild = "#search div #rso > div:first-child ";
    private String firstResultAnchor = firstResultChild+ "a[href=\"https://www.selenium.dev/\"]";
    private By firstResultTitle = By.cssSelector(firstResultAnchor+ " h3");

    private WebDriver d;

    public GooglePage(WebDriver d){
        this.d = d;
    }

    public String getURL(){
        return this.URL;
    }

    public void typeAndSearch(String searchText){
        d.findElement(searchTextArea).sendKeys(searchText);
        WebElement button = new WebDriverWait(this.d, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
    }
    public void assertFirstResult(){

        Wait<WebDriver> wait = createFluentWait();
        WebElement waitedAnchor = wait.until(driver -> driver.findElement(By.cssSelector(firstResultAnchor)));
        assertThat(d.findElement(firstResultTitle).getText(),equalTo("Selenium"));

    }
    private Wait<WebDriver> createFluentWait(){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(this.d)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        return wait;
    }


}
