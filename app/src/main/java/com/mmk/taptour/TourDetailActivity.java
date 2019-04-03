package com.mmk.taptour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class TourDetailActivity extends AppCompatActivity {
    private Toolbar toolbarDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);
        setup();
    }

    public void setup(){

        toolbarDetail=(Toolbar)findViewById(R.id.toolbarTourDetail);
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
