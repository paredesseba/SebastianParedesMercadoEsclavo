package com.example.sebastianparedesmercadoesclavo.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.hardware.Camera;
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
import android.widget.Toast;

import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.controller.QueryResponseController;
import com.example.sebastianparedesmercadoesclavo.databinding.FragmentSearchBinding;
import com.example.sebastianparedesmercadoesclavo.model.Filter;
import com.example.sebastianparedesmercadoesclavo.model.Query;
import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;
import com.example.sebastianparedesmercadoesclavo.model.ValueFilter;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 *
 */


public class SearchFragment extends Fragment implements ResultListAdapter.ResultListAdapterListener {

    //parametros fijos del querymap
    public static final String KEY_Q = "q";
    public static final String KEY_OFFSET = "offset";
    public static final String KEY_LIMIT = "limit";
    public static final Integer SIZE_PAGE = 50;
    private String filterid;
    private String filtrovalue;
    private Integer paginas = 0;
    private FragmentSearchBinding binding;
    private SearchFragmentListener listener;
    private Integer paginaactual;
    private Integer offset;
    private QueryResponse resultados;
    private QueryResponseController queryResponseController;
    private Map<String, Object> params = new HashMap<>();
    private Query query;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;



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

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //ocultar layout de paginacion
        binding.btnprev.setVisibility(View.GONE);
        binding.btnnext.setVisibility(View.GONE);
        binding.tvpagina.setVisibility(View.GONE);
        binding.layoutfilters.setVisibility(View.GONE);

        //creo controller
        queryResponseController = new QueryResponseController();

        //busqueda nueva
        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //enter
                if (query != null) { //Osea si hay algo que buscar

                    Query query1 = new Query();
                    query1.setQuery(query);
                    listener.agregarHistorial(query1);


                    //visibilizo UI
                    binding.layoutfilters.setVisibility(View.VISIBLE);
                    binding.layoutpaginate.setVisibility(View.VISIBLE);
                    binding.btnprev.setVisibility(View.VISIBLE);
                    binding.btnnext.setVisibility(View.VISIBLE);
                    binding.tvpagina.setVisibility(View.VISIBLE);

                    //inicializo el punto de busqueda en el total de resultados
                    offset = 0;

                    //inicializo la pagina actual
                    paginaactual = 1;

                    //armo el map
                    params.clear();
                    params.put(KEY_Q, query);
                    params.put(KEY_LIMIT, SIZE_PAGE);
                    params.put(KEY_OFFSET, offset);

                    //BUSCO
                    queryResponseController.getQueryRSearch(new ResultListener<QueryResponse>() {
                        @Override
                        public void onFinish(QueryResponse result) {
                            resultados = result;
                            updateRecycler(result);
                            controlPaging(result);
                            setAdapterFilterID(result);
                        }
                    }, params);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    binding.layoutfilters.setVisibility(View.GONE);
                    binding.layoutpaginate.setVisibility(View.GONE);
                return true;
            }
        });

        //listener boton pagina anterior
        binding.btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paginaactual > 1){
                    offset = offset - SIZE_PAGE; //y vuelvo a buscar
                    params.put(KEY_OFFSET, offset);
                    queryResponseController.getQueryRSearch(new ResultListener<QueryResponse>() {
                        @Override
                        public void onFinish(QueryResponse result) {
                            resultados = result;
                            updateRecycler(result);
                        }
                    }, params);
                    paginaactual = paginaactual - 1;
                    binding.tvpagina.setText("Pagina " + paginaactual + " de "+ paginas);
                }
                else
                {
                    Toast.makeText(getContext(),"No podes ir a la pagina anterior", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //listener boton pagina siguiente
        binding.btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (paginaactual < paginas){
                    offset = offset + SIZE_PAGE;
                    params.put(KEY_OFFSET, offset); //y vuelvo a buscar
                    queryResponseController.getQueryRSearch(new ResultListener<QueryResponse>() {
                        @Override
                        public void onFinish(QueryResponse result) {
                            resultados = result;
                            updateRecycler(result);
                        }
                    }, params);
                    paginaactual = paginaactual +1;
                    binding.tvpagina.setText("Pagina " + paginaactual + " de "+ paginas);
                }
                else
                {
                    Toast.makeText(getContext(),"No podes ir a la pagina siguiente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.spinnerFilterID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Filter filter = (Filter) binding.spinnerFilterID.getSelectedItem();
                filterid = filter.getId();
                setAdapterValueFilter(filter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //no hacer nada
            }
        });

        binding.spinnerFilterValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ValueFilter valueFilter = (ValueFilter) binding.spinnerFilterValue.getSelectedItem();
                if (filterid != null){
                    filtrovalue = valueFilter.getId();
                }
                else {
                    Toast.makeText(getContext(),"Primero selecciona un tipo de filtro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //no hacer nada
            }
        });

        binding.btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.put(filterid, filtrovalue);
                filterid = null;
                filtrovalue = null;
                queryResponseController.getQueryRSearch(new ResultListener<QueryResponse>() {
                    @Override
                    public void onFinish(QueryResponse result) {
                        resultados = result;
                        updateRecycler(result);
                        controlPaging(result);
                        setAdapterFilterID(result);
                    }
                }, params);

            }
        });

        binding.btnclearfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQueryAndCreateNewHashmap(params);
                queryResponseController.getQueryRSearch(new ResultListener<QueryResponse>() {
                    @Override
                    public void onFinish(QueryResponse result) {
                        resultados = result;
                        updateRecycler(result);
                        controlPaging(result);
                        setAdapterFilterID(result);
                    }
                }, params);
            }
        });


        return view;
    }


    private void saveQueryAndCreateNewHashmap(Map<String,Object> params) {
        offset=0;
        params.clear();
        params.put(KEY_Q, binding.searchview.getQuery().toString());
        params.put(KEY_OFFSET, offset);
        params.put(KEY_LIMIT,SIZE_PAGE);

    }

    private void setAdapterValueFilter(Filter filterID) {
        List<ValueFilter> filterValueList = new ArrayList<>();
        for (int i = 0; i < filterID.getValueFilters().size(); i++) {
            ValueFilter valueFilter = filterID.getValueFilters().get(i);
            filterValueList.add(valueFilter);
        }

        ArrayAdapter<ValueFilter> valueFilterArrayAdapter = new ArrayAdapter<ValueFilter>(SearchFragment.this.getContext(), R.layout.support_simple_spinner_dropdown_item, filterValueList);
        binding.spinnerFilterValue.setAdapter(valueFilterArrayAdapter);
    }

    private void setAdapterFilterID(QueryResponse resultados) {
        //creo lista para su adapter
        List<Filter> filterIDlist = new ArrayList<>();
        for (int i = 0; i < resultados.getAvailableFilters().size(); i++) {
            Filter filterID = resultados.getAvailableFilters().get(i);
            filterIDlist.add(filterID);
        }
        //set de la lista al adapter, set del adapter al spinner
        ArrayAdapter<Filter> filterIDArrayAdapter = new ArrayAdapter<Filter>(SearchFragment.this.getContext(), R.layout.support_simple_spinner_dropdown_item, filterIDlist);
        binding.spinnerFilterID.setAdapter(filterIDArrayAdapter);

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
        void agregarHistorial (Query query);
    }

}