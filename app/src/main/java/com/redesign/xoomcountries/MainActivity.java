package com.redesign.xoomcountries;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.redesign.BaseActivity;
import com.redesign.R;
import com.redesign.model.XoomCountry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * App Main activity displays list of Countries with Flags
 */
public class MainActivity extends BaseActivity implements MainActivityContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    MainActivityContract.Presenter mainActivityPresenter;

    private MainActivityComponent mainActivityComponent;
    private CountriesRecyclerAdapter recyclerAdapter;
    private List<XoomCountry> countriesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDaggerAppComponent();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher_foreground);

        initData();
        initViews();

    }

    private void initViews() {
        recyclerAdapter = new CountriesRecyclerAdapter(this, countriesList);
        recyclerView.setAdapter(recyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
        countriesList = new ArrayList<>();

        SharedPreferences sharedPreferences = this.getSharedPreferences("xoomCountries", Context.MODE_PRIVATE);
        Map<String, String> favCountries = (Map<String, String>) sharedPreferences.getAll();
        mainActivityPresenter.onCreate(favCountries);
    }

    @Override
    public void showProgressBar(boolean isDisplayProgressBar) {
        if (isDisplayProgressBar)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showCountriesList(List<XoomCountry> countriesList) {
        recyclerAdapter.addAll(countriesList);
    }

    @Override
    public void showErrorMessage() {
        System.out.println("APP Crahsed");
    }

    private void initDaggerAppComponent() {
        super.initDaggerComponent();
        if (mainActivityComponent == null) {
            mainActivityComponent = DaggerMainActivityComponent.builder()
                    .appComponent(appComponent)
                    .mainActivityModule(new MainActivityModule(this))
                    .build();
            mainActivityComponent.inject(this);
        }
    }
}
