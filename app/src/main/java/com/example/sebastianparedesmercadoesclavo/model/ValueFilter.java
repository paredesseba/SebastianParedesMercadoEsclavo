package com.example.sebastianparedesmercadoesclavo.model;

public class ValueFilter {

    private String id;
    private String name;
    private String results;

    public ValueFilter() {
    }

    public ValueFilter(String id, String name, String results) {
        this.id = id;
        this.name = name;
        this.results = results;
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

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
