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

public class MyAdapter3 extends RecyclerView.Adapter<MyViewHolder3> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter3(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_hospital,parent,false);

        return new MyViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder3 holder, int position) {

        Glide.with(context).load(dataList.get(position).getHospitalImage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getHospitalName());
        holder.recRating.setText(dataList.get(position).getHospitalStar());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailHospital.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getHospitalImage());
                intent.putExtra("Address",dataList.get(holder.getAdapterPosition()).getHospitalAdd());
                intent.putExtra("Name",dataList.get(holder.getAdapterPosition()).getHospitalName());
                intent.putExtra("Contact",dataList.get(holder.getAdapterPosition()).getHospitalContact());
                intent.putExtra("Timing",dataList.get(holder.getAdapterPosition()).getHospitalTiming());
                intent.putExtra("Rating",dataList.get(holder.getAdapterPosition()).getHospitalStar());
                intent.putExtra("Sector",dataList.get(holder.getAdapterPosition()).getHospitalSector());
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

class MyViewHolder3 extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recName,recRating;
    CardView recCard;

    public MyViewHolder3(@NonNull View itemView) {
        super(itemView);
        recImage=itemView.findViewById(R.id.recImage3);
        recName=itemView.findViewById(R.id.recName3);
        recCard=itemView.findViewById(R.id.recCard3);
        recRating=itemView.findViewById(R.id.recRating3);
    }
}
