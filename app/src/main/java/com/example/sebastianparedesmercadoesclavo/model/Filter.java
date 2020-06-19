package com.example.sebastianparedesmercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Filter {

    private String id;
    private String name;
    private List<ValueFilter> values;

    public Filter() {
    }

    public Filter(String id, String name, List<ValueFilter> valueFilters) {
        this.id = id;
        this.name = name;
        this.values = valueFilters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ValueFilter> getValueFilters() {
        return values;
    }

    public void setValues(List<ValueFilter> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return name;
    }
}
