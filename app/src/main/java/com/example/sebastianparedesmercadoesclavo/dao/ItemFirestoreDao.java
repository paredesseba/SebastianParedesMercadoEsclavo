package com.example.sebastianparedesmercadoesclavo.dao;

import androidx.annotation.NonNull;

import com.example.sebastianparedesmercadoesclavo.model.Item;
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

public class ItemFirestoreDao {

    private FirebaseFirestore db;

    public static final String COLECC_FAVORITOS = "favoritos";
    public static final String MIS_FAVORITOS = "misfavoritos";

    public ItemFirestoreDao(){
        this.db = FirebaseFirestore.getInstance();
    }

    public void agregarItemAFavoritosFirestore(final Item item, FirebaseUser firebaseUser, final ResultListener<Item> listener){
        db.collection(COLECC_FAVORITOS)
                .document(firebaseUser.getUid())
                .collection(MIS_FAVORITOS)
                .document(item.getId())
                .set(item)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        listener.onFinish(item);
                    }
                });
    }

    public void getItemListFirestore(FirebaseUser firebaseUser, final ResultListener<List<Item>> listener){
        db.collection(COLECC_FAVORITOS)
            .document(firebaseUser.getUid())
                .collection(MIS_FAVORITOS)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            List<Item> itemList = new ArrayList<>();
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                Item item = queryDocumentSnapshot.toObject(Item.class);
                                itemList.add(item);
                            }
                            listener.onFinish(itemList);
                        }
                    }
                });
    }

}
