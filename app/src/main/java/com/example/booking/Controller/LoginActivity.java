package com.example.booking.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booking.Model.Business.Concrete.AuthManager;
import com.example.booking.Model.Business.Utilities.Result.IDataResult;
import com.example.booking.Model.DataAccess.Room.Contexts.AppDatabase;
import com.example.booking.Model.Entities.Concrete.User;
import com.example.booking.Model.Entities.Dtos.UserForLoginDto;
import com.example.booking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    private static int control = 0;
    @BindView(R.id.password) TextView password;
    @BindView(R.id.mail) TextView mail;
    @BindView(R.id.errorLogin) TextView errorLogin;

    @OnClick(R.id.btnLogin)
    void ClickButton() {
        AppDatabase db = AppDatabase.getAppDatabase(this);
        AuthManager authManager = new AuthManager(db);
        UserForLoginDto userForLoginDto = new UserForLoginDto();
        userForLoginDto.setMail(mail.getText().toString());
        userForLoginDto.setPassword(password.getText().toString());
        TextView[] textViews = {mail,password};
        Control(textViews,authManager,userForLoginDto);
    }

    void Control(TextView[] textView,AuthManager authManager, UserForLoginDto userForLoginDto) {
        String message = "";
        for (TextView textView1 : textView){
            if (textView1.getText().toString().matches("")) {
                message +="\n"+ textView1.getHint().toString();
            }
        }
        if(message != ""){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }else{

            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedpreferences.edit();
            IDataResult<User> login= authManager.Login(userForLoginDto);
            if(login.getSuccess()){
                goHomeScreen();
                editor.putBoolean("login",true);
                editor.putInt("userId",login.getData().getUserId());
                editor.apply();
            }else{
                errorLogin.setText(login.getMessage());
            }
        }
    }

    @OnClick(R.id.sendRegister)
    void ClickTextView(){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    void goHomeScreen(){
        Intent intent = new Intent(this, PropertyListActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedpreferences= getApplicationContext().getSharedPreferences("MyPref", 0);
        ButterKnife.bind(this);
        if (sharedpreferences.getBoolean("login",false)){
            goHomeScreen();
        }
    }
}
