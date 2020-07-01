package com.example.booking.Model.Entities.Concrete;

import com.example.booking.Model.Entities.Abstract.IEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City implements IEntity {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
