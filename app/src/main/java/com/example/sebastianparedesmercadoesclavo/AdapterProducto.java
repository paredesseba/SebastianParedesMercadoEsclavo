package com.example.sebastianparedesmercadoesclavo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ViewHolderProducto> {

    List<Producto> productoList;

    public AdapterProducto(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.celda_producto, parent, false);
        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto producto = this.productoList.get(position);
        holder.onBind(producto);
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    protected class ViewHolderProducto extends RecyclerView.ViewHolder{

        ImageView ivproducto;
        TextView tvprecioproducto;
        TextView tvnombreproducto;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            ivproducto = itemView.findViewById(R.id.celdaivproducto);
            tvprecioproducto = itemView.findViewById(R.id.celdatvprecio);
            tvnombreproducto = itemView.findViewById(R.id.celdatvnombre);
        }

        public void onBind(Producto producto) {
            ivproducto.setImageResource(producto.getImagen());
            tvprecioproducto.setText("$" + producto.getPrecio());
            tvnombreproducto.setText(producto.getNombre());
        }
    }
}
