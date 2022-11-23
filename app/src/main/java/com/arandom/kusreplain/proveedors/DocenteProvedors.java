package com.arandom.kusreplain.proveedors;

import com.arandom.kusreplain.models.Docenter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DocenteProvedors {

    DatabaseReference mDataBase;

    public DocenteProvedors() {
        //hace referencia al cambio de nombres db
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Users").child("Docentes");
    }

    public Task<Void> create(Docenter docenter) {
        return mDataBase.child(docenter.getId()).setValue(docenter);
    }
}
