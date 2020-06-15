package com.example.sebastianparedesmercadoesclavo.dao;

import com.example.sebastianparedesmercadoesclavo.model.Item;
import com.example.sebastianparedesmercadoesclavo.service.ItemService;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemDao {

    protected static final String BASE_URL = "https://api.mercadolibre.com/";
    private Retrofit retrofit;
    private ItemService itemService;

    public ItemDao() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.itemService = retrofit.create(ItemService.class);
    }

    public void getItem(final ResultListener<Item> resultListener, String id){
        Call<Item> itemCall = itemService.getItem(id);
        itemCall.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                if (response.isSuccessful()){
                    Item item = response.body();
                    resultListener.onFinish(item);
                }
                else
                    response.errorBody();
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
