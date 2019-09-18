package com.prod.realtimecurrencycalc.datasource.model;

public class CurrencyViewModel {
    public CurrencyViewModel(int imageURl, String currencyShortcut, String currencyFullName, Double currencyValue) {
        this.imageURl = imageURl;
        this.currencyShortcut = currencyShortcut;
        this.currencyFullName = currencyFullName;
        this.currencyValue = currencyValue;
    }

    private int imageURl;
    private String currencyShortcut;

    public int getImageURl() {
        return imageURl;
    }

    public void setImageURl(int imageURl) {
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
