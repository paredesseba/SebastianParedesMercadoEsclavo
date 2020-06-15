package com.example.sebastianparedesmercadoesclavo.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.controller.QueryResponseController;
import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;
import com.example.sebastianparedesmercadoesclavo.model.Result;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class ResultListFragment extends Fragment implements ResultListAdapter.ResultListAdapterListener {

    private RecyclerView recyclerView;
    private RecyclerView recyclerViewUbicacion;
    private ResultListFragmentListener resultListFragmentListener;


    public ResultListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.resultListFragmentListener = (ResultListFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result_list, container, false);

        recyclerView = view.findViewById(R.id.fragmentresultlistrecycler);
        recyclerViewUbicacion = view.findViewById(R.id.fragmentresultlistrecyclerubicacion);

        QueryResponseController queryResponseController = new QueryResponseController();
        queryResponseController.getQueryResponse(new ResultListener<QueryResponse>() {
            @Override
            public void onFinish(QueryResponse result) {

                ResultListAdapter resultListAdapter = new ResultListAdapter(result.getResults(),ResultListFragment.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setAdapter(resultListAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);

            }
        });

        queryResponseController.getQueryResponseFilterState(new ResultListener<QueryResponse>() {
            @Override
            public void onFinish(QueryResponse result) {
                ResultListAdapter resultListAdapter = new ResultListAdapter(result.getResults(),ResultListFragment.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                recyclerViewUbicacion.setAdapter(resultListAdapter);
                recyclerViewUbicacion.setLayoutManager(linearLayoutManager);

            }
        });


        return view;
    }

    @Override
    public void onClickResult(String id) {
        resultListFragmentListener.onClickResult(id);
    }

    public interface ResultListFragmentListener{
        void onClickResult(String id);
    }
}