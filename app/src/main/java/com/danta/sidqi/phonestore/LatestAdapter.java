package com.danta.sidqi.phonestore;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.LateViewHolder> {
    ArrayList<Model> lateList;

    public LatestAdapter(ArrayList<Model> lateList){
        this.lateList = lateList;
    }
    @NonNull
    @Override

    public LateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent,false);
        return new LateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LateViewHolder holder, int position) {
        Model del = lateList.get(position);
        holder.txtLatePName.setText(del.getLatePhoneName());
        Picasso.get().load(del.getLateImageName()).resize(100, 150).into(holder.imgLate);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Detail.class);
                intent.putExtra("name", del.getLatePhoneName());
                intent.putExtra("image", del.getLateImageName());
                intent.putExtra("detail", del.getLateUrl());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lateList.size();
    }

    public static class LateViewHolder extends RecyclerView.ViewHolder {
        TextView txtLatePName;
        ImageView imgLate;
        CardView card;

        public LateViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLatePName = itemView.findViewById(R.id.tx_phone_name);
            imgLate = itemView.findViewById(R.id.img_phone);
            card = itemView.findViewById(R.id.container);


        }
    }
}
