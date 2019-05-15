package com.mmk.taptour.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mmk.taptour.Interface.LikeClickListener;
import com.mmk.taptour.Interface.TourClickListener;
import com.mmk.taptour.LoginRegisterActivity;
import com.mmk.taptour.R;
import com.mmk.taptour.TourDetailActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Constans.Constants;
import Data.TourListAdapter;
import Model.Tour;
import Model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends android.support.v4.app.Fragment {

    private RecyclerView toursRecylerView;
    private TourListAdapter tourListAdapter;
    private List<Tour> tourList;
    private RequestQueue queue;
    private ProgressBar progressBar;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_favorites, container, false);
        setup(view);

        return view;
    }



    public void setup(View v){
        queue= Volley.newRequestQueue(getContext());
        progressBar=v.findViewById(R.id.progressbarFavfragment);
        toursRecylerView=(RecyclerView)v.findViewById(R.id.favoriteToursListRecylerView);
        toursRecylerView.setHasFixedSize(true);
        toursRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tourList=new ArrayList<>();

        tourListAdapter=new TourListAdapter(getActivity(), tourList, new TourClickListener() {


            @Override
            public void onClick(View view, int position, Tour tour) {
                //TODO Tour Clicked
                tourClicked(tour);
            }

            //TODO LIKE BUTTON Clicked
        }, new LikeClickListener() {
            @Override
            public boolean onClick(Tour tour, boolean isFavourite) {
                User currentUser= Constants.currentUser;
                if (isFavourite) {

                    if (currentUser != null) {
                        //TODO ADD TO FAVORITES
                        addFavoritesSuccess(tour);
                        return true;
                    } else {
                        addFavoritesFail();
                        return false;
                    }
                }
                else {

                    if (currentUser != null) {
                        //TODO REMOVE FROM FAVORITES
                        removeFavoritesSuccess();
                        return true;
                    } else {
                        removeFavoritesFail();
                        return false;
                    }

                }
            }
        });


        toursRecylerView.setAdapter(tourListAdapter);
        addToursToList();


    }

    private void removeFavoritesFail() {
        addFavoritesFail();
    }

    private void removeFavoritesSuccess() {

    }

    //Add Favorites
    private void addFavoritesSuccess(final Tour tour) {
        StringRequest data=new StringRequest(Request.Method.POST, Constants.FAVORITETOURLIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put(Constants.KEY_FAVORITES_TOURID,tour.getId());
                params.put(Constants.KEY_FAVORITES_USERID,Constants.currentUser.getId());

                return params;
            }
        };
        queue.add(data);
    }

    private void addFavoritesFail() {

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view=getLayoutInflater().inflate(R.layout.login_warning_builder_view,null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
        Button loginButton=view.findViewById(R.id.loginWarningButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent =new Intent(getContext(),LoginRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addToursToList(){

        StringRequest data=new StringRequest(Request.Method.POST, Constants.FAVORITETOURLIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray data=new JSONArray(response);
                            for (int i=0;i<data.length();i++){

                                try {
                                    JSONObject tourObject=data.getJSONObject(i);
                                    Tour tour=new Tour();
                                    tour.setId(tourObject.getString(Constants.KEY_TOUR_ID));
                                    tour.setName(tourObject.getString(Constants.KEY_TOUR_NAME));
                                    tour.setPrice(tourObject.getString(Constants.KEY_TOUR_PRICE));
                                    tour.setImageLink(tourObject.getString(Constants.KEY_TOUR_IMAGELINK));
                                    tour.setCompanyId(tourObject.getString(Constants.KEY_TOUR_COMPANYID));
                                    tour.setCompanyName(tourObject.getString(Constants.KEY_TOUR_COMPANYNAME));
                                    tourList.add(tour);
                                    tourListAdapter.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            progressBar.setVisibility(View.GONE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String>params=new HashMap<>();
                params.put(Constants.KEY_FAVORITES_USERID,Constants.currentUser.getId());
                return params;
            }
        };



        queue.add(data);

    }
    public void tourClicked(Tour clickedTour){
        Intent intent=new Intent(getContext(), TourDetailActivity.class);
        intent.putExtra(getString(R.string.intentFromTourToTourDetail),clickedTour);
        startActivity(intent);
    }


}
