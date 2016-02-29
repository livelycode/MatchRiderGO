package com.livelycode.matchridergo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class UserAccountActivity extends MainActivity {
    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(1).setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreateDrawer(R.layout.activity_user_account);
        getSupportActionBar().setTitle(R.string.user_account_title);
        navigationView.getMenu().getItem(1).setChecked(true);
        fab.hide();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}