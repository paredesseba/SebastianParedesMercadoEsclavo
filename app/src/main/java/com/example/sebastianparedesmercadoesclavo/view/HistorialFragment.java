package com.example.sebastianparedesmercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.controller.HistorialController;
import com.example.sebastianparedesmercadoesclavo.databinding.FragmentFavoritosBinding;
import com.example.sebastianparedesmercadoesclavo.databinding.FragmentHistorialBinding;
import com.example.sebastianparedesmercadoesclavo.model.Item;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;


public class HistorialFragment extends Fragment {

    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FragmentHistorialBinding binding;

    public HistorialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistorialBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        HistorialController historialController = new HistorialController();
        historialController.getHistorialFirestore(currentUser, new ResultListener<List<String>>() {
            @Override
            public void onFinish(List<String> result) {
                HistoryListAdapter historyListAdapter = new HistoryListAdapter(result);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                binding.recyclerhistorial.setAdapter(historyListAdapter);
                binding.recyclerhistorial.setLayoutManager(linearLayoutManager);
            }
        });





        return view;
    }
}