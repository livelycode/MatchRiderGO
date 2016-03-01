package com.livelycode.matchridergo;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.livelycode.matchridergo.databinding.ActivityRideDetailsBinding;
import com.livelycode.matchridergo.global.ClientState;

public class RideDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // The index of which `Ride' was pressed is saved in the bundle
        Bundle bundle = getIntent().getExtras();
        long ride_index = bundle.getLong("ride_index");

        // Fill the layout (`activity_ride_details.xml') with information from GlobalData
        ActivityRideDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ride_details);
        binding.setRide(ClientState.getInstance().getRides()[(int) ride_index]);

        // Enable `Back' button in ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
