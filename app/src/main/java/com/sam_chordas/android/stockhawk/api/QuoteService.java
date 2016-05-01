package com.sam_chordas.android.stockhawk.api;

import com.sam_chordas.android.stockhawk.api.model.Result;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * @author albinmathew
 * @date 01/05/16.
 */
public interface QuoteService {

    //So these are the list available in our WEB API and the methods look straight forward
    @GET("/public/yql")
    void getHistoricalData(@Query("q") String q, @Query("diagnostics") String diagnostics,
                           @Query("env") String env, @Query("format") String format,
                           Callback<Result> cb
    );
}
