package com.arandom.kusreplain.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.arandom.kusreplain.R;
import com.arandom.kusreplain.activitys.estudiante.princiEstudiActivity;
import com.arandom.kusreplain.activitys.docenter.princiDocenteActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button mButtonSoyEstudiante;
    Button mButtonSoyDocente;

    //este es el indentificador para saber tipo de ususarios
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //nicializamos shared verificar que tipodeusuario es
        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        final SharedPreferences.Editor editor = mPref.edit();

        mButtonSoyEstudiante = findViewById(R.id.btnSoyEstudiante);
        mButtonSoyDocente =findViewById(R.id.btnSoyDocente);

        mButtonSoyEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user", "estudiante");
                editor.apply();
                goToselectAuth();
            }
        });

        mButtonSoyDocente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user", "docente");
                editor.apply();
                goToselectAuth();
            }
        });
    }

    //vamos a sobreescribir ciclo de vida de android


    @Override
    protected void onStart() {
        super.onStart();
        //verificamos si hay una sesion activa o iniciada
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            String user = mPref.getString("user","");
            if (user.equals("estudiante")) {
                Intent intent = new Intent(MainActivity.this, princiEstudiActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(MainActivity.this, princiDocenteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    }

    private void goToselectAuth() {
        Intent intent = new Intent(MainActivity.this, SelectOpcioAutActivity.class);
        startActivity(intent);
    }
}