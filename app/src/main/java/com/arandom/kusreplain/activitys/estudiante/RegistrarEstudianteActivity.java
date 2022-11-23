package com.arandom.kusreplain.activitys.estudiante;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arandom.kusreplain.R;
import com.arandom.kusreplain.includes.MyToolbar;
import com.arandom.kusreplain.models.Estudiante;
import com.arandom.kusreplain.proveedors.AutenProvedors;
import com.arandom.kusreplain.proveedors.EstudianteProvedors;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrarEstudianteActivity extends AppCompatActivity {
    //esta es la logica de para guarad datos en bd
    //llamamos los autenticaciones
    AutenProvedors mAutnProvider;
    EstudianteProvedors mEstudianteProvedor;

    //instanciamos lostxt de registros
    Button mButtonRegister;
    TextInputEditText mTxtInputCorreo;
    TextInputEditText mTxtInputNombre;
    TextInputEditText mTextInputPassword;

    //AlertDialog mDialog; no hace falta esto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_estudiante);

        MyToolbar.show(this,"Crear cuenta Estudiante",true);

        //inicializamos los provers
        mAutnProvider = new AutenProvedors();
        mEstudianteProvedor = new EstudianteProvedors();


        mButtonRegister = findViewById(R.id.btnRegister);
        mTxtInputCorreo = findViewById(R.id.txtInputCorreo);
        mTxtInputNombre = findViewById(R.id.txtInputNombre);
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
        final String password = mTextInputPassword.getText().toString();

        if (!nombre.isEmpty() && !correo.isEmpty() && ! password.isEmpty()) {
            if (password.length() >= 6){
                //mDialog.show(); have referencia los puntos al caragar
                registar(nombre, correo,password);

            }
            else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    void registar(final String nombre,final String correo, String password) {
        mAutnProvider.registar(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Estudiante estudiante = new Estudiante(id,nombre,correo);
                    create(estudiante);
                }
                else {
                    Toast.makeText(RegistrarEstudianteActivity.this, "No se pudo registrar Estudiante", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //verificar crearte  ojo____________________mejorandocodigo video 08:20__________________________________________
    void create(Estudiante estudiante) {
        mEstudianteProvedor.create(estudiante).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //Toast.makeText(RegistrarEstudianteActivity.this, "Se registro exitosamente el Estudiante", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrarEstudianteActivity.this, princiEstudiActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegistrarEstudianteActivity.this, "No se pudo crear el estudiante", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
    private void saveUser(String id,String name, String email) {
        String selectedUser = mPref.getString("user", "");
        //importamos models del user
        User user = new User();
        user.setEmail(email);
        user.setName(name);

        if (selectedUser.equals("driver")){
            //cambiamos nombre driver a docente
            mDataBase.child("User").child("Drivers").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrarEstudianteActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegistrarEstudianteActivity.this, "Fallo el Registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if (selectedUser.equals("client")){
            mDataBase.child("User").child("Clients").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegistrarEstudianteActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegistrarEstudianteActivity.this, "Fallo el Registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }*/
}