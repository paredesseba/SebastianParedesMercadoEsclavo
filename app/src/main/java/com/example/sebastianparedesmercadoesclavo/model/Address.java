package com.example.sebastianparedesmercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("state_id")
    private String stateId;
    @SerializedName("state_name")
    private String stateName;
    @SerializedName("city_id")
    private String cityId;
    @SerializedName("city_name")
    private String cityName;

    public Address() {
    }

    public Address(String stateId, String stateName, String cityId, String cityName) {
        this.stateId = stateId;
        this.stateName = stateName;
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
