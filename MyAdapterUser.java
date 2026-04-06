package com.example.bright_eyed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterUser extends RecyclerView.Adapter<MyAdapterUser.MyViewHolderU> {

    Context context;
    ArrayList<Userbright> userbrightArrayList;

    public MyAdapterUser(Context context, ArrayList<Userbright> userbrightArrayList) {
        this.context = context;
        this.userbrightArrayList = userbrightArrayList;
    }

    @NonNull
    @Override
    public MyAdapterUser.MyViewHolderU onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.itemuser,parent,false);
        return new MyViewHolderU(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterUser.MyViewHolderU holder, int position) {

        Userbright userbright=userbrightArrayList.get(position);

        holder.Name.setText(userbright.Name);
        holder.Email.setText(userbright.Email);
        holder.Mbno.setText(userbright.Number);
        holder.Address.setText(userbright.Address);
    }

    @Override
    public int getItemCount() {
        return userbrightArrayList.size();
    }
    public static class MyViewHolderU extends RecyclerView.ViewHolder{

        TextView Email,Address,Name,Mbno;
        public MyViewHolderU(@NonNull View itemView) {
            super(itemView);

            Mbno=itemView.findViewById(R.id.UserMbno);
            Email=itemView.findViewById(R.id.Useremail);
            Address=itemView.findViewById(R.id.Useradd);
            Name=itemView.findViewById(R.id.Username);
        }
    }
}
