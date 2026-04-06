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

public class MyAdapter4 extends RecyclerView.Adapter<MyViewHolder4> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter4(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_news,parent,false);

        return new MyViewHolder4(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder4 holder, int position) {

        Glide.with(context).load(dataList.get(position).getNewsImage()).into(holder.recImage);
        holder.recName.setText(dataList.get(position).getNewsName());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailNews.class);
                intent.putExtra("Image",dataList.get(holder.getAdapterPosition()).getNewsImage());
                intent.putExtra("Address",dataList.get(holder.getAdapterPosition()).getNewsDetail());
                intent.putExtra("Name",dataList.get(holder.getAdapterPosition()).getNewsName());
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

class MyViewHolder4 extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recName;
    CardView recCard;

    public MyViewHolder4(@NonNull View itemView) {
        super(itemView);
        recImage=itemView.findViewById(R.id.recImage4);
        recName=itemView.findViewById(R.id.recName4);
        recCard=itemView.findViewById(R.id.recCard4);
    }
}
