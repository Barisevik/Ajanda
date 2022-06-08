package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PlanActivity extends AppCompatActivity {
    private FloatingActionButton fab;

    private RecyclerView recyclerView;
    public ArrayList<plan> planArrayList;
    planadapter planadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        final ArrayList<String> icerikNamelist = new ArrayList<String>();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlanActivity.this, pkaydetActivity.class);
                intent.putExtra("info", "new");
                startActivity(intent);
            }
        });
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String Url = "content://com.example.planvehatirlatici.Contentprovedir";
        Uri planuri = Uri.parse(Url);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(planuri, null, null, null, null);

        if (cursor != null) {

            while (cursor.moveToNext()) {

                plan plan = new plan(cursor.getString(cursor.getColumnIndexOrThrow(Contentprovedir.BASLIK)));
                planArrayList = new ArrayList<>();
                planArrayList.add(plan);


                planadapter = new planadapter(planArrayList, this);
                recyclerView.setAdapter(planadapter);


            }
        }
    }

}