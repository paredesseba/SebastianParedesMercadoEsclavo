package com.example.sebastianparedesmercadoesclavo.controller;

import com.example.sebastianparedesmercadoesclavo.dao.HistorialFirestoreDao;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class HistorialController {

    private HistorialFirestoreDao historialFirestoreDao;

    public HistorialController() {
        this.historialFirestoreDao = new HistorialFirestoreDao();
    }

    public void agregarHistorial(final String query, FirebaseUser firebaseUser, final ResultListener<String> resultListener){
        historialFirestoreDao.agregarHistorial(query, firebaseUser, new ResultListener<String>() {
            @Override
            public void onFinish(String result) {
                resultListener.onFinish(result);
            }
        });
    }

    public void getHistorialFirestore(FirebaseUser firebaseUser, final ResultListener<List<String>> resultListener){
        historialFirestoreDao.getHistorialFirestore(firebaseUser, new ResultListener<List<String>>() {
            @Override
            public void onFinish(List<String> result) {
                resultListener.onFinish(result);
            }
        });
    }
}
