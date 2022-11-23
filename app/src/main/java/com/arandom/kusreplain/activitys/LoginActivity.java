package com.arandom.kusreplain.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arandom.kusreplain.R;
import com.arandom.kusreplain.activitys.estudiante.princiEstudiActivity;
import com.arandom.kusreplain.activitys.docenter.princiDocenteActivity;
import com.arandom.kusreplain.includes.MyToolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText mTxtInputCorreo;
    TextInputEditText mTextImputPassword;
    Button mButtonLogin;

    //creamos lapropiedad
    FirebaseAuth mAuth;
    DatabaseReference mDataBase;

    //creamos alerta dialog
    //AlertDialog mDialog;

    //este es el indentificador para saber tipo de ususarios
    SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MyToolbar.show(this,"Iniciar sesión",true);

        mTxtInputCorreo= findViewById(R.id.txtInputCorreo);
        mTextImputPassword = findViewById(R.id.textInputPassword);
        mButtonLogin = findViewById(R.id.btnLogin);

        //instanciamos la firebase y database
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        //instanciamos dialog verificar
        //mDialog = new SpotsDialog.Builder().setco

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String correo = mTxtInputCorreo.getText().toString();
        String password = mTextImputPassword.getText().toString();

        if (!correo.isEmpty() &&  !password.isEmpty()) {
            //validaciones de los campos que no esten vacias
            if (password.length() >= 6) {
                //dialog verificacion
                //mDialog.show();
                mAuth.signInWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //creamos metodo de logeo como estu y si no como doce
                            String user = mPref.getString("user","");
                            if (user.equals("estudiante")) {
                                Intent intent = new Intent(LoginActivity.this, princiEstudiActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this, princiDocenteActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                            //Toast.makeText(LoginActivity.this, "Login se realizo correctamente", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Correo o Contraseña Incorrecto", Toast.LENGTH_SHORT).show();
                        }
                        //termina dialog verificacion
                        //mDialog.dismiss();
                    }
                });
            }
            else {
                Toast.makeText(LoginActivity.this, "la Contraseñason debe tener mas de 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(LoginActivity.this, "el Correo y la Contraseña son obligatorio", Toast.LENGTH_SHORT).show();
        }
    }
}