package com.gladysinc.gladys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gladysinc.gladys.Settings.settingsActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView user;
    private MenuItem button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbarTitle(R.string.dashboard);

        initialdeclarations();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout, new DashboardFragment()).commit();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.DashBoard) {

            getSupportFragmentManager().beginTransaction().replace(R.id.layout, new DashboardFragment()).commit();
            setToolbarTitle(R.string.dashboard);

            button.setVisible(false);

        } else if (id == R.id.Rooms) {

            getSupportFragmentManager().beginTransaction().replace(R.id.layout, new RoomsFragment()).commit();
            setToolbarTitle(R.string.rooms);

            button.setVisible(false);

        } else if (id == R.id.Timeline) {

            getSupportFragmentManager().beginTransaction().replace(R.id.layout, new TimelineFragment()).commit();
            setToolbarTitle(R.string.timeline);

        } else if (id == R.id.Réveils) {

            getSupportFragmentManager().beginTransaction().replace(R.id.layout, new AlarmFragment()).commit();
            setToolbarTitle(R.string.alarm);

        }  /*else if (id == R.id.Tchat) {

            getSupportFragmentManager().beginTransaction().replace(R.id.layout, new TchatFragment()).commit();
            setToolbarTitle(R.string.tchat);

            button.setVisible(false);

        }*/ else if (id == R.id.Infos) {

            getSupportFragmentManager().beginTransaction().replace(R.id.layout, new InfosFragment()).commit();
            setToolbarTitle(R.string.info);

            button.setVisible(false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initialdeclarations(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);

        user = (TextView) header.findViewById(R.id.user);
    }

    public void setToolbarTitle(int title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        button = menu.findItem(R.id.add_button);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), settingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    protected void onResume(){
        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String prefuser = preferences.getString("nom" , "");
        String prefusername = preferences.getString("prenom", "");


        if(!Objects.equals(prefuser, "") & !Objects.equals(prefusername, "")){
            user.setText("" + prefusername + " " + prefuser);
        }
        else if (!Objects.equals(prefusername, "") & Objects.equals(prefuser, "") ){
            user.setText(prefusername);

        }
        else if (!Objects.equals(prefuser, "") & Objects.equals(prefusername, "")){
            user.setText(prefuser);

        }

    }

}
