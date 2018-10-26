package com.redesign.xoomcountries.ui;


import com.redesign.data.Repository;
import com.redesign.model.XoomCountry;
import com.redesign.xoomcountries.MainActivityContract;
import com.redesign.xoomcountries.MainActivityPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Presenter
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainActivityContract.View mockView;

    @Mock
    private Repository mockDataRepository;

    @Mock
    private List<XoomCountry> countriesData;

    @Mock
    private XoomCountry country;

    private MainActivityContract.Presenter presenter;

    @Before
    public void setup() {
        presenter = new MainActivityPresenter(mockView, mockDataRepository);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void getRestaurants_testCallback() {
        int start = 0;
        int end = 50;
        Map<String, String> favCountires = new HashMap<>();
        countriesData = new ArrayList<>();
        when(mockDataRepository.getCountriesList(eq(start), eq(end), eq(favCountires)))
                .thenReturn(Observable.just(countriesData));

        presenter.getCountriesList(eq(start), eq(end), eq(favCountires));

        verify(mockView).showProgressBar(true);
        verify(mockDataRepository).getCountriesList(eq(start), eq(end), eq(favCountires));

        verify(mockView).showCountriesList(countriesData);
        verify(mockView, times(1)).showProgressBar(false);
    }


}
