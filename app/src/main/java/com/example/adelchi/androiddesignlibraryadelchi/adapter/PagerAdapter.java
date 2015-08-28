package com.example.adelchi.androiddesignlibraryadelchi.adapter;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.adelchi.androiddesignlibraryadelchi.PagedFragment;

/**
 * Created by Adelchi on 27/08/2015.
 * Pager adapter
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return PagedFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Tab " + position;
    }
}
