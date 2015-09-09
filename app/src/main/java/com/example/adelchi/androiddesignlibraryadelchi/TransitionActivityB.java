package com.example.adelchi.androiddesignlibraryadelchi;

import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.example.adelchi.androiddesignlibraryadelchi.anim.ClassAnimator;

public class TransitionActivityB extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    Toolbar mToolbar;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Definisce come il secondo activity deve entrare, in questo caso non viene usato il
         * file xml ma viene definita una transizione base dell'sdk
         */
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            /*Fade fade = new Fade();
            fade.setDuration(500);
            getWindow().setEnterTransition(fade);*/

            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.transition_a);
            getWindow().setEnterTransition(transition);

        }

        setContentView(R.layout.activity_transition_activity_b);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbarLayout);

        setSupportActionBar(mToolbar);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView)findViewById(R.id.imageview);

        Integer value = getIntent().getExtras().getInt("img", 0);
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName("imageValeAde" + value.toString());
        }

        collapsingToolbarLayout.setTitle(getIntent().getExtras().getString("txt"));

        switch (value){
            case R.drawable.gopro_small:
                Glide.with(this).load(R.drawable.gopro).into(imageView);
                break;
            case R.drawable.gopro_small1:
                Glide.with(this).load(R.drawable.gopro_1).into(imageView);
                break;
            case R.drawable.gopro_small2:
                Glide.with(this).load(R.drawable.gopro_2).into(imageView);
                break;
            case R.drawable.gopro_small3:
                Glide.with(this).load(R.drawable.gopro_3).into(imageView);
                break;
        }

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
        getMenuInflater().inflate(R.menu.menu_transition_activity_b, menu);
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
