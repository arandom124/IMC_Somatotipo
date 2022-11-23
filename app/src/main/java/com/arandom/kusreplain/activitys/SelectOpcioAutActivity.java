package com.arandom.kusreplain.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.arandom.kusreplain.R;
import com.arandom.kusreplain.activitys.estudiante.RegistrarEstudianteActivity;
import com.arandom.kusreplain.activitys.docenter.RegistarDocenteActivity;
import com.arandom.kusreplain.includes.MyToolbar;

public class SelectOpcioAutActivity extends AppCompatActivity {

    Button mButtonGotoLogin;
    Button mButtonGotoRegister;
    //este es el indentificador para saber tipo de ususarios
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_opcio_aut);

        MyToolbar.show(this,"Selecciona una Opcion",true);

        mButtonGotoLogin = findViewById(R.id.btnGoToLogin);
        mButtonGotoRegister = findViewById(R.id.btnGoToRegister);



        mButtonGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
        mButtonGotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegistar();
            }
        });

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
    }
    //creamos metodologin
    private void goToLogin() {
        Intent intent = new Intent(SelectOpcioAutActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void goToRegistar() {
        String typeUser = mPref.getString("user","");
        if (typeUser.equals("estudiante")) {
            Intent intent = new Intent(SelectOpcioAutActivity.this, RegistrarEstudianteActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(SelectOpcioAutActivity.this, RegistarDocenteActivity.class);
            startActivity(intent);
        }
    }
}//mejorando codiog parte 2 error no muestra activity driver