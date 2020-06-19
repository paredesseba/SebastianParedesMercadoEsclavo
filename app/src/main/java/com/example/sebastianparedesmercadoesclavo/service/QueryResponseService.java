package com.example.sebastianparedesmercadoesclavo.service;

import com.example.sebastianparedesmercadoesclavo.model.QueryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface QueryResponseService {

    @GET ("search?q=promociones")
    Call<QueryResponse> getQueryResponse();

    @GET ("search?q=descuentos&city=TUxBQ0xBUGxhdGE&shipping_quarantine=guaranteed_delivery")
    Call<QueryResponse> getQueryResponseFilterState();

    @GET ("search")
    Call<QueryResponse> getQueryRSearch(
            @Query("q") String query,
            @Query("offset") Integer offset,
            @Query("limit") Integer limit
            //@Path("{id}") String id,
            //@Query("id") String value
    );

    @GET
    Call<QueryResponse> getSearchAndFilters (@Url String url);


}