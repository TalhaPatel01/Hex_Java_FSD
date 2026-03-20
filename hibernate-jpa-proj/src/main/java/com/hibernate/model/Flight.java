package com.hibernate.model;

import jakarta.persistence.*;

@Entity //this makes model class an Entity
public class Flight { //table name will be flight
    @Id //makes id primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "flight_number", nullable = false) //will add column
    private String flightNumber;
}