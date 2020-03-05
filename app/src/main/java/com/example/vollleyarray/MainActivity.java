package com.example.vollleyarray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vollleyarray.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    String url="http://androindian.com/apps/blog_links/api.php";
    ArrayList<String> IDArray=new ArrayList<String>();
    ArrayList<String> urlArray=new ArrayList<String>();
    ArrayList<String> titleArray=new ArrayList<String>();
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        requestQueue= Volley.newRequestQueue(MainActivity.this);


        final JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("action","get_all_links");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String res=response.getString("response");
                            if(res.equalsIgnoreCase("success")){

                                JSONArray jsonArray=response.getJSONArray("data");

                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object=jsonArray.getJSONObject(i);

                                    String id=object.getString("id");
                                    IDArray.add(id);

                                    String title=object.getString("title");
                                    titleArray.add(title);

                                    String jurl=object.getString("url");
                                    urlArray.add(jurl);
                                }

                                //rec

                                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this,
                                        LinearLayoutManager.VERTICAL,false);
                                binding.rec.setLayoutManager(linearLayoutManager);
                                binding.rec.setAdapter(new ApplistAdp(MainActivity.this,
                                        IDArray,titleArray,urlArray));

                            }else {
                                Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }

        );
        requestQueue.add(jsonObjectRequest);

    }
}
