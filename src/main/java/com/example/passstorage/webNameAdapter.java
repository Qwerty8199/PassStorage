package com.example.passstorage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class webNameAdapter extends RecyclerView.Adapter<webNameAdapter.ViewHolder> {
    public ArrayList<String> webName;
    public Context con;

    public webNameAdapter(ArrayList<String> webName,Context con) {
        this.webName = webName;
        this.con = con;
    }

    @NonNull
    @Override
    public webNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.indi_web_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull webNameAdapter.ViewHolder holder, int position) {
        final String CurWebName = webName.get(position);
        Log.d("onBind",CurWebName);
        holder.textView.setText(CurWebName);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+CurWebName,Toast.LENGTH_LONG).show();
                Intent i = new Intent(con,ViewTheDamnPass.class);
                i.putExtra("WebName",CurWebName);
                con.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return webName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public RelativeLayout relativeLayout;
        public BreakIterator Passpass;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.indiwebName);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.RlLayout);
        }
    }
}
