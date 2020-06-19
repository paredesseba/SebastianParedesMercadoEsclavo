package com.example.sebastianparedesmercadoesclavo.controller;

import com.example.sebastianparedesmercadoesclavo.dao.QueryResponseDao;
import com.example.sebastianparedesmercadoesclavo.model.Filter;
import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

import java.util.List;

import static com.example.sebastianparedesmercadoesclavo.dao.QueryResponseDao.BASE_URL;

public class QueryResponseController {

    private QueryResponseDao queryResponseDao;
    private static final Integer PAGE_SIZE_LIMIT = 50;
    private Integer offset = 0;
    private static final String ENDPOINT_BASE = "search?q=";


    public QueryResponseController() {
        this.queryResponseDao = new QueryResponseDao();
        this.offset = 0;
    }

    public void getQueryResponse(final ResultListener<QueryResponse> queryResponseResultListener){
        this.queryResponseDao.getQueryResponse(new ResultListener<QueryResponse>() {
            @Override
            public void onFinish(QueryResponse result) {
                queryResponseResultListener.onFinish(result);
            }
        });
    }

    public void getQueryResponseFilterState(final ResultListener<QueryResponse> queryResponseResultListener){
        this.queryResponseDao.getQueryResponseFilterState(new ResultListener<QueryResponse>() {
            @Override
            public void onFinish(QueryResponse result) {
                queryResponseResultListener.onFinish(result);
            }
        });
    }

    public void getQueryRSearch(final ResultListener<QueryResponse> queryResponseResultListener, String query, Integer offset, Integer limit){
        this.queryResponseDao.getQueryRSearch(new ResultListener<QueryResponse>() {
            @Override
            public void onFinish(QueryResponse result) {
                queryResponseResultListener.onFinish(result);
            }
        }, query, offset, limit);
    }

    //metodo usado al aplicar filtros o paginacion
    public void getSearchAndFilters(final ResultListener<QueryResponse> queryResponseResultListener, String query, Integer offset, Integer limit, String filterID, String filterValue, List<Filter> filters){
        String url = armarURL(query,offset,limit,filterID,filterValue, filters);
        this.queryResponseDao.getSearchAndFilters(new ResultListener<QueryResponse>() {
            @Override
            public void onFinish(QueryResponse result) {
                queryResponseResultListener.onFinish(result);
            }
        }, url);
    }

    public String armarURL (String query, Integer offset, Integer limit, String filterID, String filterValue, List<Filter> filters){
        String url;
        String addFilters = new String();
        for (int i = 0; i < filters.size(); i++) {
            addFilters = addFilters + "&"+filters.get(i).getId()+"="+filters.get(i).getValueFilters().get(0).getId();
        }
        if (filterID == null && filterValue == null){
            url = BASE_URL + "search?q=" + query + "&limit=" + limit + "&offset=" + offset + addFilters;
        }
        else{
            url = BASE_URL + "search?q=" + query + "&limit=" + limit + "&offset=" + offset + "&" + filterID + "=" + filterValue + addFilters;
        }
        return url;
    }
}