package com.example.sebastianparedesmercadoesclavo.service;

import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface QueryResponseService {

    @GET ("search?q=promociones")
    Call<QueryResponse> getQueryResponse();

    @GET ("search?q=descuentos&city=TUxBQ0xBUGxhdGE&shipping_quarantine=guaranteed_delivery")
    Call<QueryResponse> getQueryResponseFilterState();

    @GET ("search")
    Call<QueryResponse> getQueryRSearch(@QueryMap Map <String,Object> params);

}