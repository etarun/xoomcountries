package com.redesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class XoomCountryResponse {

    @SerializedName("total_items")
    @Expose
    private Integer totalItems;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;
    @SerializedName("items")
    @Expose
    private List<XoomCountry> countries = null;

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<XoomCountry> getCountries() {
        return countries;
    }

    public void setCountries(List<XoomCountry> countries) {
        this.countries = countries;
    }

}