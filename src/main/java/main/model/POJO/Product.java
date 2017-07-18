package main.model.POJO;

/**
 * Created by video on 14.07.2017.
 */
public class Product {
    private long id;
    private String name;
    private String brand;
    private int categoryId;
    private float cost;
    private int quantity;

    public Product(long id, String name, String brand, int categoryId, float cost, int quantity) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.categoryId = categoryId;
        this.cost = cost;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
