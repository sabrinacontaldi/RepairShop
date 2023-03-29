package com.depauw.repairshop.database;

public class Client {

    private int id;
    private String dln;
    private String full_name;
    private int contact_number;
    //private String email;
    private String street_address;
    private String city;
    private String state;
    private int zip_code;

    public Client(String dln, String full_name, int contact_number, String street_address, String city, String state, int zip_code) {
        this.dln = dln;
        this.full_name = full_name;
        this.contact_number = contact_number;
        this.street_address = street_address;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
    }

    public Client(int id, String dln, String full_name, int contact_number, String street_address, String city, String state, int zip_code) {
        this.id = id;
        this.dln = dln;
        this.full_name = full_name;
        this.contact_number = contact_number;
        this.street_address = street_address;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
    }

    public int getId() { return id; }

    public String getDln() {
        return dln;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getStreet_address() {
        return street_address;
    }

    public int getContact_number() {
        return contact_number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZip_code() {
        return zip_code;
    }

    @Override
    public String toString() {
        return full_name;
    }
}
