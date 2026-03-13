package com.ecom.model;

public class Category {
    private int id;
    private String name;
    private int seq;

    public Category() {
    }

    public Category(int id, String name, int seq) {
        this.id = id;
        this.name = name;
        this.seq = seq;
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

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seq=" + seq +
                '}';
    }
}