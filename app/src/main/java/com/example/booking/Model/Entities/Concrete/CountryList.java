package com.example.booking.Model.Entities.Concrete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class CountryList {
    @SerializedName("country")
    @Expose
    private List<Country> country = null;

    public List<Country> getCountry(int id) {
        CollectionUtils.filter(country, object -> Integer.parseInt(((Country) object).getIlId())==id);
        return country;
    }

    public void setCountry(List<Country> country) {
        this.country = country;
    }
}
