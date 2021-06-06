package com.example.mp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity2 extends AppCompatActivity {
    String [] Yoga = {"ArmBalance.html","Boat.html","Bridge_BandhaSarvangasana.html ",
            "ChairUtkatasana.html","ChildPoseBalasana.html ","CorpseSavasana.html",
            "CrowBakasana.html","DownwardDog.html","EasyPoseSukhasana.html ","FourLimbedStaff.html",
            "gasreleasingpose.html","KingDancerNatarajasana.html","pranayama.html",
            "sunsalution.html","trianglepose.html","TreeVrksasana.html","upwardplank.html",
            "UtthitaTrikonasana.html"
            ,"warrior1_virabhadrasana.html","Warrior2_virabhadrasana.html"};
    String[] TRADITIONAL={"Asthma","Anemia","BLOOD_PRESSURE","Chickenpox","Cough","dehydration","Diabetes",
            "Dysentry","eyeitch","fever","Headache","kidneyinfection","kidneystones","Liver Infection","Malaria","Skin Itching"
            ,"Stomach Upset","thyroid","toothdecay"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i=getIntent();
        int a=i.getIntExtra("Pos",1);
        String choice=i.getStringExtra("choice");
        if(choice.equals("TM")
        ){
            WebView web = (WebView) findViewById(R.id.wv);

            web.loadUrl("file:///android_asset/" + TRADITIONAL[a]+".html");
        }

    else if(choice.equals("Y")) {
            WebView web = (WebView) findViewById(R.id.wv);

            web.loadUrl("file:///android_asset/" + Yoga[a]);
        }
    else{
        String c=i.getStringExtra("Names");
            WebView web = (WebView) findViewById(R.id.wv);

            web.loadUrl("file:///android_asset/" +c+".html" );
        }


}}