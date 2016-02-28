package com.livelycode.matchridergo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.livelycode.matchridergo.databinding.ActivityRideDetailsBinding;

public class RideDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_ride_details);

        Bundle bundle = getIntent().getExtras();
        long ride_index = bundle.getLong("ride_index");

        ActivityRideDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ride_details);
        binding.setRide(GlobalData.getInstance().getRides()[(int) ride_index]);

        // Enable `Back' button in ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
