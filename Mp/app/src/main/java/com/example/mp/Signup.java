package com.example.mp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Signup extends AppCompatActivity implements  CounterHandler.CounterListener {
    double weight=0.0;
    double Height=0.0;
    int Age;
    double BMI;
    String mi;
    EditText password,name1;
    String Gender,name;
    String pass;
    private Button save;
    private static final String TAG = "Signup";

    private TextView t1,ht;
    private Button bt1,bt2,bt3,bt4;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    int year1 = Calendar.getInstance().get(Calendar.YEAR);
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    SQLiteDatabase db;
    private TextView age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        name1=findViewById(R.id.name);
        password=findViewById(R.id.pass);
        t1=findViewById(R.id.w1);
        ht=findViewById(R.id.ht);
        radioGroup=findViewById(R.id.r1);
        save=findViewById(R.id.save);

        db = openOrCreateDatabase("user", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Userinfo(NAME VARCHAR,PASSWORD VARCHAR,AGE VARCHAR,WEIGHT VARCHAR,HEIGHT VARCHAR,GENDER VARCHAR,BMI VARCHAR);");







        mDisplayDate.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Signup.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            Age=year1-year;
            Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

            String date = month + "/" + day + "/" + year;
            mDisplayDate.setText(""+Age);

        };
        bt1 = findViewById(R.id.bt1);
        bt2=findViewById(R.id.bt2);
        new CounterHandler.Builder()
                .incrementalView(bt1)
                .decrementalView(bt2)
                .minRange(0) // cant go any less than -50
                .maxRange(500) // cant go any further than 50
                .isCycle(true) // 49,50,-50,-49 and so on
                .counterDelay(200) // speed of counter
                .counterStep(1)  // steps e.g. 0,2,4,6...
                .listener(this) // to listen counter results and show them in app
                .build();

        bt3=findViewById(R.id.bt3);

        bt4=findViewById(R.id.bt4);
        new CounterHandler.Builder()
                .incrementalView(bt3)
                .decrementalView(bt4)
                .minRange(0) // cant go any less than -50
                .maxRange(500) // cant go any further than 50
                .isCycle(true) // 49,50,-50,-49 and so on
                .counterDelay(200) // speed of counter
                .counterStep(1)  // steps e.g. 0,2,4,6...
                .listener(this) // to listen counter results and show them in app
                .build();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDB();
            }
        });

    }
    void Bmi(){
        double hel;
        Height=Double.parseDouble(ht.getText().toString());
        weight=Double.parseDouble(t1.getText().toString());
        hel=Height/100;


        BMI = weight/(hel*hel);
        DecimalFormat df = new DecimalFormat("0.00");
        mi=df.format(BMI);
    }
    void saveDB(){
        Bmi();
        pass=password.getText().toString();
        name=name1.getText().toString();
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(selectedId);
        Gender=radioButton.getText().toString();
        try {

            db.execSQL("INSERT INTO Userinfo VALUES('" + name +
                    "','" + pass +
                    "','" + Age +
                    "','" + weight +"','"+Height+ "','" + Gender + "','" + mi + "');");
            showMessage("Success", "Record added");
          open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void showMessage(String success, String record_added) {
        Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
    }
void open(){
    Intent i=new Intent(Signup.this,MainPage.class);
    i.putExtra("Name",name);

    startActivity(i);
}

    @Override
    public void onIncrement(View view, long number) {
        if (view.getId()==bt1.getId()){
t1.setText(""+number);
}else{

            ht.setText(""+number);
        }

    }

    @Override
    public void onDecrement(View view, long number) {

        if (view.getId()==bt2.getId()){
            t1.setText(""+number);
            }
        else {

            ht.setText(""+number);
        }
    }
    }

