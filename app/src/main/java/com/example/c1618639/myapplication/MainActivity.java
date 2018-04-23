package com.example.c1618639.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

//import static com.example.c1618639.myapplication.CameraActivity.REQUEST_IMAGE_CAPTURE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;
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

        final SharedPreferences sp = this.getSharedPreferences("main_preferences", Context.MODE_PRIVATE);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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


        this.navView = (NavigationView) findViewById(R.id.nav_view);
        this.navView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            this.navView.setCheckedItem(DEFAULT_DRAWER_ITEM);
            this.navView.getMenu().performIdentifierAction(DEFAULT_DRAWER_ITEM, 0);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            ImageView userImage = (ImageView)findViewById(R.id.userImg);
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            userImage.setImageBitmap(imageBitmap);
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

        switch (id) {
            case R.id.menu_home:
                changeInternalFragment(new MainMenuFragment(), R.id.fragmentContainer);
                break;
            case R.id.menu_about_us:
                changeInternalFragment(new AboutUsFragment(), R.id.fragmentContainer);
                break;
            case R.id.menu_bee_map:
                Intent myIntent = new Intent(MainActivity.this, MapActivity.class);
                MainActivity.this.startActivity(myIntent);
                break;
            case R.id.menu_information:
                changeInternalFragment(new InfoPageFragment(), R.id.fragmentContainer);
                break;
            case R.id.menu_camera:
//                Intent openCameraIntent = new Intent(MainActivity.this, CameraActivity.class);
//                MainActivity.this.startActivity(openCameraIntent);
                Intent cameraIntent = new Intent(MainActivity.this, CameraActivity.class);
                MainActivity.this.startActivity(cameraIntent);
                break;
            case R.id.menu_my_gallery:
                changeInternalFragment(new GalleryFragment(), R.id.fragmentContainer);
                break;
            case R.id.menu_media:
                changeInternalFragment(new MediaFragment(), R.id.fragmentContainer);
                break;
        }

        this.mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeInternalFragment(Fragment fragment, int fragmentContainer){
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        supportFragmentManager.beginTransaction()
                .replace(fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();


    }

    // Youtube
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MediaFragment fragment = new MediaFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.youtube, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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








