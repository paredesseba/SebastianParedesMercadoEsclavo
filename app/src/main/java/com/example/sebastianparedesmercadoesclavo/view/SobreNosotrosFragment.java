package com.example.sebastianparedesmercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sebastianparedesmercadoesclavo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SobreNosotrosFragment extends Fragment {

    public SobreNosotrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sobre_nosotros, container, false);
    }
}
