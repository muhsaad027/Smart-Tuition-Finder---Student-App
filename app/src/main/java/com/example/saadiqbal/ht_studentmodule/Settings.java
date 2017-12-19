package com.example.saadiqbal.ht_studentmodule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.settingstoolbar);
        toolbar.setTitle("Settings");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbartextcolor));
        setSupportActionBar(toolbar);
    }
}
