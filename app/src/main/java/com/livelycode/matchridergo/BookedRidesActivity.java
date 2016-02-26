package com.livelycode.matchridergo;

import com.livelycode.matchridergo.MatchRiderObjects.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


class MySimpleArrayAdapter extends ArrayAdapter<Ride> {
    private final Context context;
    private final Ride[] rides;

    public MySimpleArrayAdapter(Context context, Ride[] values) {
        super(context, -1, values);
        this.context = context;
        this.rides = values;
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
        TextView textView = (TextView) rowView.findViewById(R.id.ride_row_driver_name);
        TextView textView2 = (TextView) rowView.findViewById(R.id.ride_row_start_location);
        textView.setText(this.rides[position].driver.name);
        textView2.setText(this.rides[position].startLocation);

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

        Ride[] rides = new Ride[20];
        for(int i=0; i < 20; i++) {
            rides[i] = new Ride();
        }

        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, rides);

        ll.setAdapter(adapter);
        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //final String item = (String) parent.getItemAtPosition(position);
                final long item_id = (long) parent.getItemIdAtPosition(position);
                //Log.i("System.out", "you pressed " + item);

                Log.i("System.out", "you pressed " + String.valueOf(item_id));
                Intent rideDetails= new Intent(parent.getContext(), RideDetailsActivity.class);
                startActivity(rideDetails);
            }
        });
    }
}