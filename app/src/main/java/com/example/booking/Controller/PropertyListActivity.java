package com.example.booking.Controller;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.booking.Model.Business.Adapter.CustomListViewAdapter;
import com.example.booking.Model.Business.Concrete.PropertyManager;
import com.example.booking.Model.DataAccess.Room.Contexts.AppDatabase;
import com.example.booking.Model.Entities.Concrete.Property;
import com.example.booking.R;

import java.util.ArrayList;
public class PropertyListActivity extends AppCompatActivity {
    private ArrayList<Property> properties;
    private ListView listView;
    private CustomListViewAdapter listViewAdapter;
    private PropertyManager propertyManager;
    private SharedPreferences sharedPreferences;

    private void initialize(ArrayList<Property>properties) {

        listView = (ListView) findViewById(R.id.propertyListView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder diyalogOlusturucu =
                        new AlertDialog.Builder(PropertyListActivity.this);
            for(Property property : properties){
                diyalogOlusturucu.setMessage("Yapmak istediğiniz işlemi seçiniz...")
                        .setCancelable(false)
                        .setNeutralButton("Sil", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                propertyManager.Delete(propertyManager.GetById(property.getPropertyId()));
                                refresh();
                            }
                        }).setPositiveButton("Güncelle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToUpdate(property.getPropertyId());
                    }
                }).setNegativeButton("İncele", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
                diyalogOlusturucu.create().show();
                return false;
            }
        });

        listViewAdapter = new CustomListViewAdapter(PropertyListActivity.this,properties);
        listView.setAdapter(listViewAdapter);
        }
    private void fillArrayList(ArrayList<Property>properties){
        int userId = sharedPreferences.getInt("userId",0);
        this.properties = propertyManager.ArrayGetList(userId);
        if(this.properties.size() == 0){
            Toast.makeText(PropertyListActivity.this,"İlan Bulunamadı",Toast.LENGTH_LONG).show();
        }else{
            for(Property property : this.properties){
                properties.add(property);
            }
        }
    }
    private void refresh(){
        Intent intent = new Intent(this, PropertyListActivity.class);
        startActivity(intent);
    }
    private void goToUpdate(int propertyId){
        Intent intent = new Intent(PropertyListActivity.this,UpdatePropertyActivity.class);
        intent.putExtra("propertyId",propertyId);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);

        AppDatabase db = AppDatabase.getAppDatabase(this);
        propertyManager = new PropertyManager(db);
        sharedPreferences= getApplicationContext().getSharedPreferences("MyPref", 0);
        properties = new ArrayList<Property>();
        initialize(properties);
        fillArrayList(properties);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.updateProfil){
            goToUpdateProfil();
        }else if(item.getItemId()==R.id.logout){
            logout();
        }else if(item.getItemId() == R.id.addProperty){
            goToAddProperty();
        } else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
    private void goToUpdateProfil(){
        Intent intent = new Intent(this,UpdateUserActiviy.class);
        startActivity(intent);
    }
    private void logout(){
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login",false);
        editor.putInt("userId",0);
        editor.apply();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void goToAddProperty(){
        Intent intent = new Intent(this,PropertyAddActivity.class);
        startActivity(intent);
    }
}
