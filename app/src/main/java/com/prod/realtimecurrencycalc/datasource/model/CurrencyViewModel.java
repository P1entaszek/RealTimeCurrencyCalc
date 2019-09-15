package com.prod.realtimecurrencycalc.datasource.model;

public class CurrencyViewModel {
    public CurrencyViewModel(String imageURl, String currencyShortcut, String currencyFullName, Double currencyValue) {
        this.imageURl = imageURl;
        this.currencyShortcut = currencyShortcut;
        this.currencyFullName = currencyFullName;
        this.currencyValue = currencyValue;
    }

    private String imageURl;
    private String currencyShortcut;

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public String getCurrencyShortcut() {
        return currencyShortcut;
    }

    public void setCurrencyShortcut(String currencyShortcut) {
        this.currencyShortcut = currencyShortcut;
    }

    public String getCurrencyFullName() {
        return currencyFullName;
    }

    public void setCurrencyFullName(String currencyFullName) {
        this.currencyFullName = currencyFullName;
    }

    public Double getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    private String currencyFullName;
    private Double currencyValue;

}
