package com.developer.jsonpracticeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.developer.jsonpracticeapplication.adapters.CustomAdapter;
import com.developer.jsonpracticeapplication.databinding.ActivityJsonLinkBinding;
import com.developer.jsonpracticeapplication.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonLinkActivity extends AppCompatActivity {
    ActivityJsonLinkBinding binding;

    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityJsonLinkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setRecyclerView();
        getJsonData();
        handleAdapter();
    }

    private void handleAdapter() {
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(JsonLinkActivity.this, personNames, emailIds, mobileNumbers);
        binding.recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    private void getJsonData() {
        try {
            JSONObject reader = new JSONObject(loadJSONFromAsset());
            //fetch JSONArray named Users
            JSONArray userArray =  reader.getJSONArray("users");

            //Implement for loop for getting userList Data
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userDetail =  userArray.getJSONObject(i);
                // fetch email and name and store it in arraylist
                personNames.add(userDetail.getString("name"));
                personNames.add(userDetail.getString("email"));

                // create a object for getting contact data from JSONObject
                JSONObject contact = userDetail.getJSONObject("contact");
                // fetch mobile number and store it in arraylist
                mobileNumbers.add(contact.getString("mobile"));
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset() {
        String json=null;
        try {
            InputStream is = getAssets().open("sample2.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");

        }catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void setRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(linearLayoutManager);
    }
}