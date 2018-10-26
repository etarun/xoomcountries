package com.redesign.xoomcountries;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.redesign.R;
import com.redesign.model.XoomCountry;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CountriesRecyclerAdapter extends RecyclerView.Adapter<CountriesRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<XoomCountry> countries;
    private SharedPreferences sharedPreferences;


    public CountriesRecyclerAdapter(Context context,
                                    List<XoomCountry> countries) {
        this.context = context;
        this.countries = countries;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_country_name)
        TextView tvCountryName;
        @BindView(R.id.iv_country_Icon)
        ImageView ivCountryFlag;
        @BindView(R.id.iv_fav_icon)
        ImageView favButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public CountriesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_country, parent, false);
        sharedPreferences = context.getSharedPreferences("xoomCountries", Context.MODE_PRIVATE);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountriesRecyclerAdapter.ViewHolder viewHolder, int position) {

        XoomCountry country = countries.get(position);
        viewHolder.tvCountryName.setText(country.getName());

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String s = sharedPreferences.getString(country.getCode(), "INVALID");
        if (s.equalsIgnoreCase(country.getCode()))
            viewHolder.favButton.setImageDrawable(context.getDrawable(R.drawable.starfilled));
        else
            viewHolder.favButton.setImageDrawable(context.getDrawable(R.drawable.star));


        String flagURL = String.format("https://www.countryflags.io/%s/shiny/64.png", country.getCode());
        Glide.with(context).load(flagURL).placeholder(
                R.drawable.drawable_placeholder).error(
                R.drawable.drawable_placeholder).into(viewHolder.ivCountryFlag);

        viewHolder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = sharedPreferences.getString(country.getCode(), "INVALID");
                if (s.equalsIgnoreCase(country.getCode())) {
                    editor.remove(country.getCode());
                    editor.apply();
                    viewHolder.favButton.setImageDrawable(context.getDrawable(R.drawable.star));
                } else {
                    viewHolder.favButton.setImageDrawable(context.getDrawable(R.drawable.starfilled));
                    editor.putString(country.getCode(), country.getCode());
                    editor.apply();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void clear() {
        int size = getItemCount();
        //photos.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(List<XoomCountry> countries) {
        //int prevSize = getItemCount();
        this.countries.addAll(countries);
        notifyDataSetChanged();
        //notifyItemRangeInserted(prevSize, photos.size());
    }

}
