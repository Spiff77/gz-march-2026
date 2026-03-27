package com.example.demo.model;

public class Cat implements Pet {

    private String name;
    private String color;
    private boolean haveFur;

    public Cat(String name, String color, boolean haveFur) {
        this.name = name;
        this.color = color;
        this.haveFur = haveFur;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHaveFur() {
        return haveFur;
    }

    public void setHaveFur(boolean haveFur) {
        this.haveFur = haveFur;
    }

    @Override
    public void feed() {
        System.out.println("Cat is enjoying is tiny meal");
    }
}
