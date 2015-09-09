package com.example.adelchi.androiddesignlibraryadelchi;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.adelchi.androiddesignlibraryadelchi.anim.ClassAnimator;

/**
 * Created by Adelchi on 01/09/2015.
 * Activity esempio per la gestione del toolbar esteso che collassa allo slide della pagina
 * La maggior parte del lavoro è fatta dal file xml del layout (activity_collapsing_toolbar)
 * sfruttando le direttive della design library
 */
public class CollapsingToolbarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);

        mContext = this;

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.fabAdd);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coorLayout);

        /**
         * Lega il coordinatorlayout al floatingactionbutton in modo che quando viene scrollato
         * questo sparisce
         */
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();
        ScrollAwareFABBehavior scrollAwareFABBehavior = new ScrollAwareFABBehavior(this, null);
        p.setBehavior(scrollAwareFABBehavior);
        floatingActionButton.setLayoutParams(p);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * in caso la versione android è maggiore o uguale alla 21 posso effettuare le
                 * animazioni, altrimenti nascondo/mostro senza animazioni
                 */
                View myView = findViewById(R.id.scrollView);
                ClassAnimator classAnimator = new ClassAnimator(myView);

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    classAnimator.revealView();
                }else{
                    if(myView.getVisibility() == View.VISIBLE){
                        myView.setVisibility(View.INVISIBLE);
                    }else {
                        myView.setVisibility(View.VISIBLE);
                    }
                }

                Snackbar.make(coordinatorLayout, "Mostra/Nascondi lista elementi", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collapsing_toolbar, menu);
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
