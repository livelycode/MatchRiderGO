package com.livelycode.matchridergo;

import android.animation.Animator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.livelycode.matchridergo.MatchRiderObjects.User;

import java.util.Stack;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int NAVDRAWER_LAUNCH_DELAY = 250;
    private static final int CONTENT_FADEIN_DURATION = 250;
    private static final int CONTENT_FADEOUT_DURATION = 150;
    private boolean mAnimating;
    private Handler mHandler = new Handler();

    public DrawerLayout drawer;
    public FloatingActionButton fab;
    public NavigationView navigationView;
    public Toolbar toolbar;
    public ActionBarDrawerToggle toggle;
    protected FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onCreateDrawer(final int layoutResID) {
        // The sequence of calls is important here.
        // At first, inflate the XML of `activity_main'.
        setContentView(R.layout.activity_main);
        // Now a toolbar can be set up, because `activity_main.xml' includes `app_bar_main.xml'
        // which provides `android.support.v7.widget.Toolbar' (this is the default
        // `Navigation Drawer Activity' pattern, not mine).
        setupActionBar();

        // `content' now serves as the place to put the layout of other activities into,
        // specified by `layoutResID'.
        content = (FrameLayout) findViewById(R.id.fragment_container);
        getLayoutInflater().inflate(layoutResID, content, true);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();

        content.setAlpha(0);
        content.animate().alpha(1).setDuration(CONTENT_FADEIN_DURATION).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) { mAnimating = true; }

            @Override
            public void onAnimationEnd(Animator animation) { mAnimating = false; }

            @Override
            public void onAnimationCancel(Animator animation) { mAnimating = false; }

            @Override
            public void onAnimationRepeat(Animator animation) { }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mAnimating) content.setAlpha(1);
    }

    public void goToIntent(final Intent intent) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        }, NAVDRAWER_LAUNCH_DELAY);

        content.animate().alpha(0).setDuration(CONTENT_FADEOUT_DURATION);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (item.getItemId()) {
            case R.id.nav_booked_rides:
                intent = new Intent(this, BookedRidesActivity.class);
                goToIntent(intent);
                return true;

            case R.id.nav_user_account:
                intent = new Intent(this, UserAccountActivity.class);
                goToIntent(intent);
                return true;
        }
        return false;
    }
}
