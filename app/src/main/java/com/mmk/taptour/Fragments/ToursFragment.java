package com.mmk.taptour.Fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mmk.taptour.Interface.TourClickListener;
import com.mmk.taptour.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import Constans.Constants;
import Data.TourListAdapter;
import Model.Tour;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToursFragment extends android.support.v4.app.Fragment {
    private RecyclerView toursRecylerView;
    private TourListAdapter tourListAdapter;
    private List<Tour>tourList;
    private RequestQueue queue;
    private ProgressBar progressBar;

    public ToursFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tours, container, false);
        setup(view);
        return view;
    }

    public void setup(View v){
        queue= Volley.newRequestQueue(getContext());
        progressBar=v.findViewById(R.id.progressbar);
        toursRecylerView=(RecyclerView)v.findViewById(R.id.toursListRecylerView);
        toursRecylerView.setHasFixedSize(true);
        toursRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tourList=new ArrayList<>();
        tourListAdapter=new TourListAdapter(getActivity(), tourList, new TourClickListener() {
            @Override
            public void onClick(View view, int position, Tour tour) {
               //TODO Tour Clicked
            }
        });
        toursRecylerView.setAdapter(tourListAdapter);
        addToursToList();


    }

    public void addToursToList(){
        JsonArrayRequest jsonArray=new JsonArrayRequest(Request.Method.POST, Constants.TOURLIST_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0;i<response.length();i++){

                            try {
                                JSONObject tourObject=response.getJSONObject(i);
                                Tour tour=new Tour();
                                tour.setId(tourObject.getString(Constants.KEY_TOUR_ID));
                                tour.setName(tourObject.getString(Constants.KEY_TOUR_NAME));
                                tour.setPrice(tourObject.getString(Constants.KEY_TOUR_PRICE));
                                tour.setImageLink(tourObject.getString(Constants.KEY_TOUR_IMAGELINK));
                                tour.setCompanyId(tourObject.getString(Constants.KEY_TOUR_COMPANYID));
                                tourList.add(tour);
                                tourListAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonArray);

    }
}
