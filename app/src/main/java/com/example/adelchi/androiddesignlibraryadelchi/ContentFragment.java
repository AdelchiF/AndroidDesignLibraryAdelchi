package com.example.adelchi.androiddesignlibraryadelchi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Adelchi on 27/08/2015.
 * Fragment di default quando si apre l'app
 */
public class ContentFragment extends Fragment {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.content_fragment, container, false);

        return mView;
    }
}
