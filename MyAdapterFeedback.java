package com.example.bright_eyed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterFeedback extends RecyclerView.Adapter<MyViewHolderFeed> {

    private Context context;
    private List<DataFeedback> dataList;

    public MyAdapterFeedback(Context context, List<DataFeedback> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderFeed onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_feedback,parent,false);

        return new MyViewHolderFeed(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderFeed holder, int position) {
        holder.recFeed.setText(dataList.get(position).getDataFeedback());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

class MyViewHolderFeed extends RecyclerView.ViewHolder{


    TextView recFeed;
    CardView recCard;

    public MyViewHolderFeed(@NonNull View itemView) {
        super(itemView);
        recFeed=itemView.findViewById(R.id.FeedbackUser);
        recCard=itemView.findViewById(R.id.recfeedback);
    }
}
