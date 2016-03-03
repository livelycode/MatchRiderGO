package com.livelycode.matchridergo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.livelycode.matchridergo.data.MatchRiderException;
import com.livelycode.matchridergo.data.Ride;
import com.livelycode.matchridergo.databinding.ActivityRideDetailsBinding;
import com.livelycode.matchridergo.global.ClientState;
import com.livelycode.matchridergo.global.DataReceiver;
import com.livelycode.matchridergo.global.DataService;

public class RideDetailsActivity extends AppCompatActivity implements DataReceiver.Receiver{
    private final String TAG = RideDetailsActivity.class.getSimpleName();
    private RideDetailsActivity instance = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The index of which `Ride' was pressed is saved in the bundle
        Bundle bundle = getIntent().getExtras();
        long ride_index = bundle.getLong("ride_index");

        // Fetch data from data service
        DataReceiver receiver = new DataReceiver(new Handler());
        receiver.setReceiver(instance);
        Intent dataIntent = new Intent(Intent.ACTION_SYNC, null, instance, DataService.class);
        dataIntent.putExtra("receiver", receiver);
        dataIntent.putExtra("request", DataService.RIDE_DETAILS);
        dataIntent.putExtra("ride_index", ride_index);
        startService(dataIntent);
    }

    @Override
    public void onReceiveResult(int statusCode, Bundle bundle) throws MatchRiderException {
        switch(statusCode) {
            case DataService.STATUS_RUNNING:
                break;
            case DataService.STATUS_FINISHED:

                ActivityRideDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_ride_details);
                binding.setRide((Ride) bundle.getParcelable("ride"));
                // Enable `Back' button in ActionBar
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                break;
            case DataService.STATUS_ERROR:
                Log.d(TAG, "Receiving error");
                break;
        }

    }
}
