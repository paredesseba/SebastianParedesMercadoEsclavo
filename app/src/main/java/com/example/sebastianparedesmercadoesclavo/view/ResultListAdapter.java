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
import com.example.sebastianparedesmercadoesclavo.model.Result;

import java.util.List;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ViewHolderResult> {

    private List<Result> resultList;
    private ResultListAdapterListener resultListAdapterListener;


    public ResultListAdapter(List<Result> resultList, ResultListAdapterListener listener) {
        this.resultList = resultList;
        this.resultListAdapterListener = listener;
    }

    @NonNull
    @Override
    public ViewHolderResult onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.celda_producto, parent, false);
        return new ViewHolderResult(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderResult holder, int position) {
        Result result = this.resultList.get(position);
        holder.onBind(result);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    protected class ViewHolderResult extends RecyclerView.ViewHolder {

        private ImageView ivResult;
        private TextView tvtitleResult;
        private TextView tvpriceResult;
        private TextView tvcityResult;

        public ViewHolderResult(@NonNull View itemView) {
            super(itemView);
            ivResult = itemView.findViewById(R.id.celdaivproducto);
            tvtitleResult = itemView.findViewById(R.id.celdatvnombre);
            tvpriceResult = itemView.findViewById(R.id.celdatvprice);
            tvcityResult = itemView.findViewById(R.id.celdatvciudad);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Result result = resultList.get(getAdapterPosition());
                    resultListAdapterListener.onClickResult(result);
                }
            });
        }

        public void onBind(Result result) {
            Glide.with(itemView.getContext()).load(result.getThumbnail()).centerCrop().into(ivResult);
            tvtitleResult.setText(result.getTitle());
            tvpriceResult.setText("$" + result.getPrice());
            tvcityResult.setText(result.getAddress().getCityName() + " - " + result.getAddress().getStateName());
        }
    }

    public interface ResultListAdapterListener{
        void onClickResult(Result result);
    }
}