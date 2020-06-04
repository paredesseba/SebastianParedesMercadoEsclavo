package com.example.sebastianparedesmercadoesclavo.controller;

import com.example.sebastianparedesmercadoesclavo.dao.QueryResponseDao;
import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

public class QueryResponseController {

    private QueryResponseDao queryResponseDao;


    public QueryResponseController() {
        this.queryResponseDao = new QueryResponseDao();
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
}