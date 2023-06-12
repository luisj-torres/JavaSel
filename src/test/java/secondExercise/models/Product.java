package secondExercise.models;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Product{

    @SerializedName("#")
    private int number;
    @SerializedName("product")
    private String product;
    @SerializedName("price")
    private String price;
    @SerializedName("status")
    private String status;

    public Product(int number, String product, String price, String status){
        this.number = number;
        this.product = product;
        this.price = price;
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getProduct() {
        return product;
    }

    public Integer getProductNumber(){
        return Integer.valueOf(this.product.split(" ")[1]);
    }

    public void setProduct(String product) {
        this.product = product;
    }

    //Asumiendo por el ejercicio que los precios no tienen decimal
    public Integer getPrice() {
        return Integer.valueOf(price.replace("$",""));
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "number=" + number +
                ", product='" + product + '\'' +
                ", price='" + price + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
