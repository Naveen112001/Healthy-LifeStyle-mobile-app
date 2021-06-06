package com.example.mp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class second extends AppCompatActivity {
    SearchView searchView;
    SQLiteDatabase Db;
String Name;
    public String q1="";
    public int pos=-1;

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    String[] TRADITIONAL={"Asthma","Anemia","BLOOD_PRESSURE","Chickenpox","Cough","Dehydration","Diabetes",
            "Dysentry","Eyeitch","fever","Headache","kidneyinfection","kidneystones","Liver Infection","Malaria","Skin Itching"
            ,"Stomach Upset","Thyroid","Toothdecay"};
    String[] Yoga = {"Arm Balance","Boat pose","Bridge pose ",
            "Chair pose","Child pose ","Corpse pose",
            "Crow pose","Downward Dog pose","Easy Pose ","Four Limbed Staff","gas releasing pose","King Dancer","pranayama",
            "Sun Salution","Triangle pose","Tree pose","upward plank pose","Utthita Trikonasana"
            ,"Warrior 1 pose","Warrior 2 pose"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        Intent intent = getIntent();
        String cho=intent.getStringExtra("choice");
        if(cho.equals("Yoga")) {
            list = new ArrayList<>();
            for(int a=0;a<Yoga.length;a++){
                list.add(Yoga[a]);
            }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                    for(int k=0;k<Yoga.length;k++) {
                        if (query.equalsIgnoreCase(list.get(k))) {
                            pos=k;
                        }
                    }
                }else{

                    Toast.makeText(second.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                if(pos==-1) {
                    Toast.makeText(second.this, Yoga[position], Toast.LENGTH_SHORT).show();
                    open_activity(position, "Y");
                }
                else {
                    Toast.makeText(second.this, Yoga[pos], Toast.LENGTH_SHORT).show();
                    open_activity(pos, "Y");
                }
            }
        });
    }
        else if(cho.equals("Traditional_medicine")){
            list = new ArrayList<>();
            for(int a=0;a<TRADITIONAL.length;a++){
                list.add(TRADITIONAL[a]);
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    if(list.contains(query)){
                        adapter.getFilter().filter(query);
                        for(int k=0;k<TRADITIONAL.length;k++){
                        if (query.equalsIgnoreCase(TRADITIONAL[k])){
                            pos=k;
                        }}
                    }else{

                        Toast.makeText(second.this, "No Match found",Toast.LENGTH_LONG).show();
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    //    adapter.getFilter().filter(newText);
                    return false;
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
if(pos==-1){
                    Toast.makeText(second.this, TRADITIONAL[position], Toast.LENGTH_SHORT).show();
                    open_activity(position,"TM");

                }
                else{
    Toast.makeText(second.this, TRADITIONAL[pos], Toast.LENGTH_SHORT).show();
    open_activity(pos,"TM");
                }
                }

            });
        }
        else {
           String age=intent.getStringExtra("Age");
           String bmi=intent.getStringExtra("BMI");
           String Gender=intent.getStringExtra("Gender");
            float a=Float.parseFloat(age);
            float b=Float.parseFloat(bmi);


            list = new ArrayList<>();

            if(Gender.equals("Male")){
                if(a>=25){
                list.add("eyeitch");
                list.add("thyroid");}

            }
            else{
                list.add("kidneystones");
                if(a>=25&&a<=40){
                    list.add("Pregnant Care");
                }
            }
            if(a>=25)
            {
                list.add("Stress Relief");
                list.add("Diabetes");
                list.add("BP Maintain");
                if(a>=30){
                    list.add("Bone Density");

                }
            }
            else{
                list.add("dehydration");
                list.add("Cough");
                list.add("Brain Growth");
            }
            if(b<16){
                list.add("Gain weight");
            }
            else if(b>29){
                list.add("WEIGHT LOSS");
            }
            list.add("pranayama");

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    if(list.contains(query)){
                        adapter.getFilter().filter(query);
                       q1=query;
                    }else{

                        Toast.makeText(second.this, "No Match found",Toast.LENGTH_LONG).show();
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    //    adapter.getFilter().filter(newText);
                    return false;
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {


                    if(q1.equals("")){
                        Toast.makeText(second.this, list.get(position), Toast.LENGTH_SHORT).show();
                    opens(list.get(position),"Suggestions");}
                    else{
                        Toast.makeText(second.this, q1, Toast.LENGTH_SHORT).show();
                        opens(q1,"Suggestions");
                    }

                }
            });
        }
    }
    private void open_activity(int position,String st) {
        Intent I=new Intent(this,MainActivity2.class);
        I.putExtra("Pos",position);
        I.putExtra("choice",st);

        startActivity(I);
    }
    void opens(String a,String st){
        Intent I=new Intent(this,MainActivity2.class);
        I.putExtra("choice",st);
        I.putExtra("Names",a);
        startActivity(I);

    }
}