package com.depauw.repairshop.database;

public class Vehicle {
    private int id;
    private int client_id;
    private int year;
    private String make_Model;
    private String color;

    public Vehicle(int client_id, int year, String make_Model, String color) {
        this.client_id = client_id;
        this.year = year;
        this.make_Model = make_Model;
        this.color = color;
    }

    public Vehicle(int id, int client_id, int year, String make_Model, String color) {
        this.id = id;
        this.client_id = client_id;
        this.year = year;
        this.make_Model = make_Model;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getClient_id() {
        return client_id;
    }

    public String getMake_Model() {
        return make_Model;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return year + " " + make_Model ;
    }
}
