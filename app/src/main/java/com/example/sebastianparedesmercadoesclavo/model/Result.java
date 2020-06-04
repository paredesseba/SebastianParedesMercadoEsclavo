package com.example.sebastianparedesmercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {

    private String id;
    private String title;
    private Seller seller;
    private String price;
    @SerializedName("currency_id")
    private String currencyId;
    private String condition;
    private String thumbnail;
    @SerializedName("address")
    private Address address;
    private List<Attribute> attributes;
    @SerializedName("category_id")
    private String categoryId;
    @SerializedName("catalog_product_id")
    private String catalogProductId;

    public Result() {
    }

    public Result(String id, String title, Seller seller, String price, String currencyId, String condition, String thumbnail, Address address, List<Attribute> attributes, String categoryId, String catalogProductId) {
        this.id = id;
        this.title = title;
        this.seller = seller;
        this.price = price;
        this.currencyId = currencyId;
        this.condition = condition;
        this.thumbnail = thumbnail;
        this.address = address;
        this.attributes = attributes;
        this.categoryId = categoryId;
        this.catalogProductId = catalogProductId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCatalogProductId() {
        return catalogProductId;
    }

    public void setCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
    }
}
