package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import java.util.List;


import Adapter.AdapterRecycleImage;
import Model.UploadImages;

public class ShowUploadImageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterRecycleImage adapterRecycle;
    ArrayList<UploadImages> arrayList = new ArrayList<>();

    //
//    List<UploadImages> uploadlist;
    String URL = "http://192.168.42.201/android/getAllImages.php";

    //LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_upload_image);
        recyclerView = findViewById(R.id.recyleshowimage);
//        linearLayoutManager= new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        uploadlist= new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowUploadImageActivity.this));
        getdata();
    }


    public void getdata() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
//                        int id = jo.getInt("id");
                        String picture = jo.getString("url");
                        UploadImages uploadImages = new UploadImages();
                        uploadImages.setImages(picture);
                        arrayList.add(uploadImages);
                    }
                    adapterRecycle = new AdapterRecycleImage(ShowUploadImageActivity.this, arrayList);
                    recyclerView.setAdapter(adapterRecycle);

                    JSONObject c = jsonArray.getJSONObject(0);
                    final String resultJ = c.getString("result");

                    if (resultJ.equals("success")) {
                        Toast.makeText(ShowUploadImageActivity.this, " Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ShowUploadImageActivity.this, "Some error occurred!", Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(ShowUploadImageActivity.this);
        rQueue.add(stringRequest);
    }
}


