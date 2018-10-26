package com.redesign.xoomcountries;

import com.redesign.model.XoomCountry;

import java.util.List;
import java.util.Map;

/**
 * Empty Contract, can be used for interacting between Views and Presenter
 */

public class MainActivityContract {

    public interface View {

        void showCountriesList(List<XoomCountry> countriesList);

        void showProgressBar(boolean isDisplayProgressBar);

        void showErrorMessage();
    }

    public interface Presenter {

        void onCreate(Map<String, String> favCountries);

        void getCountriesList(int page, int pageSize, Map<String, String> favCountries);
    }
}
