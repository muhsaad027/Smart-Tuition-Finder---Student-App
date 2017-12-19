package com.example.saadiqbal.ht_studentmodule.CoursePKG;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.saadiqbal.ht_studentmodule.R;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.coursedetailtolbar);
        toolbar.setTitle("Course Detail");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbartextcolor));
        setSupportActionBar(toolbar);

    }
}
