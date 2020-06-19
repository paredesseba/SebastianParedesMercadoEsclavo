package com.example.sebastianparedesmercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QueryResponse {

    private String query;
    private Paging paging;
    private List<Result> results;
    private Sort sort;
    @SerializedName("available_sorts")
    private List<Sort> availableSorts;
    private List<Filter> filters;
    @SerializedName("available_filters")
    private List<Filter> availableFilters;

    public QueryResponse() {
    }

    public QueryResponse(String query, Paging paging, List<Result> results, Sort sort, List<Sort> availableSorts, List<Filter> filters, List<Filter> availableFilters) {
        this.query = query;
        this.paging = paging;
        this.results = results;
        this.sort = sort;
        this.availableSorts = availableSorts;
        this.filters = filters;
        this.availableFilters = availableFilters;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public List<Sort> getAvailableSorts() {
        return availableSorts;
    }

    public void setAvailableSorts(List<Sort> availableSorts) {
        this.availableSorts = availableSorts;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Filter> getAvailableFilters() {
        return availableFilters;
    }

    public void setAvailableFilters(List<Filter> availableFilters) {
        this.availableFilters = availableFilters;
    }
}
