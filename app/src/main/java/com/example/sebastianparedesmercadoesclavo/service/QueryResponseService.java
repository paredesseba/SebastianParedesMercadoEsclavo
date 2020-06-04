package com.example.sebastianparedesmercadoesclavo.service;

import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QueryResponseService {

    @GET ("search?q=promociones")
    Call<QueryResponse> getQueryResponse();


    @GET ("search?q=descuentos&city=TUxBQ0xBUGxhdGE&shipping_quarantine=guaranteed_delivery")
    Call<QueryResponse> getQueryResponseFilterState();

}