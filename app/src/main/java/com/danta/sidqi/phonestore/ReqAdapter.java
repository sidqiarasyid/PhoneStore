package com.danta.sidqi.phonestore;

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
        holder.phone_name.setText(desclist.get(position).getPhoneName());
        Picasso.get()
                .load(desclist.get(position)
                        .getImage())
                .resize(100, 150)
                .error(R.drawable.imgphone)
                .into(holder.img);

    }

    @Override
    public int getItemCount() { return desclist.size(); }

    public static class ReqViewHolder extends RecyclerView.ViewHolder {
        private final TextView phone_name;
        private final ImageView img;
        private final CardView container;

        public ReqViewHolder(@NonNull View itemView) {
            super(itemView);
            phone_name = itemView.findViewById(R.id.tx_phone_name);
            img = itemView.findViewById(R.id.img_phone);
            container = itemView.findViewById(R.id.container);

        }

    }
}
