package com.example.sebastianparedesmercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.databinding.FragmentItemPictureBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemPictureFragment extends Fragment {

    public static final String KEY_PIC = "pic";
    private FragmentItemPictureBinding binding;


    public ItemPictureFragment() {
        // Required empty public constructor
    }


    public static ItemPictureFragment crearItemPictureFragment(String urlPic){
        ItemPictureFragment fragment = new ItemPictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PIC, urlPic);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentItemPictureBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Bundle url = getArguments();
        String urlpic = url.getString(KEY_PIC);

        Glide.with(getContext()).load(urlpic).centerCrop().into(binding.picture);

        return view;
    }

}