package com.livelycode.matchridergo;

import com.livelycode.matchridergo.data.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


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
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View rowView = inflater.inflate(R.layout.sample_ride_row_view, parent, false);

        RideRowView rowView = new RideRowView(parent.getContext());
        TextView driver_name = (TextView) rowView.findViewById(R.id.ride_row_driver_name);
        TextView start_location = (TextView) rowView.findViewById(R.id.ride_row_start_location);
        TextView destination_location = (TextView) rowView.findViewById(R.id.ride_row_destination_location);
        RatingBar driver_score = (RatingBar) rowView.findViewById(R.id.ride_row_driver_score);
        TextView ride_date = (TextView) rowView.findViewById(R.id.ride_row_date);

        driver_name.setText(this.rides[position].getDriver().getName());
        start_location.setText(this.rides[position].getStartLocation());
        destination_location.setText(this.rides[position].getDestinationLocation());
        driver_score.setRating((float) this.rides[position].getDriver().getScore());
        ride_date.setText(this.rides[position].getDate());

        return rowView;
    }
}


public class BookedRidesActivity extends MainActivity {

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(0).setChecked(true);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer(R.layout.activity_booked_rides);
        navigationView.getMenu().getItem(0).setChecked(true);


        ListView ll = (ListView) findViewById(R.id.booked_rides_list);

        final BookedRidesArrayAdapter adapter = new BookedRidesArrayAdapter(this, GlobalData.getInstance().getRides());

        ll.setAdapter(adapter);
        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final long ride_index = parent.getItemIdAtPosition(position);
                
                Intent rideDetails = new Intent(parent.getContext(), RideDetailsActivity.class);
                rideDetails.putExtra("ride_index", ride_index);
                startActivity(rideDetails);
            }
        });
    }
}