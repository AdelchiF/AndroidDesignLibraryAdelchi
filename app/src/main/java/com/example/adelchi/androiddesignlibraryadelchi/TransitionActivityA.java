package com.example.adelchi.androiddesignlibraryadelchi;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class TransitionActivityA extends AppCompatActivity implements View.OnClickListener {

    Toolbar mToolbar;
    ImageView imageView, imageView1, imageView2, imageView3;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        /**
         * Definisce il tipo di transizione tra un activity e l'altro
         * il tipo di transition è stato definito all'interno del file xml
         * Bisogna ricordare che la transizione si compone di 4 fasi:
         * @exit: quando il primo activity esce
         * @enter: quando il secondo activity entra
         * @return: quando il secondo activity esce
         * @reenter: quando il primo activity rientra
         *
         * Da non dimenticare: aggiungere nello style(v21) l'item
         * "android:windowContentTransitions" a true
         * (non ho capito quale tipo di differenza porta ma viene sembra richiesto anche
         * nella documentazione ufficiale)
         */
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.transition_a);
            getWindow().setExitTransition(transition);
        }

        setContentView(R.layout.activity_transition);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView)findViewById(R.id.imageview);
        imageView1 = (ImageView)findViewById(R.id.imageview1);
        imageView2 = (ImageView)findViewById(R.id.imageview2);
        imageView3 = (ImageView)findViewById(R.id.imageview3);

        /**
         * Il transitionName è essenziale per definire quali tipi di elementi
         * devono essere condivisi nella transazione, deve essere sia nell'elemento
         * della prima activity che nell'elemento della seconda activity
         */
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName("imageValeAde");
            imageView1.setTransitionName("imageValeAde");
            imageView2.setTransitionName("imageValeAde");
            imageView3.setTransitionName("imageValeAde");
        }
        imageView.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transition, menu);
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
    public void onClick(View v) {
        /**
         * L'ActivityOptionsCompat serve per definire quale o quali elementi devono essere
         * condivisi tra le due activity, vengono poi passate nello startActivity
         */
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(TransitionActivityA.this, v, "imageValeAde");
        Intent intent = new Intent(mContext, TransitionActivityB.class);
        intent.putExtra("img", getResources().getResourceEntryName(v.getId()));
        startActivity(intent, activityOptionsCompat.toBundle());
    }
}
