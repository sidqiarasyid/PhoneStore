package com.danta.sidqi.phonestore;

import android.content.Intent;
import android.util.Log;
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

class ReqAdapter extends RecyclerView.Adapter<ReqAdapter.ReqViewHolder> {
    ArrayList<Model> desclist;

    public ReqAdapter(ArrayList<Model> arrayList){
        this.desclist = arrayList;
    }

    @NonNull
    @Override
    public ReqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutinflater = LayoutInflater.from(parent.getContext());
        View view= layoutinflater.inflate(R.layout.item_list,parent,false);
        return new ReqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReqViewHolder holder, int position) {
        Model model = desclist.get(position);
        holder.phone_name.setText(model.getPhoneName());
        Picasso.get()
                .load(model
                        .getImage())
                .resize(100, 150)
                .error(R.drawable.imgphone)
                .into(holder.img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Detail.class);
                intent.putExtra("name", model.getPhoneName());
                intent.putExtra("image", model.getImage());
                intent.putExtra("detail", model.getDetailUrl());
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() { return desclist.size(); }

    public static class ReqViewHolder extends RecyclerView.ViewHolder {
        TextView phone_name;
        ImageView img;
        CardView cardView;

        public ReqViewHolder(@NonNull View itemView) {
            super(itemView);
            phone_name = itemView.findViewById(R.id.tx_phone_name);
            img = itemView.findViewById(R.id.img_phone);
            cardView = itemView.findViewById(R.id.container);
        }

    }
    void setFilter(ArrayList<Model> filterMode) {
        desclist = new ArrayList<>();
        desclist.addAll(filterMode);
        notifyDataSetChanged();
    }
}
