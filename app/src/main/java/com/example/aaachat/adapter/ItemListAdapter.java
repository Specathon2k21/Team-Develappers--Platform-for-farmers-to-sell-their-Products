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
    int []arr;

    public ItemListAdapter(List<ItemList> list, Context context, int[] arr) {
        this.list = list;
        this.context = context;
        this.arr = arr;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        ItemList itemList = list.get(position);
        holder.profileImage.setImageResource(arr[position]);
        holder.itemName.setText(itemList.getItemName());
        //

    }

    @Override
    public int getItemCount() {
        return arr.length;
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
