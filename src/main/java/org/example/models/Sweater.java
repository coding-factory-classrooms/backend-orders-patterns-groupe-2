package org.example.models;

public class Sweater extends Clothes {
    public enum Size {
        L,
        S,
        M,
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    private Size size;

    @Override
    public String getTypeName() {
        return "Sweater";
    }
}
