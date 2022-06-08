package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AnasayfaActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        bottomNavigationView=findViewById(R.id.menu);
        bottomNavigationView.setSelectedItemId(R.id.AnasayfaActivity);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.hatirliyiciActivity:
                        Intent intent = new Intent(AnasayfaActivity.this, hatirliyiciActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.PlanActivity:
                        Intent intent1= new Intent(AnasayfaActivity.this, PlanActivity.class);
                        startActivity(intent1);
                        return  true;
                    case R.id.AnasayfaActivity:
                        Intent intent2 = new Intent(AnasayfaActivity.this, AnasayfaActivity.class);
                        startActivity(intent2);
                }
                return true;

            }
        });
    }



}