package com.example.passstorage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class webPassAdapter extends RecyclerView.Adapter<webPassAdapter.ViewHolder> {

    public ArrayList<PassClass> CurPass;
    public Context con;

    public webPassAdapter(ArrayList<PassClass> curPass,Context con) {
        CurPass = curPass;
        this.con = con;
    }


    @NonNull
    @Override
    public webPassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.indi_pass_layout, parent, false);
        webPassAdapter.ViewHolder viewHolder = new webPassAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull webPassAdapter.ViewHolder holder, int position) {
        final PassClass curWebPass = CurPass.get(position);
        holder.Passpass.setText(curWebPass.getPassWord());
        holder.PassUser.setText(curWebPass.getUserName());
        holder.Passemail.setText(curWebPass.getEmail());
        Log.d("Checking indi",holder.PassUser.getText().toString());
        holder.but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = holder.Passpass.getText().toString();
                try{
                    String _temp = new String(Base64.decode(temp.getBytes(),Base64.DEFAULT));
                    holder.Passpass.setText(_temp);
                }
                catch (IllegalArgumentException e){
                    Toast.makeText(con, "XXX", Toast.LENGTH_SHORT).show();
                    holder.Passpass.setText(temp);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return CurPass.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView PassUser,Passemail,Passpass;
        public Button but;
        public RelativeLayout rl;
        public ViewHolder(View it){
            super(it);
            Passpass = (TextView)it.findViewById(R.id.inidpass);
            Passemail = (TextView)it.findViewById(R.id.indiemail);
            PassUser = (TextView)it.findViewById(R.id.indiUser);
            but = (Button)it.findViewById(R.id.butdecrypt);
            rl = (RelativeLayout)it.findViewById(R.id.passrl);
        }
    }
}
