package com.developer.jsonpracticeapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.jsonpracticeapplication.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<String> personNames;
    ArrayList<String> emailIds;
    ArrayList<String> mobileNumbers;
    Context context;


    public CustomAdapter(Context context, ArrayList<String> personNames, ArrayList<String> emailIds, ArrayList<String> mobileNumbers) {
        this.context = context;
        this.personNames = personNames;
        this.emailIds = emailIds;
        this.mobileNumbers = mobileNumbers;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        // set the data in items
        holder.name.setText(personNames.get(position));
        holder.email.setText(emailIds.get(position));
        holder.mobileNo.setText(mobileNumbers.get(position));

        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(view -> {
            // display a toast with person name on item click
            Toast.makeText(context, personNames.get(position), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return personNames.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, mobileNo;// init the item view's

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            mobileNo = (TextView) itemView.findViewById(R.id.mobileNo);
        }
    }
}
