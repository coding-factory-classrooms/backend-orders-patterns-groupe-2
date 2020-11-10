package org.example.models;

public abstract class Clothes {
    protected String name;
    protected int price;

    public abstract String  getTypeName();

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
