package com.example.booking.Model.Entities.Concrete;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.booking.Model.Entities.Abstract.IEntity;

@Entity(tableName = "Property")
public class Property implements IEntity {

    @NonNull
    @ColumnInfo(name= "propertyId")
    @PrimaryKey(autoGenerate = true)
    private int propertyId;
    @NonNull
    @ColumnInfo(name = "userId")
    private int userId;
    @NonNull
    @ColumnInfo(name = "tittle")
    private String tittle;
    @NonNull
    @ColumnInfo(name = "type")
    private String type;
    @NonNull
    @ColumnInfo(name = "cityName")
    private String cityName;
    @NonNull
    @ColumnInfo(name = "countryName")
    private String countryName;
    @NonNull
    @ColumnInfo(name = "address")
    private String address;
    @NonNull
    @ColumnInfo(name = "priceType")
    private String priceType;
    @NonNull
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;
    @NonNull
    @ColumnInfo(name = "numberRoom")
    private int numberRoom;
    @NonNull
    @ColumnInfo(name = "numberFloor")
    private int numberFloor;
    @ColumnInfo(name = "otherFeatures")
    private String otherFeatures;

    @NonNull
    public int getUserId() {
        return userId;
    }

    public void setUserId(@NonNull int userId) {
        this.userId = userId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getCityName() {
        return cityName;
    }

    public void setCityName(@NonNull String cityName) {
        this.cityName = cityName;
    }

    @NonNull
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(@NonNull String countryName) {
        this.countryName = countryName;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(@NonNull String priceType) {
        this.priceType = priceType;
    }

    public int getPrice() {
        return price;
    }

    @NonNull
    public String getTittle() {
        return tittle;
    }

    public void setTittle(@NonNull String tittle) {
        this.tittle = tittle;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(int numberRoom) {
        this.numberRoom = numberRoom;
    }

    public int getNumberFloor() {
        return numberFloor;
    }

    public void setNumberFloor(int numberFloor) {
        this.numberFloor = numberFloor;
    }

    public String getOtherFeatures() {
        return otherFeatures;
    }

    public void setOtherFeatures(String otherFeatures) {
        this.otherFeatures = otherFeatures;
    }
}
