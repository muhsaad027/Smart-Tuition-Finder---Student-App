package com.example.saadiqbal.ht_studentmodule.CoursePKG;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.saadiqbal.ht_studentmodule.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Course extends AppCompatActivity {

    public RecyclerView mRecyclerView,mRecyclerView2;
    public RecyclerView.LayoutManager mLayoutManager;
    public RecyclerView.Adapter mAdapter,mAdapter2;
    public ArrayList<String> alName,alName2;
    public ArrayList<Integer> alImage,alImage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        mycourses();
        recommendedcourses();
        Toolbar toolbar = (Toolbar) findViewById(R.id.coursetoolbar);
        toolbar.setTitle("My Courses");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbartextcolor));
        setSupportActionBar(toolbar);
    }
     void mycourses() {

         alName = new ArrayList<>(Arrays.asList("Demo1", "Demo2... ", "Demo3...", "Demo4...", "Demo5...", "Demo6...", "Demo7...", "Demo8..."));
         alImage = new ArrayList<>(Arrays.asList(R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h));

         // Calling the RecyclerView
         mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
         mRecyclerView.setHasFixedSize(true);

         // The number of Columns
         mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
         mRecyclerView.setLayoutManager(mLayoutManager);

         mAdapter = new HLVAdapter(this, alName, alImage);
         mRecyclerView.setAdapter(mAdapter);
     }

     void recommendedcourses(){

         alName2 = new ArrayList<>(Arrays.asList("Demo1", "Demo2... ", "Demo3...", "Demo4...", "Demo5...", "Demo6...", "Demo7...", "Demo8..."));
         alImage2 = new ArrayList<>(Arrays.asList(R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h));

         // Calling the RecyclerView
         mRecyclerView2 = (RecyclerView) findViewById(R.id.recycler_view2);
         mRecyclerView2.setHasFixedSize(true);

         // The number of Columns
         mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
         mRecyclerView2.setLayoutManager(mLayoutManager);

         mAdapter2 = new HLVAdapter2(this, alName2, alImage2);
         mRecyclerView2.setAdapter(mAdapter);
     }
}
