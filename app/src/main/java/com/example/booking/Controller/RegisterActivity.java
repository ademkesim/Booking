package com.example.booking.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking.Model.Business.Concrete.AuthManager;
import com.example.booking.Model.DataAccess.Room.Contexts.AppDatabase;
import com.example.booking.Model.Entities.Concrete.User;
import com.example.booking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.registerFirstName) TextView firstName;
    @BindView(R.id.registerLastName) TextView lastName;
    @BindView(R.id.registerMail) TextView mail;
    @BindView(R.id.registerPassword) TextView password;
    @BindView(R.id.registerNumber) TextView number;
    @BindView(R.id.errorRegister) TextView errorRegister;
    @BindView(R.id.registerPassword2)  TextView password2;
    @OnClick(R.id.btnRegister)
    void clickButton(){
        AppDatabase db = AppDatabase.getAppDatabase(this);
        AuthManager authManager = new AuthManager(db);
        User user = new User();
        user.setFirstName(firstName.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setMail(mail.getText().toString());
        user.setPassword(password.getText().toString());
        user.setNumber(number.getText().toString());
        TextView[] textViews = {firstName,lastName,number,mail,password,password2};
        Control(textViews,authManager,user);
    }
    Boolean PasswordControl(String psw1,String psw2){
        return psw1.equals(psw2);
    }
    void Control(TextView[] textView,AuthManager authManager, User user) {
        String message = "";
        for (TextView textView1 : textView){
            if (textView1.getText().toString().matches("")) {
                message +="\n"+ textView1.getHint().toString();
            }
        }
        if (!PasswordControl(password.getText().toString(),password2.getText().toString())){
            message += "\n Parolanız Eşleşmiyor";
        }
        if(message != ""){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }else{
            authManager.Register(user);
            clickTextView();
        }
    }
    @OnClick(R.id.sendLogin)
    void clickTextView(){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }
}
