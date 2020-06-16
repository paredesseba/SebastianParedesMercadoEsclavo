package com.example.sebastianparedesmercadoesclavo.controller;

import com.example.sebastianparedesmercadoesclavo.dao.QueryResponseDao;
import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

public class QueryResponseController {

    private QueryResponseDao queryResponseDao;
    private static final Integer PAGE_SIZE_LIMIT = 50;
    private Integer offset = 0;


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
}