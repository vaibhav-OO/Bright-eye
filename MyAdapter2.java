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

public class MyAdapter2 extends RecyclerView.Adapter<MyViewHolder2> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter2(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_restro,parent,false);

        return new MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {

        Glide.with(context).load(dataList.get(position).getRestroImage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getRestroName());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailRestro.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getRestroImage());
                intent.putExtra("Address",dataList.get(holder.getAdapterPosition()).getRestroAdd());
                intent.putExtra("Name",dataList.get(holder.getAdapterPosition()).getRestroName());
                intent.putExtra("Contact",dataList.get(holder.getAdapterPosition()).getRestroContact());
                intent.putExtra("Timing",dataList.get(holder.getAdapterPosition()).getRestroTiming());
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

class MyViewHolder2 extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recName;
    CardView recCard;

    public MyViewHolder2(@NonNull View itemView) {
        super(itemView);
        recImage=itemView.findViewById(R.id.recImage2);
        recName=itemView.findViewById(R.id.recName2);
        recCard=itemView.findViewById(R.id.recCard2);

    }
}
