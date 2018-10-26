package com.redesign.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class XoomCountry {

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("residence")
    @Expose
    private Boolean residence;
    @SerializedName("phone_prefix")
    @Expose
    private String phonePrefix;
    @SerializedName("features")
    @Expose
    private List<String> features = null;
    @SerializedName("disbursement_options")
    @Expose
    private List<DisbursementOption> disbursementOptions = null;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;
    @SerializedName("min")
    @Expose
    private Min min;
    @SerializedName("max")
    @Expose
    private Max max;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getResidence() {
        return residence;
    }

    public void setResidence(Boolean residence) {
        this.residence = residence;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(String phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public List<DisbursementOption> getDisbursementOptions() {
        return disbursementOptions;
    }

    public void setDisbursementOptions(List<DisbursementOption> disbursementOptions) {
        this.disbursementOptions = disbursementOptions;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Min getMin() {
        return min;
    }

    public void setMin(Min min) {
        this.min = min;
    }

    public Max getMax() {
        return max;
    }

    public void setMax(Max max) {
        this.max = max;
    }

}