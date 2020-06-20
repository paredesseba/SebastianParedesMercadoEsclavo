package com.example.sebastianparedesmercadoesclavo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    private String id;
    private String title;
    @SerializedName("seller_id")
    private String sellerId;
    @SerializedName("category_id")
    private String categoryId;
    private String price;
    private String condition;
    private String thumbnail;
    private List<ItemPicture> pictures;
    @SerializedName("seller_address")
    private ItemSellerAddress sellerAddress;
    private ItemLocation location;
    private List<Attribute> attributes;

    public Item() {
    }

    public Item(String id, String title, String sellerId, String categoryId, String price, String condition, String thumbnail, List<ItemPicture> pictures, ItemSellerAddress sellerAddress, ItemLocation location, List<Attribute> attributes) {
        this.id = id;
        this.title = title;
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.price = price;
        this.condition = condition;
        this.thumbnail = thumbnail;
        this.pictures = pictures;
        this.sellerAddress = sellerAddress;
        this.location = location;
        this.attributes = attributes;
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

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
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

    public List<ItemPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ItemPicture> pictures) {
        this.pictures = pictures;
    }

    public ItemSellerAddress getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(ItemSellerAddress sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public ItemLocation getLocation() {
        return location;
    }

    public void setLocation(ItemLocation location) {
        this.location = location;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
