package org.example.models;

public class Shoe extends Clothes {
    private int shoeSize;

    public Shoe(String name, int price) {
        super(name, price);
    }

    public Shoe(String name, int price, int shoeSize) {
        super(name, price);
        this.shoeSize = shoeSize;
    }

    public int getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    @Override
    public String getTypeName() {
        return "Shoe";
    }
}
