package com.mmk.taptour;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Constans.Constants;
import Model.Company;
import Model.Tour;
import Model.User;

public class TourDetailActivity extends AppCompatActivity {
    private Toolbar toolbarDetail;
    private ImageView tourImage;
    private TextView tourName;
    private TextView tourPrice;
    private TextView tourCompanyName;
    private Tour tour;
    private Button bookingButton;
    private User currentUser;
    private CardView companyProfileContainer;
    private String companyId="";
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);
        setup();
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingProcess();
            }
        });
        companyProfileContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!companyId.isEmpty()) {
                    Intent intent = new Intent(TourDetailActivity.this, CompanyProfileActivity.class);
                    intent.putExtra(getString(R.string.intentFromTourDetailToCompanyProfile), companyId);
                    startActivity(intent);
                }
            }
        });
    }

    public void setup(){

        toolbarDetail=(Toolbar)findViewById(R.id.toolbarTourDetail);
        setSupportActionBar(toolbarDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        requestQueue= Volley.newRequestQueue(this);

        currentUser= Constants.currentUser;
        tour=(Tour)getIntent().getSerializableExtra(getString(R.string.intentFromTourToTourDetail));
        tourImage=(ImageView)findViewById(R.id.tourImgDetail);
        tourName=(TextView)findViewById(R.id.tourNameDetail);
        tourPrice=(TextView)findViewById(R.id.tourPriceDetail);
        tourCompanyName=(TextView)findViewById(R.id.companyNameTourDetail);
        bookingButton=(Button)findViewById(R.id.bookPlaceDetailButton);
        companyProfileContainer=(CardView)findViewById(R.id.companyContainerTourDetail);

        if (tour!=null){

            tourName.setText(tour.getName());
            Picasso.get().load(tour.getImageLink()).placeholder(R.drawable.taptour_logo).into(tourImage);
            tourPrice.setText("$ "+tour.getPrice());
            tourCompanyName.setText(tour.getCompanyName());
            companyId=tour.getCompanyId();

        }

    }
    public void bookingProcess(){



        if (currentUser==null)
            bookingFail();
        else
            bookingSuccess();

    }

    private void bookingSuccess() {
        final String userId=currentUser.getId();
        final String tourId=tour.getId();
        final String companyId=tour.getCompanyId();
        //TODO get people Number from the user;
        final String numberPeeople="1";

        StringRequest data=new StringRequest(Request.Method.POST, Constants.ADDBOOKINGSLIST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject resultObject=new JSONObject(response);
                            String result=resultObject.getString(Constants.RESULT_TEXT);
                            Log.d("ErrorMessage",result);
                            if (result.equals(Constants.RESULT_SUCCESS)){
                                Toast.makeText(TourDetailActivity.this,R.string.bookingSuccessText,Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(TourDetailActivity.this,R.string.bookingFailText,Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ErrorMessage",error.getMessage());
                Toast.makeText(TourDetailActivity.this,R.string.bookingFailText,Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put(Constants.KEY_BOOKINGS_COMPANYID,companyId);
                params.put(Constants.KEY_BOOKINGS_TOURID,tourId);
                params.put(Constants.KEY_BOOKINGS_USERID,userId);
                params.put(Constants.KEY_BOOKINGS_NUMBERPEOPLE,numberPeeople);

                return params;
            }
        };
        requestQueue.add(data);
    }

    private void bookingFail() {
        AlertDialog.Builder builder=new AlertDialog.Builder(TourDetailActivity.this);
        View view=getLayoutInflater().inflate(R.layout.login_warning_builder_view,null);
        builder.setView(view);
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
        Button loginButton=view.findViewById(R.id.loginWarningButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent =new Intent(TourDetailActivity.this,LoginRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
