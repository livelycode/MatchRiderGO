package com.livelycode.matchridergo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Leye on 23.02.16.
 */
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

        activityStack.push(this);
        Log.i("System.out", String.valueOf(activityStack.size()));

        ScrollView sv = (ScrollView) findViewById(R.id.scrollView);


        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        // Add text
        for(int i = 1; i < 50; i++) {

            TextView tv = new TextView(this);
            tv.setText("my text");
            ll.addView(tv);
        }

        // Add the LinearLayout element to the ScrollView
        sv.addView(ll);
    }
}