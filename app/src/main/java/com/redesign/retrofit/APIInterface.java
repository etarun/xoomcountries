package com.redesign.retrofit;

import com.redesign.model.XoomCountryResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface APIInterface {

    @GET("/catalog/v2/countries")
    Observable<XoomCountryResponse> getXoomCountries(@QueryMap Map<String, String> queryMap);
}
