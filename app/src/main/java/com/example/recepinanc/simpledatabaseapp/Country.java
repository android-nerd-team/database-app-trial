package com.example.recepinanc.simpledatabaseapp;

import java.io.Serializable;

/**
 * Created by Recepinanc on 18.09.2015.
 */
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String countryName;
    private String countryCode;

    public Country() {
        super();
    }

    public Country(String countryName,String countryCode) {
        super();
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
