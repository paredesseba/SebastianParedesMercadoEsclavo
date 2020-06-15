package com.example.sebastianparedesmercadoesclavo.controller;

import com.example.sebastianparedesmercadoesclavo.dao.ItemDao;
import com.example.sebastianparedesmercadoesclavo.model.Item;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

public class ItemController {

    private ItemDao itemDao;

    public ItemController(){
        this.itemDao = new ItemDao();
    }

    public void getItem (final ResultListener<Item> itemResultListener, String id){
        this.itemDao.getItem(new ResultListener<Item>() {
            @Override
            public void onFinish(Item result) {
                itemResultListener.onFinish(result);
            }
        },id);
    }
}
