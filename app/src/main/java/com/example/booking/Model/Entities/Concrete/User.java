package com.example.booking.Model.Entities.Concrete;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.booking.Model.Entities.Abstract.IEntity;

@Entity(tableName = "User")
public class User implements IEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "userId")
    private int userId;
    @ColumnInfo(name= "firstName")
    private String firstName;
    @ColumnInfo(name= "lastName")
    private String lastName;
    @ColumnInfo(name= "mail")
    private String mail;
    private String password;
    @ColumnInfo(name= "passwordHash")
    private String passwordHash;
    @ColumnInfo(name= "number")
    private String number;
    @ColumnInfo(name = "rank")
    private int rank;

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
