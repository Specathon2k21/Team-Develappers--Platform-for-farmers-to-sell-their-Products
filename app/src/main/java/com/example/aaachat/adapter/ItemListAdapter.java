package com.example.aaachat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aaachat.R;
import com.example.aaachat.model.ItemList;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.Holder> {

    private  List<ItemList> list;
    private Context context;

    public ItemListAdapter(List<ItemList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.itemName.setText(list.get(position).getItemName());
        Glide.with(context).load(list.get(position).getImageProfile()).into(holder.profileImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView profileImage;
        private TextView itemName;

        public Holder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
        }
    }
}
