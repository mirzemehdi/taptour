package com.mmk.taptour;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

        //TODO Navigation View Items Clicked

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                navigationView.setCheckedItem(menuItem);
                drawerLayout.closeDrawers();
                Toast.makeText(MainActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                switch (menuItem.getItemId()){

                    case R.id.nav_booking:

                        break;
                    case R.id.nav_favourites:

                        break;
                    case R.id.nav_profile:

                        break;
                    case R.id.nav_signout:

                        break;
                    case R.id.nav_settings:

                        break;


                }


                return true;
            }
        });

    }


    public  void setup(){

        /*
        * Toolbar ,Drawer,NavigationView Setup
        *
        * */

        toolbar=(Toolbar)findViewById(R.id.toolbar_main);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayoutMain);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
        toolbar.setTitle(R.string.title_toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_menu);
        drawerToggle=new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
