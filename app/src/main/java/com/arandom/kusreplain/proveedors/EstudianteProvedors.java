package com.arandom.kusreplain.proveedors;

import com.arandom.kusreplain.models.Estudiante;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EstudianteProvedors {

    DatabaseReference mDataBase;

    public EstudianteProvedors() {
        //hace referencia al cambio de nombres db
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Users").child("Estudiantes");
    }

    public Task<Void> create(Estudiante estudiante) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", estudiante.getNombre());
        map.put("correo", estudiante.getCorreo());
        return mDataBase.child(estudiante.getId()).setValue(map);
    }
}
