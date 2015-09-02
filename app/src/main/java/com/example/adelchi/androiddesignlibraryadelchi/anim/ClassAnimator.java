package com.example.adelchi.androiddesignlibraryadelchi.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by Adelchi on 01/09/2015.
 * Questa classe si occupa di effettuare l'animazzione di comparsa/scomparsa di una qualsiasi view
 */
public class ClassAnimator {

    Animator anim;
    View viewTarget;

    public ClassAnimator(View viewTarget) {

        this.viewTarget = viewTarget;
    }

    public void animateView(){

        if(viewTarget.getVisibility() == View.VISIBLE){
            // get the center for the clipping circle
            int cx = viewTarget.getWidth() / 2;
            int cy = viewTarget.getHeight() / 2;

            // get the initial radius for the clipping circle
            int initialRadius = viewTarget.getWidth();

            // create the animation (the final radius is zero)
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                anim = ViewAnimationUtils.createCircularReveal(viewTarget, cx, cy, initialRadius, 0);

                // make the view invisible when the animation is done
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        viewTarget.setVisibility(View.INVISIBLE);
                    }
                });

            }
        }else{

            // get the center for the clipping circle
            int cx = viewTarget.getWidth() / 2;
            int cy = viewTarget.getHeight() / 2;

            // get the final radius for the clipping circle
            int finalRadius = Math.max(viewTarget.getWidth(), viewTarget.getHeight());

            // make the view visible and start the animation
            viewTarget.setVisibility(View.VISIBLE);

            // create the animator for this view (the start radius is zero)
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                anim = ViewAnimationUtils.createCircularReveal(viewTarget, cx, cy, 0, finalRadius);
            }

        }
        anim.start();
    }

}
