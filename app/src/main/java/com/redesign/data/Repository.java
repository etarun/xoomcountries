package com.redesign.data;

import com.redesign.model.XoomCountry;

import java.util.List;
import java.util.Map;

import rx.Observable;


public class Repository {

    private DataSource remoteDataSource;

    public Repository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public Observable<List<XoomCountry>> getCountriesList(int page, int PageSize, Map<String, String> favCountries) {

        Observable<List<XoomCountry>> observable;

        observable = remoteDataSource.getXoomCountriesList(page, PageSize, favCountries);

        return observable;
    }

}
