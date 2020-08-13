package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import Adapter.NewsAdapter;
import Model.TechCrunch;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
private static final String URL="https://api.github.com/users";

    StringBuilder builder;
    FetchNewsTask task;

    ArrayList<TechCrunch> newsList;
    NewsAdapter adapter;

    ListView listView;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.techview);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait...");

        task = new FetchNewsTask();
        task.execute();



        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("code ",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
            }
        }


        );


        RequestQueue requestQueue= Volley.newRequestQueue( this);
        requestQueue.add(request);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TechCrunch news = newsList.get(position);
        Intent intent = new Intent(MainActivity.this, DetailActvity.class);
        intent.putExtra("keyUrl", news.url);
        startActivity(intent);


    }
    class FetchNewsTask extends AsyncTask {


        protected Object doInBackground(Object[] objects) {

            try{


                java.net.URL url = new URL("https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=31c21508fad64116acd229c10ac11e84");
                // URL url = new URL("https://newsapi.org/v2/top-headlines?country=in&apiKey=31c21508fad64116acd229c10ac11e84");

                URLConnection connection = url.openConnection();

                InputStream inputStream = connection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line ="";

                builder = new StringBuilder();

                while((line = reader.readLine()) !=null){
                    builder.append(line);
                }

                // We now need to display the data in Toast or Log

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }


        protected void onPreExecute() {

            progressDialog.show();
        }


        protected void onPostExecute(Object o) {
            //Toast.makeText(getApplicationContext(), "Response:"+builder.toString(), Toast.LENGTH_LONG).show();
            Log.i("TechCrunchNewsActivity", builder.toString());

            parseJSONResponse();
        }
    }

    // 2. Extract meaningful or required data from JSON Data
    void parseJSONResponse(){

        try{

            // Create JSON Object from JSON Response
            JSONObject jObj = new JSONObject(builder.toString());
            JSONArray array = jObj.getJSONArray("articles");

            newsList = new ArrayList<>();

            for(int i=0; i<array.length();i++){
                JSONObject obj = array.getJSONObject(i);

                /*String author = obj.getString("author");
                String title = obj.getString("title");
                String urlToImage = obj.getString("urlToImage");
                String publishedAt = obj.getString("publishedAt");*/

                // 3. Meaningful data from JSON Data is represented as an Object in Java
                TechCrunch news = new TechCrunch();

                news.author= obj.getString("author");
                news.title = obj.getString("title");
                news.url = obj.getString("url");
                news.urlToImage = obj.getString("urlToImage");
                news.publishedAt = obj.getString("publishedAt");

                newsList.add(news);

                Log.i("TechCrunchNewsActivity", news.toString());
            }

            adapter = new NewsAdapter(MainActivity.this,R.layout.list_item,newsList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(MainActivity.this);
            progressDialog.dismiss();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

