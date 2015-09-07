package com.example.adelchi.androiddesignlibraryadelchi;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.adelchi.androiddesignlibraryadelchi.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransitionActivityA extends AppCompatActivity {

    private Toolbar mToolbar;
    private ImageView imageView, imageView1, imageView2, imageView3;
    private Context mContext;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mRecyclerViewAdapter;

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
            getWindow().setReenterTransition(new Fade().setDuration(250));

        }

        setContentView(R.layout.activity_transition);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        if(getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new LinearLayoutManager(this);
        } else {
            mLayoutManager = new GridLayoutManager(this, 2);
        }

        List<Element> elements = new ArrayList<>();
        elements.add(new Element("Foto 1", R.drawable.gopro_small));
        elements.add(new Element("Foto 2", R.drawable.gopro_small1));
        elements.add(new Element("Foto 3", R.drawable.gopro_small2));
        elements.add(new Element("Foto 4", R.drawable.gopro_small3));

        mRecyclerViewAdapter = new RecyclerViewAdapter(elements, this, TransitionActivityA.this);

        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRecyclerView.setLayoutManager(mLayoutManager);

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

    public class Element{
        private String name;
        private Integer image;

        public Element(String name, Integer image) {
            this.name = name;
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getImage() {
            return image;
        }

        public void setImage(Integer image) {
            this.image = image;
        }
    }
}
