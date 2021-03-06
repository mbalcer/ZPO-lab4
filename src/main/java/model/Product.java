package model;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int qty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void showDescription() {
        System.out.println(this.description);
    }

    public void increaseQtyBy10() {
        this.qty+=10;
    }

    public void increasePriceBy10Percent() {
        this.price*=1.1;
    }

    private void reducePriceBy10Percent() {
        this.price*=0.9;
    }
}
