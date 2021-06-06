package com.example.mp;

import android.app.DatePickerDialog;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;


import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

private Button nxt,login;
private  EditText pass,name;
private String Name,Pass;
private SQLiteDatabase db;
private static String TAG="MainActivity";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ColorDrawable cd= new ColorDrawable(Color.parseColor("#9B11C1"));
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(cd);
        actionBar.setTitle("Healthy Life Style app");
        nxt=findViewById(R.id.Signup);
        login=findViewById(R.id.next);
        pass=findViewById(R.id.editTextTextPassword);
        name=findViewById(R.id.editTextTextPersonName);
        db = openOrCreateDatabase("user", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Userinfo(NAME VARCHAR,PASSWORD VARCHAR,AGE VARCHAR,WEIGHT VARCHAR,HEIGHT VARCHAR,GENDER VARCHAR,BMI VARCHAR);");


        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nxtnew();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         sign();
                                     }
                                 }

        );

    }
   void nxtnew(){
        Intent i;
        i=new Intent(MainActivity.this, Signup.class);
       startActivity(i);
    }
void sign(){

        Pass=pass.getText().toString();
        Name=name.getText().toString();
    try {
        Toast.makeText(this, Name, Toast.LENGTH_SHORT).show();

        Cursor c = db.rawQuery("SELECT * FROM Userinfo where NAME='" + Name + "'", null);
        if (c.getCount() > 0) {
               if (c.moveToFirst()) {

                   if (Pass.equals(c.getString(1))) {

                    Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
                       next();
                } else {
                    Toast.makeText(this, "sorry password worng", Toast.LENGTH_SHORT).show();
                }
            }}else {
                Toast.makeText(this, "User not exist ", Toast.LENGTH_SHORT).show();

                nxtnew();
            }
        }catch(Exception e){
            Log.d(TAG, "" + e);
        }
    }
void next(){
    Intent i;
    i=new Intent(MainActivity.this, MainPage.class);
    i.putExtra("Name",Name);
    startActivity(i);
}
}



