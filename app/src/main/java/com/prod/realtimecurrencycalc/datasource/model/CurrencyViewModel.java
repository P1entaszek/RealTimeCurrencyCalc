package com.prod.realtimecurrencycalc.datasource.model;

public class CurrencyViewModel {
    private String currencyFullName;
    private Double currencyValue;
    private String countryCode;
    private String currencyShortcut;

    public CurrencyViewModel(String countryCode, String currencyShortcut, String currencyFullName, Double currencyValue) {
        this.countryCode = countryCode;
        this.currencyShortcut = currencyShortcut;
        this.currencyFullName = currencyFullName;
        this.currencyValue = currencyValue;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

}
