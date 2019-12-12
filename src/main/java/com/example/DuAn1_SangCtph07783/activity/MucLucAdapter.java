package com.example.DuAn1_SangCtph07783.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.DuAn1_SangCtph07783.R;
import com.example.DuAn1_SangCtph07783.moder.MucLuc;
import com.example.DuAn1_SangCtph07783.moder.ThongTin;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MucLucAdapter extends RecyclerView.Adapter<MucLucAdapter.MyRecycleview> {
private Context mContext;
private List<MucLuc> mucLucList;
private ThongTin thongTin;
    public MucLucAdapter(Context mContext, List<MucLuc> mucLucList) {
        this.mContext = mContext;
        this.mucLucList = mucLucList;
    }

    @NonNull
    @Override
    public MyRecycleview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.row_mucluc,parent,false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActShow.class);
                mContext.startActivity(intent);
            }
        });

        return new MyRecycleview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleview holder, final int position) {


        holder.tv_NameMuc.setText(mucLucList.get(position).getNamemuc());
        holder.tv_Title.setText(mucLucList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mucLucList.get(position).getNoidung();
                Intent intent = new Intent(mContext, ActShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("position", String.valueOf(mucLucList.get(position).getIdCT()));
                bundle.putString("stt", String.valueOf(mucLucList.get(position).getStt()));
                bundle.putString("idName", String.valueOf(mucLucList.get(position).getIdNameCT()));
                bundle.putString("text",text);
                bundle.putString("title",mucLucList.get(position).getTitle());
                intent.putExtra("show",bundle);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mucLucList.size();
    }

    public class MyRecycleview extends RecyclerView.ViewHolder{
        TextView tv_NameMuc, tv_Title;



        public MyRecycleview(@NonNull View itemView) {
            super(itemView);

            tv_NameMuc = (TextView)  itemView.findViewById(R.id.tvNameMuc);
            tv_Title = (TextView)  itemView.findViewById(R.id.tvTitleMuc);



        }
    }

}
