package com.depauw.repairshop.database;

public class Repair {
    private int rid;
    private int vehicle_vid;
    private String date;
    private double cost;
    private String description;

    public Repair( String date, double cost, String description) {
        //this.vehicle = vehicle;
        this.date = date;
        this.cost = cost;
        this.description = description;
    }

    public Repair(int vehicle_vid, String date, double cost, String description) {
        this.vehicle_vid = vehicle_vid;
        this.date = date;
        this.cost = cost;
        this.description = description;
    }

    public Repair(int rid, int vehicle_vid, String date, double cost, String description) {
        this.rid = rid;
        this.vehicle_vid = vehicle_vid;
        this.date = date;
        this.cost = cost;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "rid=" + rid +
                ", vehicle_vid=" + vehicle_vid +
                ", date='" + date + '\'' +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                '}';
    }

    public int getRid() {
        return rid;
    }

    public int getVehicle_vid() {
        return vehicle_vid;
    }

    public String getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
