package com.danta.sidqi.phonestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {
    private ArrayList<Model> recList;

    public RecAdapter(ArrayList<Model> consRecList){
        this.recList = consRecList;
    }



    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        Model model = recList.get(position);
        holder.txtNameRec.setText(model.getPhoneName());
        Picasso.get().load(model.getImage())
                .placeholder(R.drawable.imgphone)
                .error(R.drawable.imgphone)
                .resize(100, 150)
                .into(holder.recImg);
    }

    @Override
    public int getItemCount() {
        return (recList != null) ? recList.size() : 0;
    }

    public class RecViewHolder extends RecyclerView.ViewHolder {
        ImageView recImg;
        TextView txtNameRec;
        CardView cardRec;
        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            recImg = itemView.findViewById(R.id.img_phone);
            txtNameRec = itemView.findViewById(R.id.tx_phone_name);
            cardRec = itemView.findViewById(R.id.container);
        }
    }
}
