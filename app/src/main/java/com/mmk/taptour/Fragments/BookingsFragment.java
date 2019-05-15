package com.mmk.taptour.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mmk.taptour.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Constans.Constants;
import Data.BookingListAdapter;
import Model.Booking;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingsFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView bookingRecylerView;
    private BookingListAdapter adapter;
    private List<Booking>bookingList;
    private RequestQueue requestQueue;



    public BookingsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view=inflater.inflate(R.layout.fragment_bookings, container, false);
        bookingList=new ArrayList<>();
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipeRefresh);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
                if (Constants.companyID!=null)
                    addBookingToList(Constants.companyID);
            }
        });

        bookingRecylerView=(RecyclerView)view.findViewById(R.id.bookingRecylerView);
        bookingRecylerView.setHasFixedSize(true);
        bookingRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (Constants.companyID!=null)
        addBookingToList(Constants.companyID);
        else
            Toast.makeText(getActivity(),"Booking List is empty",Toast.LENGTH_LONG).show();

        adapter=new BookingListAdapter(bookingList,getActivity());


        bookingRecylerView.setAdapter(adapter);


        return view;
    }

    private void addBookingToList(final String companyID) {
        bookingList.clear();
        requestQueue= Volley.newRequestQueue(getActivity());
        Log.d("bookResponse",companyID);

        StringRequest stringRequest=new StringRequest(Request.Method.POST,Constants.BOOKINGSLIST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("bookResponse",response);
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject bookingObject=jsonArray.getJSONObject(i);
                        Booking booking=new Booking();
                        booking.setName(bookingObject.getString("tour_name"));
                        booking.setPrice(bookingObject.getString("tour_price"));
                        booking.setUserId(bookingObject.getString("userId"));
                        bookingList.add(booking);
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.d("bookResponse","333");
                Map<String,String>params=new HashMap<>();
                params.put("companyId",companyID);

                return params;
            }
        };

    requestQueue.add(stringRequest);
    }

}
