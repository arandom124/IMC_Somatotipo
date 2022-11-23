package com.arandom.kusreplain.activitys.estudiante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.arandom.kusreplain.R;
import com.arandom.kusreplain.activitys.LoginActivity;
import com.arandom.kusreplain.activitys.MainActivity;
import com.arandom.kusreplain.activitys.SelectOpcioAutActivity;
import com.arandom.kusreplain.includes.MyToolbar;
import com.arandom.kusreplain.proveedors.AutenProvedors;

public class princiEstudiActivity extends AppCompatActivity {
    //boton salir
    AutenProvedors mAutenProvider;


    ImageButton mButtonCalcular;
    ImageButton mButtonFrentePanta;
    ImageButton mButtonEncueta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princi_estudi);
        MyToolbar.show(this,"Inicio Calcular IMC ",false);

        mAutenProvider = new AutenProvedors();

        mButtonCalcular = findViewById(R.id.btnCalcular);
        mButtonFrentePanta = findViewById(R.id.btnFrentePanta);
        mButtonEncueta = findViewById(R.id.btnEncuensta);


        mButtonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irCalcular();
            }

            private void irCalcular() {
                Intent intent = new Intent(princiEstudiActivity.this, CalculadoraImcActivity.class);
                startActivity(intent);
            }
        });
        
        mButtonFrentePanta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irFrentePantalla();
            }

            private void irFrentePantalla() {
                Intent intent = new Intent(princiEstudiActivity.this, FrentePantallaActivity.class);
                startActivity(intent);
            }
        });
        mButtonEncueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irEncuesta();
            }

            private void irEncuesta() {
                Intent intent = new Intent(princiEstudiActivity.this, EncuestaActivity.class);
                startActivity(intent);
            }
        });

    }
    //creamos metodo para cerrar sesion
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.estudiante_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
    void logout() {
        mAutenProvider.logout();
        Intent intent = new Intent(princiEstudiActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}