package com.example.sebastianparedesmercadoesclavo.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.controller.ItemController;
import com.example.sebastianparedesmercadoesclavo.model.Attribute;
import com.example.sebastianparedesmercadoesclavo.model.Item;
import com.example.sebastianparedesmercadoesclavo.model.Result;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultDetailFragment extends Fragment {

    public static final String KEY_ID = "id";

    private ImageView ivresult;
    private TextView tvtitle;
    private TextView tvprice;
    private TextView tvaddress;
    private TextView tvcondition;
    private TextView tvneighborhood;


    public ResultDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_detail, container, false);
        Bundle bundle = getArguments();
        String id = bundle.getString(KEY_ID);

        ivresult = view.findViewById(R.id.fresultdetailiv);
        tvtitle = view.findViewById(R.id.fresultdetailtvtitle);
        tvprice = view.findViewById(R.id.fresultdetailtvprice);
        tvaddress = view.findViewById(R.id.fresultdetailtvaddress);
        tvcondition = view.findViewById(R.id.fresultdetailtvcondition);
        tvneighborhood = view.findViewById(R.id.fresultdetailtvneighborhood);

        ItemController itemController = new ItemController();
        itemController.getItem(new ResultListener<Item>() {
            @Override
            public void onFinish(Item result) {
                Glide.with(getContext()).load(result.getPictures().get(0).getUrl()).centerCrop().into(ivresult);
                tvtitle.setText(result.getTitle());
                tvprice.setText("$" + result.getPrice());
                tvaddress.setText(result.getSellerAddress().getCity().getName() + " - " +
                        result.getSellerAddress().getState().getName());
                if (result.getCondition() != null){
                    tvcondition.setText(result.getCondition());
                }
                if (result.getLocation().getNeighborhood() != null){
                    if (result.getLocation().getNeighborhood().getName() != null){
                        tvneighborhood.setText(result.getLocation().getNeighborhood().getName());
                    }
                }
            }
        }, id);


        return view;
    }

}