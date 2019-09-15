package com.prod.realtimecurrencycalc.datasource.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.prod.realtimecurrencycalc.utils.ReflectionUtils;

import java.util.Map;

public class CurrenciesApiModel {

    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    private Rates rates;

    public Map<String, Double> getCurrenciesMap() {
        this.ratesMap = ReflectionUtils.getRatesFieldsMap(rates);
        return ratesMap;
    }

    public void setRatesMap(Map<String, Double> ratesMap) {
        this.ratesMap = ratesMap;
    }

    public Map<String, Double> getRatesMap() {
        return ratesMap;
    }

    @Expose(serialize = false, deserialize = false)
    private Map<String, Double> ratesMap;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

}
