package com.example.oleksandr.packagemeasure;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Objects;

public class ViewServerResponse extends AppCompatActivity {


    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private TextView serverResponse;
    private String url = "http://188.166.54.21:8000/getlastpackage/";
    private static final String TAG = ViewServerResponse.class.getName();
    private Button getDataButton;
    private Button changeSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_server_response);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = "Hi!";

        // Capture the layout's TextView and set the string as its text
        getDataButton = findViewById(R.id.getDataButton);

        getDataButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v){
                                              getData();
                                          }
                                      }

        );
//
        changeSystem = findViewById(R.id.changeSystemButton);
        changeSystem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                System.out.println(changeSystem.getText().toString());

                TextView message = findViewById(R.id.serverResponse);
                Button changeSystem = findViewById(R.id.changeSystemButton);
                if (Objects.equals(changeSystem.getText().toString(), "cm")) {
                    Double B  = (1/2.54);
                    String a = "inches: " + Double.toString(B);
                    message.setText(a);
                    String c = "in";
                    changeSystem.setText(c);

                } else if (Objects.equals(changeSystem.getText().toString(), "in")) {
                    Double B  = 1.0;
                    String a = "cm: " + Double.toString(B);
                    message.setText(a);
                    String c = "cm";
                    changeSystem.setText(c);
                }
            }
        });
    }

    private void getData() {

        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                serverResponse = (TextView) findViewById(R.id.serverResponse);
                serverResponse.setText(response);
//                Toast.makeText(getApplicationContext(),"Response :" + response, Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i(TAG,"Error :" + error.toString());
            }
        });

        mRequestQueue.add(mStringRequest);
    }
}
