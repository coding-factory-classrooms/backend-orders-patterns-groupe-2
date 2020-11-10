package org.example.models;

public class Shoe extends Clothes {
    private int shoeSize;

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
