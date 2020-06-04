package com.example.sebastianparedesmercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

public class Paging {

    private String total;
    private String offset;
    private String limit;
    @SerializedName("primary_results")
    private String primaryResults;

    public Paging() {
    }

    public Paging(String total, String offset, String limit, String primaryResults) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.primaryResults = primaryResults;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getPrimaryResults() {
        return primaryResults;
    }

    public void setPrimaryResults(String primaryResults) {
        this.primaryResults = primaryResults;
    }
}
