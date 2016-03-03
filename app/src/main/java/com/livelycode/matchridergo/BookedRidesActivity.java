package com.livelycode.matchridergo;

import com.livelycode.matchridergo.data.*;
import com.livelycode.matchridergo.global.ClientState;
import com.livelycode.matchridergo.global.DataReceiver;
import com.livelycode.matchridergo.global.DataService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


class BookedRidesArrayAdapter extends ArrayAdapter<Ride> {
    private final Context context;
    private final Ride[] rides;

    public BookedRidesArrayAdapter(Context context, Ride[] rides) {
        super(context, -1, rides);
        this.context = context;
        this.rides = rides;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /**
         * TODO: Instead of creating a RideRowView class with accompanying XML files, it might be
         * possible to only use an XML file and set each TextView individually.
         * I.e. DO NOT create a whole `RideRowView' object. Just use an XML layout file.
         */
        // Use the following code when implementing the behaviour above:
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View rowView = inflater.inflate(R.layout.sample_ride_row_view, parent, false);

        RideRowView rowView = new RideRowView(parent.getContext());
        TextView driver_name = (TextView) rowView.findViewById(R.id.ride_row_driver_name);
        TextView start_location = (TextView) rowView.findViewById(R.id.ride_row_start_location);
        TextView destination_location = (TextView) rowView.findViewById(R.id.ride_row_destination_location);
        RatingBar driver_score = (RatingBar) rowView.findViewById(R.id.ride_row_driver_score);
        TextView ride_date = (TextView) rowView.findViewById(R.id.ride_row_date);

        driver_name.setText(this.rides[position].getDriver().getFirstName());
        start_location.setText(this.rides[position].getStartLocation().getName());
        destination_location.setText(this.rides[position].getDestinationLocation().getName());
        driver_score.setRating((float) this.rides[position].getDriver().getScore());
        ride_date.setText(this.rides[position].getDate());

        return rowView;
    }
}


public class BookedRidesActivity extends MainActivity implements DataReceiver.Receiver{

    private BookedRidesActivity instance = this;

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(0).setChecked(true);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer(R.layout.activity_booked_rides);
        navigationView.getMenu().getItem(0).setChecked(true);
        fetchBookedRides();
        // Create a ListView and fill it with `RideRowView's using BookedRidesArrayAdapter

    }

    private void fetchBookedRides() {
        DataReceiver receiver = new DataReceiver(new Handler());
        receiver.setReceiver(instance);
        Intent dataIntent = new Intent(Intent.ACTION_SYNC, null, instance, DataService.class);
        dataIntent.putExtra("receiver", receiver);
        dataIntent.putExtra("request", DataService.BOOKED_RIDES);
        startService(dataIntent);
    }

    @Override
    public void onReceiveResult(int statusCode, Bundle bundle) throws MatchRiderException {
        switch (statusCode)
        {
            case DataService.STATUS_RUNNING:
                break;

            case DataService.STATUS_FINISHED:
                MatchRiderError error = bundle.getParcelable("error");

                if(error == null) {
                    ListView bookedRidesList = (ListView) findViewById(R.id.booked_rides_list);
                    Ride[] rides = (Ride[]) bundle.getParcelableArray("rides");
                    final BookedRidesArrayAdapter adapter = new BookedRidesArrayAdapter(this, rides);

                    bookedRidesList.setAdapter(adapter);
                    bookedRidesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                            // Get the position of the clicked item
                            final long ride_index = parent.getItemIdAtPosition(position);

                            // Start RideDetailsActivity and tell it which Ride was pressed
                            Intent rideDetails = new Intent(parent.getContext(), RideDetailsActivity.class);
                            rideDetails.putExtra("ride_index", ride_index);
                            startActivity(rideDetails);
                        }
                    });
                } else {
                    Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
                }

                break;

            case DataService.STATUS_ERROR:
                break;
        }
    }
}