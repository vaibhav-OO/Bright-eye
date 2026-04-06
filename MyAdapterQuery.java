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

public class MyAdapterQuery extends RecyclerView.Adapter<MyViewHolderQue> {

    private Context context;
    private List<DataQuery> dataList;

    public MyAdapterQuery(Context context, List<DataQuery> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderQue onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_query,parent,false);

        return new MyViewHolderQue(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderQue holder, int position) {
        holder.recQuery.setText(dataList.get(position).getDataQuery());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

class MyViewHolderQue extends RecyclerView.ViewHolder{


    TextView recQuery;
    CardView recCard;

    public MyViewHolderQue(@NonNull View itemView) {
        super(itemView);
        recQuery=itemView.findViewById(R.id.QueryUser);
        recCard=itemView.findViewById(R.id.recquery);
    }
}
