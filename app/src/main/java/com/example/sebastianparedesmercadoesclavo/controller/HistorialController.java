package com.example.sebastianparedesmercadoesclavo.controller;

import com.example.sebastianparedesmercadoesclavo.dao.HistorialFirestoreDao;
import com.example.sebastianparedesmercadoesclavo.model.Query;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class HistorialController {

    private HistorialFirestoreDao historialFirestoreDao;

    public HistorialController() {
        this.historialFirestoreDao = new HistorialFirestoreDao();
    }

    public void agregarHistorial(final Query query, FirebaseUser firebaseUser, final ResultListener<Query> resultListener){
        historialFirestoreDao.agregarHistorial(query, firebaseUser, new ResultListener<Query>() {
            @Override
            public void onFinish(Query result) {
                resultListener.onFinish(result);
            }
        });
    }

    public void getHistorialFirestore(FirebaseUser firebaseUser, final ResultListener<List<Query>> resultListener){
        historialFirestoreDao.getHistorialFirestore(firebaseUser, new ResultListener<List<Query>>() {
            @Override
            public void onFinish(List<Query> result) {
                resultListener.onFinish(result);
            }
        });
    }
}
