package com.mmk.taptour;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.mmk.taptour.Fragments.BookingsFragment;
import com.mmk.taptour.Fragments.FavoritesFragment;
import com.mmk.taptour.Fragments.ToursFragment;

import Constans.Constants;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private AlertDialog alertDialogSignOut;
    private TextView displayName;


    @Override
    protected void onResume() {
        super.onResume();
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();

        //TODO Navigation View Items Clicked

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                   navigationView.setCheckedItem(R.id.nav_booking);

                drawerLayout.closeDrawers();
                Toast.makeText(MainActivity.this,menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                        changeFragment(new ToursFragment());
                        break;
                    case R.id.nav_booking:
                        changeFragment(new BookingsFragment());
                        break;
                    case R.id.nav_favourites:
                        changeFragment(new FavoritesFragment());
                        break;
                    case R.id.nav_profile:
                        Intent passToLoginRegister=new Intent(getApplicationContext(),LoginRegisterActivity.class);
                        startActivity(passToLoginRegister);
                        break;
                    case R.id.nav_signout:
                        signOut();
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
        displayName=(TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_displayName);
        if (Constants.companyID!=null)
            displayName.setText("Company Name");
        else
            displayName.setText("Mirzemehdi Kerimov");
        changeFragment(new ToursFragment());
        toolbar.setTitle(R.string.title_toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer_menu);
        drawerToggle=new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }
    public void signOut(){
        displayName.setText("Mirzemehdi Kerimov");
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.alertDialogTitleForSignOut);
        builder.setMessage(R.string.alertDialogMessageForSignout);
        builder.setPositiveButton(R.string.alertDialogOkButtonForSignout, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeFragment(new ToursFragment());
                navigationView.setCheckedItem(R.id.nav_home);
                Constants.currentUser=null;
                Constants.companyID=null;
                alertDialogSignOut.dismiss();

            }
        });

        builder.setNegativeButton(R.string.alertDialogCancelButtonForSignOut, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialogSignOut.dismiss();


            }
        });
         alertDialogSignOut=builder.create();
         alertDialogSignOut.show();

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public void changeFragment(Fragment fragment){

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment)
                .commit();

    }

}
