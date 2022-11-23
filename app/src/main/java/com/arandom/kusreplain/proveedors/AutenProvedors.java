package com.arandom.kusreplain.proveedors;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AutenProvedors {

    //conectamos a firebase
    FirebaseAuth mAuth;

    public AutenProvedors() {
        //instanciamos la firebase y database
        mAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> registar(String correo, String password) {
        return mAuth.createUserWithEmailAndPassword(correo, password);
    }
    public Task<AuthResult> login(String correo, String password) {
        return mAuth.signInWithEmailAndPassword(correo, password);
    }
    //creamos metodo cerrar sesion
    public void logout() {
        mAuth.signOut();
    }
    public String getId() {//verificar si causa error
        return mAuth.getCurrentUser().getUid();
    }
}
