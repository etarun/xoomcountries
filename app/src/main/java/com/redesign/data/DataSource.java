package com.redesign.data;


import com.redesign.model.XoomCountry;
import com.redesign.model.XoomCountryResponse;
import com.redesign.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;


public class DataSource {

    private static final String QUERY_PAGE = "page";
    private static final String QUERY_PAGE_SIZE = "page_size";

    private Map<String, String> favCountries;

    private APIInterface apiInterface;

    // Memory cache of data
    private List<XoomCountry> memory = null;

    private Map<String, String> queryMap;

    public DataSource(APIInterface apiInterface) {
        this.apiInterface = apiInterface;

    }

    Observable<List<XoomCountry>> getXoomCountriesList(int page, int pageSize, Map<String, String> favCountries) {
        queryMap = new HashMap<>();
        queryMap.put(QUERY_PAGE, String.valueOf(page));
        queryMap.put(QUERY_PAGE_SIZE, String.valueOf(pageSize));
        this.favCountries = favCountries;

        return Observable.concat(
                memory(),
                network()
        ).first(data -> data != null);
    }

    public Observable<List<XoomCountry>> network() {
        return apiInterface.getXoomCountries(queryMap)
                .flatMap(this::processCountryResponse);
    }

    private Observable<List<XoomCountry>> processCountryResponse(XoomCountryResponse xoomCountryResponse) {
        List<XoomCountry> countriesList = new ArrayList<>();
        List<XoomCountry> tempCountries = xoomCountryResponse.getCountries();
        for (Iterator<XoomCountry> xoom = tempCountries.iterator(); xoom.hasNext(); ) {
            XoomCountry xoomCountry = xoom.next();
            if (favCountries.containsKey(xoomCountry.getCode())) {
                countriesList.add(xoomCountry);
                xoom.remove();
            }
        }
        countriesList.addAll(tempCountries);
        memory = countriesList;
        return Observable.just(countriesList).doOnNext(data -> {
            memory = data;
        }).compose(logSource("NETWORK"));
    }

    public Observable<List<XoomCountry>> memory() {
        Observable<List<XoomCountry>> observable = Observable.create(subscriber -> {
            subscriber.onNext(memory);
            subscriber.onCompleted();
        });

        return observable.compose(logSource("MEMORY"));
    }

    // Simple logging to let us know what each source is returning
    Observable.Transformer<List<XoomCountry>, List<XoomCountry>> logSource(final String source) {
        return dataObservable -> dataObservable.doOnNext(data -> {
            if (data == null) {
                System.out.println(source + " does not have any data.");
            } else {
                System.out.println(source + " has the data you are looking for!");
            }
        });
    }

}
