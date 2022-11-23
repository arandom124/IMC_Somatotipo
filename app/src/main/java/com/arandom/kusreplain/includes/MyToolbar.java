package com.arandom.kusreplain.includes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arandom.kusreplain.R;

public class MyToolbar {
    public static void show(AppCompatActivity activity, String title, boolean upButton) {
        //intanciamos toolbar
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
}
