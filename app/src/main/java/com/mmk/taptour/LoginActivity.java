package com.mmk.taptour;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import Constans.Constants;
import Model.User;

public class LoginActivity extends AppCompatActivity {


    

    private Button loginButton;
    private TextInputEditText usernameEditText,passwordEditText;
    private RequestQueue queue;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setup();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void setup(){
        queue= Volley.newRequestQueue(LoginActivity.this);
        loginButton=(Button)findViewById(R.id.loginButton);
        usernameEditText=(TextInputEditText)findViewById(R.id.usernameInputEditTextLogin);
        passwordEditText=(TextInputEditText)findViewById(R.id.passwordInputEditTextLogin);
        progressDialog=new ProgressDialog(LoginActivity.this);

    }

    public void login() {

        Constants.currentUser=null;
        Constants.companyID=null;

        progressDialog.setMessage(getString(R.string.waitProgressDialogMessage));
        progressDialog.show();
        final String username=usernameEditText.getEditableText().toString();
        final String password=passwordEditText.getEditableText().toString();

        StringRequest data=new StringRequest(Request.Method.POST, Constants.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject resultObject=new JSONObject(response);
                    String result=resultObject.getString(Constants.RESULT_TEXT);
                    if (result.equals(Constants.RESULT_SUCCESS)){
                        //TODO LOGIN IS SUCCESS
                        Toast.makeText(LoginActivity.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                        JSONObject userObject=resultObject.getJSONObject(Constants.KEY_USERTEXT);
                        User user=new User();
                        user.setId(userObject.getString(Constants.KEY_USERID));
                        user.setUsername(userObject.getString(Constants.KEY_USERNAME));
                        user.setPassword(userObject.getString(Constants.KEY_PASSWORD));
                        user.setType(userObject.getString(Constants.KEY_USERTYPE));
                        if (user.getType().equals("company"))
                            Constants.companyID="1";
                        Constants.currentUser=user;
                        progressDialog.dismiss();
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                    else if (result.equals(Constants.RESULT_FAIL)){
                        //TODO LOGIN IS FAIL
                        Toast.makeText(LoginActivity.this,"FAIL",Toast.LENGTH_SHORT).show();


                    }
                    progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }


        }



        )  {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put(Constants.KEY_USERNAME,username);
                params.put(Constants.KEY_PASSWORD,password);

                return params;
            }
        };

        queue.add(data);



    }


}
