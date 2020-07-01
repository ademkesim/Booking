package com.example.booking.Model.Entities.Dtos;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import com.example.booking.Model.Entities.Abstract.IDto;

@Entity(tableName = "User")
public class UserForRegisterDto implements IDto {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "userId")
    private int userId;
    @ColumnInfo(name= "firstName")
    private String firstName;
    @ColumnInfo(name= "lastName")
    private String lastName;
    @ColumnInfo(name= "mail")
    private String mail;
    @ColumnInfo(name= "password")
    private String password;
    @ColumnInfo(name= "number")
    private int number;
    @ColumnInfo(name = "rank")
    private int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
