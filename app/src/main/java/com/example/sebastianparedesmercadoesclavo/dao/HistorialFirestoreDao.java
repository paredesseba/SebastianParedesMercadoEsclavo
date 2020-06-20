package com.example.sebastianparedesmercadoesclavo.dao;

import androidx.annotation.NonNull;

import com.example.sebastianparedesmercadoesclavo.model.Item;
import com.example.sebastianparedesmercadoesclavo.model.Query;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HistorialFirestoreDao {

    private FirebaseFirestore db;
    public static final String COLECC_HISTORIAL = "historial";
    public static final String MI_HISTORIAL = "mihistorial";

    public HistorialFirestoreDao() {
        this.db = FirebaseFirestore.getInstance();
    }

    public void agregarHistorial(final Query query, FirebaseUser firebaseUser, final ResultListener<Query> resultListener){
        db.collection(COLECC_HISTORIAL)
                .document(firebaseUser.getUid())
                .collection(MI_HISTORIAL)
                .document()
                .set(query)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        resultListener.onFinish(query);
                    }
                });
    }

    public void getHistorialFirestore(FirebaseUser firebaseUser, final ResultListener<List<Query>> listener){
        db.collection(COLECC_HISTORIAL)
                .document(firebaseUser.getUid())
                .collection(MI_HISTORIAL)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            List<Query> queryList = new ArrayList<>();
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                Query query = (Query) queryDocumentSnapshot.toObject(Query.class);
                                queryList.add(query);
                            }
                            listener.onFinish(queryList);
                        }
                    }
                });
    }
}
