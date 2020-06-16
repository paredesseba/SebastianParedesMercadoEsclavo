package com.example.sebastianparedesmercadoesclavo.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.controller.QueryResponseController;
import com.example.sebastianparedesmercadoesclavo.databinding.FragmentSearchBinding;
import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements ResultListAdapter.ResultListAdapterListener {

    private SearchView searchView;
    private RecyclerView recyclerView;
    public static final Integer SIZE_PAGE = 50;
    private FragmentSearchBinding binding;
    private SearchFragmentListener listener;
    private Integer paginas = 0;
    private Integer paginaactual;
    private Integer offset;
    private QueryResponseController queryResponseController;


    public SearchFragment() {
        // Required empty public constructor
    }

    public SearchFragment(SearchFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.listener = (SearchFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //TODO llenar el recycler con el historial de busquedas (o no)

        binding.btnprev.setVisibility(View.GONE);
        binding.btnnext.setVisibility(View.GONE);
        binding.tvpagina.setVisibility(View.GONE);

        queryResponseController = new QueryResponseController();

        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //enter
                if (query != null) { //Osea si hay algo que buscar

                    //Visibilizo la UI de paginacion
                    binding.btnprev.setVisibility(View.VISIBLE);
                    binding.btnnext.setVisibility(View.VISIBLE);
                    binding.tvpagina.setVisibility(View.VISIBLE);

                    //inicializo el punto de busqueda en el total de resultados
                    offset = 0;

                    //inicializo la pagina actual
                    paginaactual = 1;

                    //busco
                    queryResponseController.getQueryRSearch(new ResultListener<QueryResponse>() {
                        @Override
                        public void onFinish(QueryResponse result) {

                            //actualizo recyclerview
                            ResultListAdapter resultListAdapter = new ResultListAdapter(result.getResults(), SearchFragment.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            binding.containerhistorial.setAdapter(resultListAdapter);
                            binding.containerhistorial.setLayoutManager(linearLayoutManager);

                            //chequeo cantidad de paginas
                            paginas = Integer.parseInt(result.getPaging().getTotal()) / Integer.parseInt(result.getPaging().getLimit());
                            if ((Integer.parseInt(result.getPaging().getTotal()) % Integer.parseInt(result.getPaging().getLimit())) != 0){
                                paginas = paginas +1;
                            }

                            //seteo textview paginacion
                            binding.tvpagina.setText("Pagina "+ paginaactual + " de " + paginas);

                        }
                    }, query, offset, SIZE_PAGE);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //no hacer nada
                return true;
            }
        });

        //listener boton pagina anterior
        binding.btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paginaactual > 1){
                    offset = offset - SIZE_PAGE; //y vuelvo a buscar
                    queryResponseController.getQueryRSearch(new ResultListener<QueryResponse>() {
                        @Override
                        public void onFinish(QueryResponse result) {
                            ResultListAdapter resultListAdapter = new ResultListAdapter(result.getResults(), SearchFragment.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            binding.containerhistorial.setAdapter(resultListAdapter);
                            binding.containerhistorial.setLayoutManager(linearLayoutManager);
                        }
                    }, binding.searchview.getQuery().toString(), offset, SIZE_PAGE);
                    paginaactual = paginaactual - 1;
                    binding.tvpagina.setText("Pagina " + paginaactual + " de "+ paginas);
                }
            }
        });

        binding.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paginaactual < paginas){
                    offset = offset + SIZE_PAGE; //y vuelvo a buscar
                    queryResponseController.getQueryRSearch(new ResultListener<QueryResponse>() {
                        @Override
                        public void onFinish(QueryResponse result) {
                            ResultListAdapter resultListAdapter = new ResultListAdapter(result.getResults(), SearchFragment.this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                            binding.containerhistorial.setAdapter(resultListAdapter);
                            binding.containerhistorial.setLayoutManager(linearLayoutManager);
                        }
                    }, binding.searchview.getQuery().toString(), offset, SIZE_PAGE);
                    paginaactual = paginaactual +1;
                    binding.tvpagina.setText("Pagina " + paginaactual + " de "+ paginas);
                }
            }
        });

        return view;
    }

    @Override
    public void onClickResult(String id) {
        listener.onClickSearchFResult(id);
    }

    public interface SearchFragmentListener {
        void onClickSearchFResult(String id);
    }

}