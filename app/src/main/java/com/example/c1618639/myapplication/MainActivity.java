package com.example.c1618639.myapplication;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.CardView;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    //private TextView mTextMessage;

    private DrawerLayout mDrawerLayout;
//    private NavigationView navView;
//    private static final int DEFAULT_DRAWER_ITEM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(true);
        //actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        final SharedPreferences sp = this.getSharedPreferences("main_preferences", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        mDrawerLayout = findViewById(R.id.drawer_layout);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        int score = sp.getInt("score", 0);
                        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                        View header = navigationView.getHeaderView(0);
                        TextView scoreTextView = (TextView) header.findViewById(R.id.score);
                        scoreTextView.setText(getString(R.string.drawer_header_score, score));
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );


        this.mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//
//
//        this.navView = (NavigationView) findViewById(R.id.nav_view);
//        this.navView.setNavigationItemSelectedListener(this);


//        if (savedInstanceState == null) {
//            this.navView.setCheckedItem(DEFAULT_DRAWER_ITEM);
//            this.navView.getMenu().performIdentifierAction(DEFAULT_DRAWER_ITEM, 0);
//        }


//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }

        CardView mapCardView = findViewById(R.id.card_view_map);
        mapCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MapActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
