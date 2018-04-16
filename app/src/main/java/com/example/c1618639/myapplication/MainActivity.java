package com.example.c1618639.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.CardView;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //private TextView mTextMessage;

    private static final int DEFAULT_DRAWER_ITEM = R.id.menu_home;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(true);
        //actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        this.mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );


        this.mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        this.navView = (NavigationView) findViewById(R.id.nav_view);
        this.navView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            this.navView.setCheckedItem(DEFAULT_DRAWER_ITEM);
            this.navView.getMenu().performIdentifierAction(DEFAULT_DRAWER_ITEM, 0);
        }

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

    @Override
    public void onBackPressed() {
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        int id = item.getItemId();

        System.out.println("AAAAAAA");

        switch (id) {
            case R.id.menu_home:
                System.out.println("TEST");
                changeInternalFragment(new MainMenuFragment(), R.id.fragmentContainer);
                break;
            case R.id.menu_about_us:
                changeInternalFragment(new AboutUsFragment(), R.id.fragmentContainer);
                break;
            case R.id.menu_media:
                changeInternalFragment(new MediaFragment(), R.id.fragmentContainer);
                break;
            case R.id.menu_information:
                changeInternalFragment(new InfoPageFragment(), R.id.fragmentContainer);
                break;
        }

//        CardView mapCardView = findViewById(R.id.card_view_map);
//        mapCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(MainActivity.this, MapActivity.class);
//                MainActivity.this.startActivity(myIntent);
//            }
//        });

        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeInternalFragment(Fragment fragment, int fragmentContainer){
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        supportFragmentManager.beginTransaction()
                .replace(fragmentContainer, fragment)
                .commit();
    }




}


