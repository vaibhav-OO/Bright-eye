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

public class MyAdapterCC extends RecyclerView.Adapter<MyViewHolderCC> {

    private Context context;
    private List<DataCafeClass> dataList;

    public MyAdapterCC(Context context, List<DataCafeClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderCC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_coupencafe,parent,false);

        return new MyViewHolderCC(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCC holder, int position) {
        holder.recCode.setText(dataList.get(position).getDatacoupencode());
        holder.recDetail.setText(dataList.get(position).getDatacoupendetail());
        holder.recDiscount.setText(dataList.get(position).getDatacoupendiscount());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Detailcoupencafe.class);
                intent.putExtra("code",dataList.get(holder.getAdapterPosition()).getDatacoupencode());
                intent.putExtra("detail",dataList.get(holder.getAdapterPosition()).getDatacoupendetail());
                intent.putExtra("discount",dataList.get(holder.getAdapterPosition()).getDatacoupendiscount());
                intent.putExtra("key",dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

class MyViewHolderCC extends RecyclerView.ViewHolder{


    TextView recCode,recDetail,recDiscount;
    CardView recCard;

    public MyViewHolderCC(@NonNull View itemView) {
        super(itemView);
        recCode=itemView.findViewById(R.id.coupencode);
        recDetail=itemView.findViewById(R.id.coupendetail);
        recDiscount=itemView.findViewById(R.id.coupendiscount);
        recCard=itemView.findViewById(R.id.recCardcoupen);
    }
}
