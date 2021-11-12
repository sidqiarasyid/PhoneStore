package com.danta.sidqi.phonestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        holder.txtLatePName.setText(lateList.get(position).getLatePhoneName());
        Picasso.get().load(lateList.get(position).getLateImageName()).resize(100, 150).into(holder.imgLate);

    }

    @Override
    public int getItemCount() {
        return lateList.size();
    }

    public static class LateViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLatePName;
        private ImageView imgLate;
        public LateViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLatePName = itemView.findViewById(R.id.tx_phone_name);
            imgLate = itemView.findViewById(R.id.img_phone);

        }
    }
}
