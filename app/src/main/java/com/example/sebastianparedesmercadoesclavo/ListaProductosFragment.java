package com.example.sebastianparedesmercadoesclavo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaProductosFragment extends Fragment {

    public ListaProductosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_productos, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragmentlistarecycler);

        List<Producto> productoList = ProveedorDeProductos.getProductos();
        AdapterProducto adapterProducto = new AdapterProducto(productoList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapterProducto);
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }
}
