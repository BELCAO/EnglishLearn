package com.example.myapplication.domain.model;

public class Course {

    private int id;
    private String name;
    private int price;

    public Course() {
        
    }

    public Course(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "ID: " + id +
                ", name: " + name + '\'' +
                ", price: " + price;

    }
}
