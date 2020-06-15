package com.example.sebastianparedesmercadoesclavo.service;

import com.example.sebastianparedesmercadoesclavo.model.Item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemService {

    @GET("items/{id}")
    Call<Item> getItem(@Path("id") String id);

}