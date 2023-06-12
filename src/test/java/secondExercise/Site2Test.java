package secondExercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import secondExercise.models.Product;
import secondExercise.pom.ReportsPage;
import secondExercise.pom.ResultsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Site2Test {
    final String URL = "file:///Users/luisj/Desktop/site2/index.html";
    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) {
        switch (browser) {
            case "Firefox": {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            case "Chrome": {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            }
        }
        driver.get(URL);
    }

    @Test
    public void navigateToReports() {
        ResultsPage resultsPage = new ResultsPage(this.driver);
        ReportsPage reportsPage = new ReportsPage(this.driver);
        reportsPage.navigate();

        reportsPage.setInitialDate(2);
        reportsPage.setFinalDate(5);
        reportsPage.selectPricesReport();
        reportsPage.selectFullReport();
        reportsPage.generateReport();

        resultsPage.waitForSumCell();
        resultsPage.selectPriceDescending();
        resultsPage.waitForSumCell();
        ArrayList<Product> products = resultsPage.getDataFromTable();
        int totalFromTable = products.stream()
                .mapToInt(product -> product.getPrice())
                .sum();
        assertThat(totalFromTable, comparesEqualTo(resultsPage.getSumCell()));


        //Que es mejor?
        // Este approach
        ArrayList<Product> clonedList = sortProductInDescOrder(products);
        assertEquals(clonedList, products);

        //o este?
        ArrayList<Integer> pricesList = (ArrayList<Integer>) products
                .stream()
                .map(product -> product.getPrice())
                .collect(Collectors.toList());
        ArrayList<Integer> clonedIntegerList = sortIntegerInDescOrder(pricesList);
        assertEquals(clonedIntegerList, pricesList);

        //Nota:
        // - El nombre de los metodos de sort nomas los puse para indicar que hacen
        // - Originalmente mi approach era la implementacion
        // del metodo 'sortProductInDescOrder' dentro del test pero no es muy reutilizable.
        // Es que me quede pensando en esto:The framework should be designed with reusability and maintainability in mind.


    }

    private ArrayList<Integer> sortIntegerInDescOrder(ArrayList<Integer> originalEnInt) {
        ArrayList<Integer> clonedList = new ArrayList(originalEnInt);
        Collections.sort(clonedList);
        Collections.reverse(clonedList);
        return clonedList;
    }

    private ArrayList<Product> sortProductInDescOrder(ArrayList<Product> original) {
        ArrayList<Product> clonedList = new ArrayList(original);
        clonedList.sort((o1, o2) -> {
            if (o1.getPrice() == o2.getPrice()) {
                return 0;
            }
            return (o1.getPrice() - o2.getPrice())
                    < 0 ? 1 : -1;
        });
        return clonedList;
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
