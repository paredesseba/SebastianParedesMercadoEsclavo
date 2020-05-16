package com.example.sebastianparedesmercadoesclavo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleProductoFragment extends Fragment {

    public static final String CLAVE_PRODUCTO = "producto";

    private ImageView ivproducto;
    private TextView tvnombreproducto;
    private TextView tvprecioproducto;
    private TextView tvdescripcionproducto;

    public DetalleProductoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_producto, container, false);

        inicializarUI(view);

        Bundle bundle = getArguments();
        Producto producto = (Producto) bundle.getSerializable(CLAVE_PRODUCTO);

        ivproducto.setImageResource(producto.getImagen());
        tvprecioproducto.setText("$" + producto.getPrecio());
        tvnombreproducto.setText(producto.getNombre());
        tvdescripcionproducto.setText(producto.getDescripcion());

        return view;
    }

    private void inicializarUI(View view) {
        ivproducto = view.findViewById(R.id.detalleivproducto);
        tvnombreproducto = view.findViewById(R.id.detalletvnombre);
        tvprecioproducto = view.findViewById(R.id.detalletvprecio);
        tvdescripcionproducto = view.findViewById(R.id.detalletvdescripcion);
    }
}
