package com.example.adelchi.androiddesignlibraryadelchi;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SELECTED_ID = "SELECTED_ID";
    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionbarDrawerToggle;
    private int selectedDrawerElem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContentFragment contentFragment = new ContentFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, contentFragment);
        fragmentTransaction.commit();


        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        mDrawer = (NavigationView)findViewById(R.id.nav_drawer);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        mActionbarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.Apri, R.string.Chiudi);

        mDrawerLayout.setDrawerListener(mActionbarDrawerToggle);

        mActionbarDrawerToggle.syncState();

        mDrawer.setNavigationItemSelectedListener(this);

        selectedDrawerElem = savedInstanceState == null ? R.id.nav_item_1 : savedInstanceState.getInt(SELECTED_ID);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionbarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        selectedDrawerElem = menuItem.getItemId();
        if(getCurrentFocus() != null) {
            Snackbar.make(getCurrentFocus(), menuItem.getTitle(), Snackbar.LENGTH_SHORT).show();
        }
        ContentElement fragment = new ContentElement();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (selectedDrawerElem){
            case R.id.nav_item_2:
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ID, selectedDrawerElem);
    }
}
