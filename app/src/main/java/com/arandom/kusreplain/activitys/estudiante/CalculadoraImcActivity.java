package com.arandom.kusreplain.activitys.estudiante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arandom.kusreplain.R;
import com.arandom.kusreplain.includes.MyToolbar;
import com.arandom.kusreplain.proveedors.AutenProvedors;

public class CalculadoraImcActivity extends AppCompatActivity {
    AutenProvedors mAutenProvider;

    android.widget.Button mcalculatebmi;
    //Button mcalculatebmi;

    TextView mcurrentheight;
    TextView mcurrentage, mcurrentweight;
    ImageView mincrementage, mincrementweight, mdecrementage, mdecrementweight;
    SeekBar mseekbarforheight;
    RelativeLayout mmale, mfemale;

    int intweight=55;
    int intage=25;
    int currentprogress;
    String mintprogress="0";
    String typeofuser="0";
    String weight2="50";
    String age2="20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_imc);

        MyToolbar.show(this,"Calcular IMC",true);
        mAutenProvider = new AutenProvedors();

        getSupportActionBar().hide();
        mcalculatebmi=findViewById(R.id.calculatebmi);
        mcurrentage=findViewById(R.id.currentage);
        mcurrentweight=findViewById(R.id.currentweight);
        mcurrentheight=findViewById(R.id.currentheight);
        mseekbarforheight=findViewById(R.id.seekbarheight);
        mincrementage=findViewById(R.id.plussage);
        mincrementweight=findViewById(R.id.plussweight);
        mdecrementage=findViewById(R.id.minusage);
        mdecrementweight=findViewById(R.id.minusweight);
        mmale=findViewById(R.id.male);
        mfemale=findViewById(R.id.female);


        mmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.focusmf));
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.blurmf));
                typeofuser="Masculino";
            }
        });
        mfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfemale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.focusmf));
                mmale.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.blurmf));
                typeofuser="Femenino";
            }
        });


        mseekbarforheight.setMax(300);
        mseekbarforheight.setProgress(170);
        mseekbarforheight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                currentprogress=i;
                mintprogress=String.valueOf(currentprogress);
                mcurrentheight.setText(mintprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        mincrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage+1;
                age2=String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });
        mdecrementage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intage=intage-1;
                age2=String.valueOf(intage);
                mcurrentage.setText(age2);
            }
        });



        mincrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight+1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });



        mdecrementweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intweight=intweight-1;
                weight2=String.valueOf(intweight);
                mcurrentweight.setText(weight2);
            }
        });

        mcalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(typeofuser.equals("0")){
                    Toast.makeText(getApplicationContext(), "Seleccione su género primero", Toast.LENGTH_SHORT).show();
                }
                else if (mintprogress.equals("0")){
                    Toast.makeText(getApplicationContext(), "Seleccione su altura primero", Toast.LENGTH_SHORT).show();
                }
                else if (intage == 0 || intage<0){
                    Toast.makeText(getApplicationContext(), "Por favor, introduzca la edad correcta", Toast.LENGTH_SHORT).show();
                }
                else if (intweight == 0 || intweight<0){
                    Toast.makeText(getApplicationContext(), "Por favor, introduzca el peso correcto", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(CalculadoraImcActivity.this, ImcResultadoActivity.class);

                    intent.putExtra("gender", typeofuser);
                    intent.putExtra("Height", mintprogress);
                    intent.putExtra("Weight", weight2);
                    intent.putExtra("Age", age2);


                    startActivity(intent);
                }



            }
        });
    }
}