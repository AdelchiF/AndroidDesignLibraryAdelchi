package com.example.adelchi.androiddesignlibraryadelchi;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
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
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private static final String SELECTED_ID = "SELECTED_ID";
    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionbarDrawerToggle;
    private int selectedDrawerElem;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        /**
         * Inizializzo il primo fragment da inserire nell'activity
         * viene inserito all'interno del fragment_container
         */

        ContentFragment contentFragment = new ContentFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, contentFragment);
        fragmentTransaction.commit();

        // setto il toolbar come ActionBar
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        /**
         * Il NavigationDrawer è stato implementato nelle design library
         * attraverso il NavigationView
         */
        mDrawer = (NavigationView)findViewById(R.id.nav_drawer);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // lego il DrawerLayout con il toolbar
        mActionbarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.Apri, R.string.Chiudi);

        mDrawerLayout.setDrawerListener(mActionbarDrawerToggle);

        // sincronizzza lo stato del navigationdrawer con il toolbar nel caso in cui è aperto o chiuso
        mActionbarDrawerToggle.syncState();

        /**
         * Per aggiungere elementi alla lista del NavigationDrawer il design library prevede il passaggio
         * di un file xml di tipo menu (menu_drawer) direttamente nel NavigationView(nell'xml) vedi
         * (app:menu="@menu/menu_drawer").
         * Qui viene definito come il sistema deve comportarsi quando viene cliccato un elemento della
         * lista del navigationdrawer
         */
        mDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                selectedDrawerElem = menuItem.getItemId();

                switch (selectedDrawerElem) {
                    case R.id.nav_item_2:
                        ContentElement fragment = new ContentElement();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                    case R.id.nav_item_3:
                        startActivity(new Intent(mContext, TabActivity.class));
                        break;
                    case R.id.nav_item_4:
                        startActivity(new Intent(mContext, CollapsingToolbarActivity.class));
                        break;
                    case R.id.nav_item_5:
                        startActivity(new Intent(mContext, TransitionActivityA.class));
                        break;
                }

                // una volta eseguito il click chiudo il navigationdrawer
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        selectedDrawerElem = savedInstanceState == null ? R.id.nav_item_2 : savedInstanceState.getInt(SELECTED_ID);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ID, selectedDrawerElem);
    }

}
