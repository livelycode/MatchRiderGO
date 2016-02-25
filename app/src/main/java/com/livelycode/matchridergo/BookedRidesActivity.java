package com.livelycode.matchridergo;

import com.livelycode.matchridergo.MatchRiderObjects.*;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.sample_ride_row_view, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.ride_row_driver_name);
        TextView textView2 = (TextView) rowView.findViewById(R.id.ride_row_start_location);
        textView.setText(values[position]);

        return rowView;
    }
}


public class BookedRidesActivity extends MainActivity {
    Ride ride = new Ride();

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(0).setChecked(true);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer(R.layout.activity_booked_rides);
        navigationView.getMenu().getItem(0).setChecked(true);

        activityStack.push(this);
        Log.i("System.out", String.valueOf(activityStack.size()));

        ListView ll = (ListView) findViewById(R.id.booked_rides_list);

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);


        ll.setAdapter(adapter);
        ll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                final long item_id = (long) parent.getItemIdAtPosition(position);
                Log.i("System.out", "you pressed " + item);
                Log.i("System.out", "you pressed " + String.valueOf(item_id));
            }
        });
    }
}