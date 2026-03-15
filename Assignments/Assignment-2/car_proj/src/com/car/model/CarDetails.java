package com.car.model;

public class CarDetails {
    private int id;
    private int year_of_purchase;
    private int milage;

    public CarDetails(){}

    public CarDetails(int id, int year_of_purchase, int milage){
        this.id=id;
        this.year_of_purchase=year_of_purchase;
        this.milage=milage;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public int getYear(){
        return year_of_purchase;
    }
    public void setYear(int year_of_purchase){
        this.year_of_purchase=year_of_purchase;
    }

    public int getMilage(){
        return milage;
    }
    public void setMilage(int milage){
        this.milage=milage;
    }
}