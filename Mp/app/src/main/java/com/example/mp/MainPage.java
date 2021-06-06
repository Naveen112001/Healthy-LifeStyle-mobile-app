package com.example.mp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainPage extends AppCompatActivity {
ImageButton btn1,btn2,btn3;
    String Name;
    TextView txt;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        btn1=findViewById(R.id.Traditional_medicine);
        btn2=findViewById(R.id.yoga);
        txt=findViewById(R.id.txt);
        btn3=findViewById(R.id.sugesstions);
        Intent i=getIntent();
        Name=i.getStringExtra("Name");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlist("Traditional_medicine");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlist("Yoga");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlist("Suggestions");
            }
        });
        db = openOrCreateDatabase("user", Context.MODE_PRIVATE, null);

    }
    void openlist(String s){
        String Gender;
        String BMI;
        String Age;
        Cursor c = db.rawQuery("SELECT * FROM Userinfo where NAME='" + Name + "'", null);
      if (c.getCount()>0){
          if(c.moveToFirst()){
               Gender=c.getString(5);
              BMI=c.getString(6);
               Age=c.getString(2);
              Intent i;
              i=new Intent(MainPage.this,second.class);
              i.putExtra("choice",s);
              i.putExtra("Name",Name);
              i.putExtra("Age",Age);
              i.putExtra("BMI",BMI);
              i.putExtra("Gender",Gender);
              startActivity(i);
          }
      }

    }
}