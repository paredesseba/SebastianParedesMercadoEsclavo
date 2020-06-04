package com.example.sebastianparedesmercadoesclavo.dao;

import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;
import com.example.sebastianparedesmercadoesclavo.service.QueryResponseService;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QueryResponseDao {

    protected static final String BASE_URL = "https://api.mercadolibre.com/sites/MLA/";
    private Retrofit retrofit;
    private QueryResponseService queryResponseService;

    public QueryResponseDao() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.queryResponseService = retrofit.create(QueryResponseService.class);
    }

    public void getQueryResponse(final ResultListener<QueryResponse> resultListener){
        Call<QueryResponse> queryResponseCall = queryResponseService.getQueryResponse();
        queryResponseCall.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                if (response.isSuccessful()){
                    QueryResponse queryResponse = response.body();
                    resultListener.onFinish(queryResponse);
                }
                else{
                    response.errorBody();
                }
            }
            @Override
            public void onFailure(Call<QueryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getQueryResponseFilterState(final ResultListener<QueryResponse> resultListener){
        Call<QueryResponse> queryResponseCall = queryResponseService.getQueryResponseFilterState();
        queryResponseCall.enqueue(new Callback<QueryResponse>() {
            @Override
            public void onResponse(Call<QueryResponse> call, Response<QueryResponse> response) {
                if (response.isSuccessful()){
                    QueryResponse queryResponse = response.body();
                    resultListener.onFinish(queryResponse);
                }
                else{
                    response.errorBody();
                }
            }
            @Override
            public void onFailure(Call<QueryResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
