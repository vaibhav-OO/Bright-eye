package com.example.bright_eyed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterUCC extends RecyclerView.Adapter<MyViewHolderUCC> {

    private Context context;
    private List<DataCafeClass> dataList;

    public MyAdapterUCC(Context context, List<DataCafeClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolderUCC onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_coupencafe1,parent,false);

        return new MyViewHolderUCC(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderUCC holder, int position) {
        holder.recDetail.setText(dataList.get(position).getDatacoupendetail());
        holder.recDiscount.setText(dataList.get(position).getDatacoupendiscount());
        holder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Payment.class);
                intent.putExtra("code",dataList.get(holder.getAdapterPosition()).getDatacoupencode());
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

class MyViewHolderUCC extends RecyclerView.ViewHolder{


    TextView recCode,recDetail,recDiscount;
    Button buy;
    CardView recCard;

    public MyViewHolderUCC(@NonNull View itemView) {
        super(itemView);

        buy=itemView.findViewById(R.id.buybtn);
        recDetail=itemView.findViewById(R.id.coupendetail1);
        recDiscount=itemView.findViewById(R.id.coupendiscount1);
        recCard=itemView.findViewById(R.id.recCardcoupen1);
    }
}
