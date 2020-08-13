package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    final String url = "http://192.168.42.233/android/login.php";
    EditText editemail, editpassword;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editemail = findViewById(R.id.editemailLo);
        editpassword = findViewById(R.id.editpasswordLO);
        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editemail.getText().toString();
                final String password = editpassword.getText().toString();

                Toast.makeText(LoginActivity.this, "loading", Toast.LENGTH_SHORT).show();
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject o = new JSONObject(response.toString());
//Handling JSON response

                            JSONArray user = o.getJSONArray("info");

                            JSONObject c = user.getJSONObject(0);

                            final String resultJ = c.getString("result");

                            if (resultJ.equals("success")) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Some error occurred!", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", email);    //send the values from here as sent from postman
                        params.put("password", password);

                        return params;

                    }
                };

                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(request);
            }
        });


    }
}
