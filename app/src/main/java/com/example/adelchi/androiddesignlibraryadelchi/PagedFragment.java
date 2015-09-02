package com.example.adelchi.androiddesignlibraryadelchi;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adelchi.androiddesignlibraryadelchi.adapter.PagerAdapter;

import java.util.zip.Inflater;


/**
 * Fragment utilizzato per costruire i contenuti del ViewPager
 * questo è solo un esempio, nella realtà sarà più complicato di così
 * questa classe viene richiamata dal PagerAdapter
 */
public class PagedFragment extends Fragment {

    public static final java.lang.String PAGE_NUM = "page_num";

    public PagedFragment() {
        // Required empty public constructor
    }

    /**
     * @param position numero del tab selezionato
     * @return viene ritornata una nuova istanza del fragment creato
     */
    public static PagedFragment newInstance(int position) {
        PagedFragment pagedFragment = new PagedFragment();
        // nel bundle recuper il numero di tab selezionato
        Bundle arguments = new Bundle();
        arguments.putInt(PAGE_NUM, position);
        pagedFragment.setArguments(arguments);
        return pagedFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paged, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = getArguments();
        Integer pageNum = arguments.getInt(PAGE_NUM);

        TextView textView = (TextView)view.findViewById(R.id.fragment_text);
        textView.append(" " + pageNum);

    }
}
