package org.example.models;

public abstract class Vetement {
    protected String name;
    protected String type;

    public abstract String  getTypeName();

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }


}
