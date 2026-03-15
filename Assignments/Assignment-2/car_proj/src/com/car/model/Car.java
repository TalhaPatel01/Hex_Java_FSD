package com.car.model;

public class Car {
    private int id;
    private String registration_number;
    private String chasis_number;
    private String registration_state;
    private String brand;
    private String model;
    private String variant;
    private Owner owner;
    private CarDetails carDetails;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getRegistrationNumber(){
        return registration_number;
    }
    public void setRegistrationNumber(String registration_number){
        this.registration_number=registration_number;
    }

    public String getChasisNumber(){
        return chasis_number;
    }
    public void setChasisNumber(String chasis_number){
        this.chasis_number=chasis_number;
    }

    public String getRegistrationState(){
        return registration_state;
    }
    public void setRegistrationState(String registration_state){
        this.registration_state=registration_state;
    }

    public String getBrand(){
        return brand;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }

    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model=model;
    }

    public String getVariant(){
        return variant;
    }
    public void setVariant(String variant){
        this.variant=variant;
    }

    public Owner getOwner(){
        return owner;
    }
    public void setOwner(Owner owner){
        this.owner=owner;
    }

    public CarDetails getCarDetails(){
        return carDetails;
    }
    public void setCarDetails(CarDetails carDetails){
        this.carDetails=carDetails;
    }
}