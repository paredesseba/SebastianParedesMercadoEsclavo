package com.example.sebastianparedesmercadoesclavo.model;

public class ItemLocation {

    private Neighborhood neighborhood;
    private City city;
    private State state;
    private String latitude;
    private String longitude;

    public ItemLocation() {
    }

    public ItemLocation(Neighborhood neighborhood, City city, State state, String latitude, String longitude) {
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
