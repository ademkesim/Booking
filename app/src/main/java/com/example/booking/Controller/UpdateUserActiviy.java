package com.example.booking.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking.Model.Business.Concrete.UserManager;
import com.example.booking.Model.Business.Hashing.HashingHelper;
import com.example.booking.Model.DataAccess.Room.Contexts.AppDatabase;
import com.example.booking.Model.Entities.Concrete.User;
import com.example.booking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateUserActiviy extends AppCompatActivity {
    @BindView(R.id.registerFirstName)
    TextView firstName;
    @BindView(R.id.registerLastName) TextView lastName;
    @BindView(R.id.registerMail) TextView mail;
    @BindView(R.id.registerPassword) TextView password;
    @BindView(R.id.registerNumber) TextView number;
    @BindView(R.id.errorRegister) TextView errorUpdate;
    @BindView(R.id.registerPassword2)  TextView password2;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @OnClick(R.id.btnRegister)
    void update(){
        updateUser();
    }
    @SuppressLint("SetTextI18n")
    @BindView(R.id.sendLogin) TextView sendLogin;
    User user;
    UserManager userManager;
    SharedPreferences sharedPreferences;
    private void updateItem(){
        AppDatabase db = AppDatabase.getAppDatabase(this);
        userManager = new UserManager(db);
        int userId = sharedPreferences.getInt("userId",0);
        user = userManager.GetById(userId);
        btnRegister.setText("Güncelle");
        sendLogin.setText("");
        lastName.setText(user.getLastName());
        firstName.setText(user.getFirstName());
        mail.setText(user.getMail());
        number.setText(user.getNumber());
        password.setText(user.getPassword());
        password2.setText(user.getPassword());
    }

    private User checkItem(){
        user.setMail(mail.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setFirstName(firstName.getText().toString());
        user.setNumber(number.getText().toString());
        String passwordHash = HashingHelper.hashPassword(user.getPassword());
        user.setPasswordHash(passwordHash);
        return user;
    }
    private boolean Control(TextView[] textView) {
        String message = "";
        for (TextView textView1 : textView){
            if (textView1.getText().toString().matches("")) {
                message +="\n"+ textView1.getHint().toString();
            }
        }
        if (!password.getText().toString().equals(password2.getText().toString())){
            message += "\n Parolanız Eşleşmiyor";
        }
        if(message != ""){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    private void updateUser(){
        TextView[] textViews = {mail,lastName,firstName};
        if(Control(textViews)){
            userManager.Update(checkItem());
            goToListView();
        }
    }

    void goToListView(){
        Intent intent = new Intent(this, PropertyListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        sharedPreferences= getApplicationContext().getSharedPreferences("MyPref", 0);
        updateItem();
    }
}
