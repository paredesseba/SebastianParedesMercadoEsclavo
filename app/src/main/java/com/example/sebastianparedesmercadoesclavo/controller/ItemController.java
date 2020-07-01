package com.example.sebastianparedesmercadoesclavo.controller;

import com.example.sebastianparedesmercadoesclavo.dao.ItemDao;
import com.example.sebastianparedesmercadoesclavo.dao.ItemFirestoreDao;
import com.example.sebastianparedesmercadoesclavo.model.Item;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ItemController {

    private ItemDao itemDao;
    private ItemFirestoreDao itemFirestoreDao;

    public ItemController(){
        this.itemDao = new ItemDao();
        this.itemFirestoreDao = new ItemFirestoreDao();
    }

    public void getItem (final ResultListener<Item> itemResultListener, String id){
        this.itemDao.getItem(new ResultListener<Item>() {
            @Override
            public void onFinish(Item result) {
                itemResultListener.onFinish(result);
            }
        },id);
    }

    public void agregarItemAFavoritosFirestore(final Item item, FirebaseUser firebaseUser, final ResultListener<Item> resultListener){
        itemFirestoreDao.agregarItemAFavoritosFirestore(item, firebaseUser, new ResultListener<Item>() {
            @Override
            public void onFinish(Item result) {
                resultListener.onFinish(result);
            }
        });
    }

    public void getItemListFirestore(FirebaseUser firebaseUser, final ResultListener<List<Item>> resultListener){
        itemFirestoreDao.getItemListFirestore(firebaseUser, new ResultListener<List<Item>>() {
            @Override
            public void onFinish(List<Item> result) {
                resultListener.onFinish(result);
            }
        });
    }
}
