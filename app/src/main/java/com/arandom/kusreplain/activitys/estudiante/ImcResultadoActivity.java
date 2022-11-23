package com.arandom.kusreplain.activitys.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arandom.kusreplain.R;
import com.arandom.kusreplain.includes.MyToolbar;
import com.arandom.kusreplain.proveedors.AutenProvedors;

public class ImcResultadoActivity extends AppCompatActivity {
    AutenProvedors mAutenProvider;

    android.widget.Button mrecalculatebmi;

    TextView mbmidisplay, mbmicategory,mgender;
    Intent intent;
    ImageView mimageview;
    String mbmi;
    float intbmi;

    String height;
    String weight;
    float intheight,intweight;
    RelativeLayout mbackground;
    RelativeLayout mtextcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc_resultado);

        mAutenProvider = new AutenProvedors();

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Result");
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);


        intent=getIntent();
        mbmidisplay=findViewById(R.id.bmiresult);
        mbmicategory=findViewById(R.id.bmicategory);
        mgender=findViewById(R.id.genderdisplay);
        mbackground=findViewById(R.id.contentlayout);
        mimageview=findViewById(R.id.imageview);
        mrecalculatebmi=findViewById(R.id.recalculatebmi);


        height=intent.getStringExtra("Height");
        weight=intent.getStringExtra("Weight");
        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);

        intheight=intheight/100;
        intbmi=intweight/(intheight*intheight);
        mbmi=Float.toString(intbmi);

        if(intbmi<16){
            mbmicategory.setText("Delgadez severa");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.cross);
        }
        else if(intbmi<16.9 && intbmi>16){
            mbmicategory.setText("Delgadez moderada");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi<18.4 && intbmi>17){
            mbmicategory.setText("Delgadez leve");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else if(intbmi<25 && intbmi>18.4){
            mbmicategory.setText("Normal");
            mbackground.setBackground(getDrawable(R.drawable.resultgreen));
            mimageview.setImageResource(R.drawable.tick);
        }
        else if(intbmi<29.4 && intbmi>25){
            mbmicategory.setText("Sobrepeso");
            mbackground.setBackground(getDrawable(R.drawable.resultred));
            mimageview.setImageResource(R.drawable.warning);
        }
        else{
            mbmicategory.setText("Obeso Clase 1");
            mbackground.setBackground(getDrawable(R.drawable.resultgreen));
            mimageview.setImageResource(R.drawable.tick);
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);

        getSupportActionBar().hide();
        mrecalculatebmi=findViewById(R.id.recalculatebmi);
        mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ImcResultadoActivity.this, CalculadoraImcActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}