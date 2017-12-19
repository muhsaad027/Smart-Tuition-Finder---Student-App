package com.example.saadiqbal.ht_studentmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class FAQs extends AppCompatActivity {
    Button sub_mit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        sub_mit = (Button)findViewById(R.id.submit);
        sub_mit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FAQs.this,Thanks.class);
                startActivity(intent);
                finish();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.faqtool);
        toolbar.setTitle("FAQs");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbartextcolor));
        setSupportActionBar(toolbar);
    }
}