package com.example.sebastianparedesmercadoesclavo.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.controller.QueryResponseController;
import com.example.sebastianparedesmercadoesclavo.databinding.FragmentSearchBinding;
import com.example.sebastianparedesmercadoesclavo.model.Filter;
import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;
import com.example.sebastianparedesmercadoesclavo.model.ValueFilter;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements ResultListAdapter.ResultListAdapterListener {

    public static final Integer SIZE_PAGE = 50;
    private FragmentSearchBinding binding;
    private SearchFragmentListener listener;
    private Integer paginas = 0;
    private Integer paginaactual;
    private Integer offset;
    private QueryResponse resultados;
    private QueryResponseController queryResponseController;
    private String valuefilterid;
    private String filterid;


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

        //ocultar layout de paginacion
        binding.btnprev.setVisibility(View.GONE);
        binding.btnnext.setVisibility(View.GONE);
        binding.tvpagina.setVisibility(View.GONE);

        //creo controller
        queryResponseController = new QueryResponseController();

        //busqueda nueva
        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) { //enter
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
                        public void onFinish(final QueryResponse result) {

                            resultados = result;

                            //actualizo recyclerview
                            updateRecycler(result);

                            //seteo el layout de paginacion
                            controlPaging(result);

                            //seteo filtrosID
                            setAdapterFilterID(result);


                        }
                    }, query, offset, SIZE_PAGE);
                }
                return false;
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
                            updateRecycler(result);
                        }
                    }, binding.searchview.getQuery().toString(), offset, SIZE_PAGE);
                    paginaactual = paginaactual - 1;
                    binding.tvpagina.setText("Pagina " + paginaactual + " de "+ paginas);
                }
            }
        });

        //listener boton pagina siguiente
        binding.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paginaactual < paginas){
                    offset = offset + SIZE_PAGE; //y vuelvo a buscar
                    queryResponseController.getQueryRSearch(new ResultListener<QueryResponse>() {
                        @Override
                        public void onFinish(QueryResponse result) {
                            updateRecycler(result);
                        }
                    }, binding.searchview.getQuery().toString(), offset, SIZE_PAGE);
                    paginaactual = paginaactual +1;
                    binding.tvpagina.setText("Pagina " + paginaactual + " de "+ paginas);
                }
            }
        });


        binding.spinnerFilterID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setAdapterValueFilter(position);
                Filter filter = (Filter) binding.spinnerFilterID.getSelectedItem();
                filterid = filter.getId().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filterid = null;
            }
        });

        binding.spinnerFilterValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ValueFilter valueFilter = (ValueFilter) binding.spinnerFilterValue.getSelectedItem();
                valuefilterid = valueFilter.getId().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                valuefilterid = null;
            }
        });

        binding.btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offset = 0;
                queryResponseController.getSearchAndFilters(new ResultListener<QueryResponse>() {
                    @Override
                    public void onFinish(QueryResponse result) {
                        updateRecycler(result);
                        controlPaging(result);
                        setAdapterFilterID(result);
                    }
                }, binding.searchview.getQuery().toString(), offset, SIZE_PAGE, filterid, valuefilterid, resultados.getFilters());


            }
        });


        return view;
    }



    //METODOS DEL FRAGMENT






    private void setAdapterValueFilter(int position) {
        List<ValueFilter> filterValueList = new ArrayList<>();
        for (int i = 0; i < resultados.getAvailableFilters().get(position).getValueFilters().size(); i++) {
            ValueFilter valueFilter = resultados.getAvailableFilters().get(position).getValueFilters().get(i);
            filterValueList.add(valueFilter);
        }
        ArrayAdapter<ValueFilter> valueFilterArrayAdapter = new ArrayAdapter<ValueFilter>(SearchFragment.this.getContext(), R.layout.support_simple_spinner_dropdown_item, filterValueList);
        binding.spinnerFilterValue.setAdapter(valueFilterArrayAdapter);
    }

    private void setAdapterFilterID(QueryResponse result) {
        List<Filter> filterIDlist = new ArrayList<>();
        for (int i = 0; i < result.getAvailableFilters().size(); i++) {
            Filter filterID = result.getAvailableFilters().get(i);
            filterIDlist.add(filterID);
        }
        ArrayAdapter<Filter> filterIDArrayAdapter = new ArrayAdapter<Filter>(SearchFragment.this.getContext(), R.layout.support_simple_spinner_dropdown_item, filterIDlist);
        binding.spinnerFilterID.setAdapter(filterIDArrayAdapter);

        binding.spinnerFilterID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setAdapterValueFilter(position);
                Filter filter = (Filter) binding.spinnerFilterID.getSelectedItem();
                filterid = filter.getId().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                filterid = null;
            }
        });
    }

    private void controlPaging(QueryResponse result) {
        //chequeo cantidad de paginas
        paginas = Integer.parseInt(result.getPaging().getTotal()) / Integer.parseInt(result.getPaging().getLimit());
        if ((Integer.parseInt(result.getPaging().getTotal()) % Integer.parseInt(result.getPaging().getLimit())) != 0){
            paginas = paginas +1;
        }

        //seteo textview paginacion
        binding.tvpagina.setText("Pagina "+ paginaactual + " de " + paginas);
    }

    private void updateRecycler(QueryResponse result) {
        ResultListAdapter resultListAdapter = new ResultListAdapter(result.getResults(), SearchFragment.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.containersearch.setAdapter(resultListAdapter);
        binding.containersearch.setLayoutManager(linearLayoutManager);
    }




    // LISTENER + INTERFACE



    @Override
    public void onClickResult(String id) {
        listener.onClickSearchFResult(id);
    }

    public interface SearchFragmentListener {
        void onClickSearchFResult(String id);
    }

}