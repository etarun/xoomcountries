package com.redesign.xoomcountries;

import com.redesign.data.Repository;
import com.redesign.model.XoomCountry;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Empty Presenter created for Future Use
 * 1. Can check Network connections and display Messages accrodingly
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {


    private Repository repository;


    private MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view, Repository repository) {
        this.view = view;
        this.repository = repository;

    }

    @Override
    public void onCreate(Map<String, String> favCountries) {
        view.showProgressBar(true);

        getCountriesList(1, 50, favCountries);
    }

    public void getCountriesList(int page, int pageSize, Map<String, String> favCountries) {
        Observable<List<XoomCountry>> countriesData = repository.getCountriesList(page, pageSize, favCountries);
        countriesData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(countries -> {
                    view.showCountriesList(countries);
                    view.showProgressBar(false);
                }, throwable -> {
                    throwable.printStackTrace();
                    view.showErrorMessage();
                    view.showProgressBar(false);
                });
    }
}
