package secondExercise.pom;


import com.google.common.reflect.TypeToken;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import secondExercise.models.Product;
import secondExercise.utils.TableMaker;

import java.util.ArrayList;

public class ResultsPage extends BasePage {

    @FindBy(css = "#price-sum")
    private WebElement sumCell;

    @FindBy(css = "#sort")
    private WebElement sortSelect;

    private final By tableHeaders = By.cssSelector(".table-header th");
    private final By tableRows = By.cssSelector("tbody tr");
    private final TableMaker<Product> table;

    public ResultsPage(WebDriver driver) {
        super(driver);
        this.table = new TableMaker(driver, new TypeToken<Product>() {
        }.getType());
        PageFactory.initElements(driver, this);
    }

    public void selectPriceDescending() {
        Select select = new Select(sortSelect);
        select.selectByValue("desc");
    }

    public void waitForSumCell() {
        this.waitUntilElementIsVisible(sumCell);
    }

    public Integer getSumCell() {
        String a = sumCell.getText().replace("$", "");
        return Integer.valueOf(a);
    }

    public ArrayList<Product> getDataFromTable() {
        return table.buildTable(tableHeaders, tableRows);
    }
}
