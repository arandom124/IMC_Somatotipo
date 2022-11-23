package com.arandom.kusreplain.activitys.docenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.arandom.kusreplain.R;
import com.arandom.kusreplain.activitys.MainActivity;
import com.arandom.kusreplain.activitys.estudiante.princiEstudiActivity;
import com.arandom.kusreplain.includes.MyToolbar;
import com.arandom.kusreplain.proveedors.AutenProvedors;

public class princiDocenteActivity extends AppCompatActivity {

    //boton salir
    AutenProvedors mAutenProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princi_docente);
        MyToolbar.show(this,"menu docente",false);
        mAutenProvider = new AutenProvedors();


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
        Intent intent = new Intent(princiDocenteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}