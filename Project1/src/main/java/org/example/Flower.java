package org.example;

public class Flower {
    private int id;
    private String name;
    private String picture;
    private String price;

    private String description;
    private String type;

    public Flower(int id, String name, String picture, String price, String type, String description) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.price = price;
        this.type = type;
        this.description = description;
    }

    public Flower() {
    }

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
