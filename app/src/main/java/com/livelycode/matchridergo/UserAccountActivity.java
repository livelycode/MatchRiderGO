package com.livelycode.matchridergo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;


public class UserAccountActivity extends MainActivity {
    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(1).setChecked(true);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer(R.layout.activity_user_account);
        navigationView.getMenu().getItem(1).setChecked(true);
        fab.hide();

        activityStack.push(this);
        Log.i("System.out", String.valueOf(activityStack.size()));
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }

}