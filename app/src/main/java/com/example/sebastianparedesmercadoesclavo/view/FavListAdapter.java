package com.example.sebastianparedesmercadoesclavo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.model.Item;

import java.util.List;

public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.ViewHolderFav> {

    private List<Item> itemList;

    public FavListAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderFav onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.celda_favorito, parent, false);
        return new FavListAdapter.ViewHolderFav(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFav holder, int position) {
        Item item = this.itemList.get(position);
        holder.onBind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    protected class ViewHolderFav extends RecyclerView.ViewHolder{

        private ImageView ivitem;
        private TextView tvitem;

        public ViewHolderFav(@NonNull View itemView) {
            super(itemView);

            ivitem = itemView.findViewById(R.id.imagenfav);
            tvitem = itemView.findViewById(R.id.titulofav);
        }

        public void onBind(Item item) {
            Glide.with(itemView.getContext()).load(item.getThumbnail()).centerCrop().into(ivitem);
            tvitem.setText(item.getTitle());

        }
    }

}
