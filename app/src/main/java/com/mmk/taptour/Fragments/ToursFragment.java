package com.mmk.taptour.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.mmk.taptour.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToursFragment extends android.support.v4.app.Fragment {


    public ToursFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tours, container, false);

        return view;
    }

}
