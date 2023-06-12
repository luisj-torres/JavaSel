package secondExercise.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportsPage extends BasePage implements NavigationBar {

    @FindBy(css = "a[href='reports.html']")
    private WebElement reportsTab;

    @FindBy(css = "#fromDate")
    private WebElement fromDateInput;

    @FindBy(css = "#toDate")
    private WebElement toDateInput;

    @FindBy(css = "#selectedReport")
    private WebElement selectedReports;

    @FindBy(css = "input[value='full']")
    private WebElement fullReportsRadio;

    @FindBy(css = ".fa-file-alt")// o .col-12 > button
    private WebElement generateReportCTA;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final JavascriptExecutor jsExecutor;

    public ReportsPage(WebDriver driver) {
        super(driver);
        jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void setInitialDate(int daysToAdd) {
        String date = getFormattedDate(daysToAdd);
        jsExecutor.executeScript("document.getElementById('fromDate').value='" + date + "'");
        //fromDateInput.sendKeys(date); Firefox solo acepta fechas en formato yyyy-MM-dd
    }

    public void generateReport() {
        generateReportCTA.click();
    }

    public void selectPricesReport() {
        Select select = new Select(selectedReports);
        select.selectByValue("prices");
    }

    public void selectFullReport() {
        fullReportsRadio.click();
    }

    public void setFinalDate(int daysToAdd) {
        String date = getFormattedDate(daysToAdd);
        jsExecutor.executeScript("document.getElementById('toDate').value='" + date + "'");
        //toDateInput.sendKeys(date); Firefox solo acepta fechas en formato yyyy-MM-dd
    }

    private String getFormattedDate(int daysToAdd) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, daysToAdd);
        String date = dateFormat.format(cal.getTime());
        return date;
    }

    @Override
    public void navigate() {
        reportsTab.click();
    }
}
