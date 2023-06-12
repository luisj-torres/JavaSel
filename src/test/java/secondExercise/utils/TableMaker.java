package secondExercise.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TableMaker<T> {
    private final WebDriver driver;
    private final Gson gson = new Gson();
    private final Type t;

    public TableMaker(WebDriver driver, Type t) {
        this.driver = driver;
        this.t = t;
    }

    private ArrayList<String> getHeaders(By tableHeaders) {
        ArrayList<String> headers = (ArrayList<String>) driver
                .findElements(tableHeaders).stream().
                map(header -> header.getText().toLowerCase()).
                collect(Collectors.toList());
        return headers;
    }

    public ArrayList<T> buildTable(By tableHeaders, By tableRows) {
        ArrayList<T> list = new ArrayList<>();
        ArrayList<String> headers = getHeaders(tableHeaders);
        ArrayList<WebElement> rows = (ArrayList) driver.findElements(tableRows);

        for (int i = 0; i < rows.size(); i++) {
            ArrayList<WebElement> cells = (ArrayList) rows.get(i).findElements(By.cssSelector("td"));
            JsonObject auxObject = new JsonObject();
            for (int j = 0; j < cells.size(); j++) {
                auxObject.addProperty(headers.get(j), cells.get(j).getText());
            }
            T obj = gson.fromJson(auxObject, t);
            list.add(obj);
        }
        return list;
    }
}
