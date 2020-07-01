package com.example.booking.Controller;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.booking.Model.Business.Concrete.PropertyManager;
import com.example.booking.Model.DataAccess.Room.Contexts.AppDatabase;
import com.example.booking.Model.Entities.Concrete.City;
import com.example.booking.Model.Entities.Concrete.CityList;
import com.example.booking.Model.Entities.Concrete.CountryList;
import com.example.booking.Model.Entities.Concrete.Property;
import com.example.booking.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePropertyActivity extends AppCompatActivity {
    @BindView(R.id.imgProperty) ImageView imgProperty;
    @BindView(R.id.txtTittle) TextView txtTittle;
    @BindView(R.id.spinnerPriceType) Spinner spinnerPriceType;
    @BindView(R.id.spinnerType) Spinner spinnerType;
    @BindView(R.id.txtPrice) TextView txtPrice;
    @BindView(R.id.txtNumberRoom) TextView txtNumberRoom;
    @BindView(R.id.spinnerCity) Spinner spinnerCity;
    @BindView(R.id.spinnerCountry) Spinner spinnerCountry;
    @BindView(R.id.txtNumberFloor) TextView txtNumberFloor;
    @BindView(R.id.txtAddress2) TextView txtAddress2;
    @BindView(R.id.txtOtherFeatures) TextView txtOtherFeatures;

    private int propertyId;
    private PropertyManager propertyManager;
    Property property;
    private Property importProperty(){
        AppDatabase db = AppDatabase.getAppDatabase(this);
        propertyManager = new PropertyManager(db);
        return propertyManager.GetById(getIntent().getIntExtra("propertyId",0));
    }

    @OnClick(R.id.imgProperty)
    void clickImage(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
            return;
        }
        Intent takeImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(takeImage,2);
    }
    private void txtFill(Property property){
        this.property = property;
        txtNumberFloor.setText(String.valueOf(property.getNumberFloor()));
        txtOtherFeatures.setText(property.getOtherFeatures());
        txtTittle.setText(property.getTittle());
        txtAddress2.setText(property.getAddress());
        txtNumberRoom.setText(String.valueOf(property.getNumberRoom()));
        txtPrice.setText(String.valueOf(property.getPrice()));
    }
    ImageView imageView;
    Bitmap bitmapImage;
    SharedPreferences sharedpreferences;
    String[] spinnerArray = new String[82];
    byte[] byteImage;
    HashMap<Integer, String> spinnerData = new HashMap<Integer, String>();
    CityList readCity() {
        CityList cityList = new CityList();
        try {
            //Load File
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.city)));
            StringBuilder jsonBuilder = new StringBuilder();
            for (String line = null; (line = jsonReader.readLine()) != null; ) {
                jsonBuilder.append(line).append("\n");
            }
            Gson gson = new Gson();
            cityList = gson.fromJson(jsonBuilder.toString(),CityList.class);
            return cityList;
        } catch (IOException e) {
            Log.e("jsonFile", "ioerror");
        }
        return null;
    }
    void writeCity(CityList cityList){
        spinnerArray[0] = "Şehir Seçiniz";
        for(int i=0;i<cityList.getCity().size();i++){
            City city1 = cityList.getCity().get(i);
            this.spinnerData.put(i+1,city1.getId());
            this.spinnerArray[i+1] = city1.getName();
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, this.spinnerArray);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerCity.setAdapter(dataAdapter);
    }
    CountryList readCountry(){
        CountryList countryList = new CountryList();
        try {
            //Load File
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.country)));
            StringBuilder jsonBuilder = new StringBuilder();
            for (String line = null; (line = jsonReader.readLine()) != null; ) {
                jsonBuilder.append(line).append("\n");
            }
            Gson gson = new Gson();
            countryList = gson.fromJson(jsonBuilder.toString(),CountryList.class);
            return countryList;
        } catch (IOException e) {
            Log.e("jsonFile", "ioerror");
        }
        return null;
    }
    void writeCountry(CountryList countryList, int id){
        List<String> spinnerData = new ArrayList<>();
        spinnerData.add("İlçe Seçiniz");
        for(int i=0;i<countryList.getCountry(id).size();i++){
            spinnerData.add(countryList.getCountry(id).get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(dataAdapter);
    }
    private void selectedCity(){
        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Şehir Seçiniz")){
                    //İşlem Yapılmayacaktır...
                }else{
                    writeCountry(readCountry(),Integer.parseInt(spinnerData.get(position)));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void controlSpinner(){
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Ev Tipi Seçiniz")){
                    //İşlem Yapılmayacaktır...
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerPriceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Ödeme Tipi Seçiniz")){
                    //İşlem Yapılmayacaktır...
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("İlçe Seçiniz")){
                    //İşlem Yapılmayacaktır...
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @OnClick(R.id.btnAddProperty)
    void updateProperty(){
        AppDatabase db = AppDatabase.getAppDatabase(this);
        PropertyManager propertyManager = new PropertyManager(db);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 50,byteArrayOutputStream);
        byteImage = byteArrayOutputStream.toByteArray();
        Property property = new Property();
        property.setAddress(txtAddress2.getText().toString());
        property.setImage(byteImage);
        property.setNumberFloor(Integer.parseInt(txtNumberFloor.getText().toString()));
        property.setNumberRoom(Integer.parseInt(txtNumberRoom.getText().toString()));
        property.setCityName(spinnerCity.getSelectedItem().toString());
        property.setCountryName(spinnerCountry.getSelectedItem().toString());
        property.setOtherFeatures(txtOtherFeatures.getText().toString());
        property.setPrice(Integer.parseInt(txtPrice.getText().toString()));
        property.setPriceType(spinnerPriceType.getSelectedItem().toString());
        property.setType(spinnerType.getSelectedItem().toString());
        property.setTittle(txtTittle.getText().toString());
        property.setUserId(sharedpreferences.getInt("userId",0));
        property.setPropertyId(getIntent().getIntExtra("propertyId",0));
        TextView[] textViews = {txtTittle,txtPrice,txtOtherFeatures,txtNumberRoom,txtNumberFloor,txtAddress2};
        propertyManager.Update(property);
        Intent intent = new Intent(this,PropertyListActivity.class);
        startActivity(intent);
    }
    void Control(TextView[] textView, PropertyManager propertyManager, Property property) {
        String message = "";
        for (TextView textView1 : textView){
            if (textView1.getText().toString().matches("")) {
                message +="\n"+ textView1.getHint().toString();
            }
        }
        if(message != ""){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }else{
            propertyManager.Update(property);
            Intent intent = new Intent(this,PropertyListActivity.class);
            startActivity(intent);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && data != null && resultCode== RESULT_OK){
            Uri dataImage = data.getData();
            try {
                ImageDecoder.Source src = ImageDecoder.createSource(this.getContentResolver(), dataImage);
                bitmapImage = ImageDecoder.decodeBitmap(src);
                bitmapImage = Bitmap.createScaledBitmap(bitmapImage, 250, 250, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmapImage);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent goGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(goGallery, 2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        ButterKnife.bind(this);
        sharedpreferences= getApplicationContext().getSharedPreferences("MyPref", 0);
        txtFill(importProperty());
        selectedCity();
        controlSpinner();
        writeCity(readCity());
    }
}
