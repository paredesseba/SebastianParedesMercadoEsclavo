package com.example.sebastianparedesmercadoesclavo.model;

public class Sort {

    private String id;
    private String name;

    public Sort() {
    }

    public Sort(String id, String name) {
        this.id = id;
        this.name = name;
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
}
