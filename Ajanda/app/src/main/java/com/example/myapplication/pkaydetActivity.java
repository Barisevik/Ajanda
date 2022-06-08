package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;

public class pkaydetActivity extends AppCompatActivity {

    private EditText editTextbaslik, editTexticerik, editTexttarih;
    private Button button;
    String firstname;
    Switch Switch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkaydet);
        editTextbaslik=findViewById(R.id.baslikt);
        editTexticerik=findViewById(R.id.icerikk);
        editTexttarih=findViewById(R.id.tariht);
        button=findViewById(R.id.guncelle);
        Switch=findViewById(R.id.switch1);
        Intent intent= getIntent();
        String info=intent.getStringExtra("info");
        if (info.matches("new" )){
            editTextbaslik.setText("");
            editTexticerik.setText("");
            editTexttarih.setText("");
            button.setVisibility(View.VISIBLE);




        }
        else {
           String baslikkId = intent.getStringExtra("PlanId");
            editTextbaslik.setText(baslikkId);
            firstname= baslikkId;
            String position= intent.getStringExtra("old");


            button.setVisibility(View.VISIBLE);
            String basliikk = editTextbaslik.getText().toString();
            String icerikk = editTexticerik.getText().toString();
            String tarihh = editTexttarih.getText().toString();


        }
        editTexttarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar =Calendar.getInstance();
                int yil=calendar.get(Calendar.YEAR);
                int ay=calendar.get(Calendar.MONTH);
                int gun=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog;
                datePickerDialog= new DatePickerDialog(pkaydetActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    }
                },yil, ay, gun);
                datePickerDialog.setTitle("Tarih seçiniz");
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ayarlar", datePickerDialog);
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "İptal", datePickerDialog);
                datePickerDialog.show();



            }
        });




    }


    public void save(View view){
        String basliik=editTextbaslik.getText().toString();
        String icerik=editTexticerik.getText().toString();
        String tarih=editTexttarih.getText().toString();
        ContentValues contentValues= new ContentValues();

        contentValues.put(Contentprovedir.BASLIK, basliik);
        contentValues.put(Contentprovedir.ICERIK,icerik);
        contentValues.put(Contentprovedir.TARIH, tarih);
        getContentResolver().insert(Contentprovedir.CONTENT_URI,contentValues);
        Intent intent = new Intent(pkaydetActivity.this, PlanActivity.class);
        startActivity(intent);

    }

}