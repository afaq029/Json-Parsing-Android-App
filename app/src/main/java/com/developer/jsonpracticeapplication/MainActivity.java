package com.developer.jsonpracticeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.developer.jsonpracticeapplication.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String JSON_STRING = "{\"employee\":{\"name\":\"Afaq Toufeeq\",\"salary\":65000}}";
    String name, salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            //get JsonObject from file
            JSONObject reader = new JSONObject(JSON_STRING);
            // fetch JSONObject named employee
            JSONObject employee = reader.getJSONObject("employee");
            // get employee name and salary
            name= employee.getString("name");
            salary= employee.getString("salary");

            //Set Views
            binding.name.setText(name);
            binding.salary.setText(salary);
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        binding.name.setOnClickListener(v-> startActivity(new Intent(getApplicationContext(),JsonLinkActivity.class)));
        binding.salary.setOnClickListener(v-> startActivity(new Intent(getApplicationContext(),JsonLinkActivity.class)));
    }
}