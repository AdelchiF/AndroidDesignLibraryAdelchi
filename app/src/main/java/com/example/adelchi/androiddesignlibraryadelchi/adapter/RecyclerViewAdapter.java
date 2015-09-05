package com.example.adelchi.androiddesignlibraryadelchi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adelchi.androiddesignlibraryadelchi.R;
import com.example.adelchi.androiddesignlibraryadelchi.TransitionActivityA;
import com.example.adelchi.androiddesignlibraryadelchi.TransitionActivityB;

import java.util.List;

/**
 * Created by Adelchi on 05/09/2015.
 * Adapter per il RecyclerView
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<TransitionActivityA.Element> elements;
    private ViewHolder vh;
    private Context mContext;
    private Activity mActivity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextView;
        public RelativeLayout mRelativeLayout;
        public ViewHolder(View v) {
            super(v);
            mImageView = (ImageView)v.findViewById(R.id.imageView);

            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mImageView.setTransitionName("imageValeAde");
            }

            mTextView = (TextView)v.findViewById(R.id.textView);
            mRelativeLayout = (RelativeLayout)v.findViewById(R.id.container_element);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(List<TransitionActivityA.Element> elements, Context mContext, Activity mActivity) {
        this.elements = elements;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_element, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        this.vh = vh;
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressWarnings("Deprecate")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TransitionActivityA.Element element = elements.get(position);

        //holder.mImageView.setImageDrawable(drawable);
        Glide.with(mContext).load(element.getImage()).into(holder.mImageView);
        holder.mImageView.setTag(element.getImage());
        holder.mTextView.setText(element.getName());

        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, v, "imageValeAde");
                Intent intent = new Intent(mContext, TransitionActivityB.class);
                intent.putExtra("img", (int)v.findViewById(R.id.imageView).getTag());
                mContext.startActivity(intent, activityOptionsCompat.toBundle());
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return elements.size();
    }
}