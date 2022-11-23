package com.arandom.kusreplain.activitys.docenter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arandom.kusreplain.R;
import com.arandom.kusreplain.includes.MyToolbar;
import com.arandom.kusreplain.models.Docenter;
import com.arandom.kusreplain.proveedors.AutenProvedors;
import com.arandom.kusreplain.proveedors.DocenteProvedors;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistarDocenteActivity extends AppCompatActivity {

    //llamamos los autenticaciones
    AutenProvedors mAutnProvider;
    DocenteProvedors mDocenteProvedor;

    //instanciamos lostxt de registros
    Button mButtonRegister;
    TextInputEditText mTxtInputCorreo;
    TextInputEditText mTxtInputNombre;
    TextInputEditText mInputNumTarProfecio;
    TextInputEditText mInputNumexpediente;
    TextInputEditText mTextInputPassword;

    //AlertDialog mDialog; no hace falta esto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_docente);

        MyToolbar.show(this,"Crear cuenta Docente",true);

        //inicializamos los provers
        mAutnProvider = new AutenProvedors();
        mDocenteProvedor = new DocenteProvedors();

        mButtonRegister = findViewById(R.id.btnRegister);
        mTxtInputCorreo = findViewById(R.id.txtInputCorreo);
        mTxtInputNombre = findViewById(R.id.txtInputNombre);
        mInputNumTarProfecio = findViewById(R.id.txtInputNumTarProfeci);
        mInputNumexpediente = findViewById(R.id.txtInputNumexpediente);
        mTextInputPassword = findViewById(R.id.textInputPassword);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickRegistar();
            }
        });
    }

    private void clickRegistar() {
        //campso de labase de datos
        final String nombre = mTxtInputNombre.getText().toString();
        final String correo = mTxtInputCorreo.getText().toString();
        final String numTargeProfe = mInputNumTarProfecio.getText().toString();
        final String numExpediente = mInputNumexpediente.getText().toString();
        final String password = mTextInputPassword.getText().toString();

        if (!nombre.isEmpty() && !correo.isEmpty() && !password.isEmpty() && !numTargeProfe.isEmpty() && !numExpediente.isEmpty()) {
            if (password.length() >= 6){
                //mDialog.show(); have referencia los puntos al caragar
                registar(nombre,correo,password,numTargeProfe,numExpediente);

            }
            else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    void registar(final String nombre,final String correo, String password,final String numTargeProfe,final String numExpediente) {
        mAutnProvider.registar(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Docenter docenter = new Docenter(id,nombre,correo,numTargeProfe,numExpediente);
                    create(docenter);
                }
                else {
                    Toast.makeText(RegistarDocenteActivity.this, "No se pudo registrar Docente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //verificar crearte  ojo____________________mejorandocodigo video 08:20__________________________________________
    void create(Docenter docenter) {
        mDocenteProvedor.create(docenter).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //Toast.makeText(RegistarDocenteActivity.this, "Se registro exitosamente el docente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistarDocenteActivity.this,princiDocenteActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegistarDocenteActivity.this, "No se pudo crear el docente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}