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

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_cafe,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getDataName());
        holder.recRating.setText(dataList.get(position).getDataRating());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailCafe.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Address",dataList.get(holder.getAdapterPosition()).getDataAddress());
                intent.putExtra("Name",dataList.get(holder.getAdapterPosition()).getDataName());
                intent.putExtra("Contact",dataList.get(holder.getAdapterPosition()).getDataContact());
                intent.putExtra("Timing",dataList.get(holder.getAdapterPosition()).getDataTiming());
                intent.putExtra("Rating",dataList.get(holder.getAdapterPosition()).getDataRating());

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

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recName,recRating;
    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        recImage=itemView.findViewById(R.id.recImage);
        recName=itemView.findViewById(R.id.recName);
        recRating=itemView.findViewById(R.id.recRating);
        recCard=itemView.findViewById(R.id.recCard);
    }
}