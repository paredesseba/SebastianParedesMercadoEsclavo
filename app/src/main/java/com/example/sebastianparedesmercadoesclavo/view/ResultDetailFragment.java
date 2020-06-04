package com.example.sebastianparedesmercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.model.Attribute;
import com.example.sebastianparedesmercadoesclavo.model.Result;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultDetailFragment extends Fragment {

    public static final String KEY_RESULT = "result";

    private ImageView ivresult;
    private TextView tvtitle;
    private TextView tvprice;
    private TextView tvaddress;
    private TextView tvattributeone;
    private TextView tvattributetwo;
    private TextView tvattributethree;

    private List<Attribute> attributes = new ArrayList<Attribute>();


    public ResultDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_detail, container, false);
        Bundle bundle = getArguments();
        Result result = (Result) bundle.getSerializable(KEY_RESULT);
        attributes = result.getAttributes();

        ivresult = view.findViewById(R.id.fresultdetailiv);
        tvtitle = view.findViewById(R.id.fresultdetailtvtitle);
        tvprice = view.findViewById(R.id.fresultdetailtvprice);
        tvaddress = view.findViewById(R.id.fresultdetailtvaddress);
        tvattributeone = view.findViewById(R.id.fresultdetailtvattributeone);
        tvattributetwo = view.findViewById(R.id.fresultdetailtvattributetwo);
        tvattributethree = view.findViewById(R.id.fresultdetailtvattributethree);


        Glide.with(getContext()).load(result.getThumbnail()).centerCrop().into(ivresult);
        tvtitle.setText(result.getTitle());
        tvprice.setText("$" + result.getPrice());
        tvaddress.setText(result.getAddress().getCityName()+" - "+result.getAddress().getStateName());
        tvattributeone.setText(attributes.get(0).getName()+": "+attributes.get(0).getValueName());
        tvattributetwo.setText(attributes.get(1).getName()+": "+attributes.get(1).getValueName());
        tvattributethree.setText(attributes.get(2).getName()+": "+attributes.get(2).getValueName());

        return view;
    }

}