package com.example.sebastianparedesmercadoesclavo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sebastianparedesmercadoesclavo.R;

import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolderHistory> {

    private List<String> historyList;


    public HistoryListAdapter(List<String> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolderHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.celda_historial, parent, false);
        return new HistoryListAdapter.ViewHolderHistory(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHistory holder, int position) {
        String string = this.historyList.get(position);
        holder.onBind(string);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    protected class ViewHolderHistory extends RecyclerView.ViewHolder{

        private TextView tv;

        public ViewHolderHistory(@NonNull View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.tvceldahistorial);
        }

        public void onBind(String string) {
            tv.setText(string);
        }
    }
}
