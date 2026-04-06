package com.example.bright_eyed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyViewHolder1> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter1(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_mall,parent,false);

        return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {

        Glide.with(context).load(dataList.get(position).getDatamallImage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getDatamallName());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailMall.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getDatamallImage());
                intent.putExtra("Address",dataList.get(holder.getAdapterPosition()).getDatamallAdd());
                intent.putExtra("Name",dataList.get(holder.getAdapterPosition()).getDatamallName());
                intent.putExtra("Contact",dataList.get(holder.getAdapterPosition()).getDatamallContact());
                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList=searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder1 extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recName;
    CardView recCard;

    public MyViewHolder1(@NonNull View itemView) {
        super(itemView);
        recImage=itemView.findViewById(R.id.recImage1);
        recName=itemView.findViewById(R.id.recName1);
        recCard=itemView.findViewById(R.id.recCard1);

    }
}
