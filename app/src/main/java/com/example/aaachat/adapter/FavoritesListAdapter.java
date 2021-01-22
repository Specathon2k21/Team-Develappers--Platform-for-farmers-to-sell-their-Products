package com.example.aaachat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aaachat.R;
import com.example.aaachat.model.FavoritesList;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.Holder> {

    private List<FavoritesList> list;
    private Context context;

    public FavoritesListAdapter(List<FavoritesList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_favorites_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        FavoritesList favoritesList = list.get(position);

        holder.tvName.setText(favoritesList.getUserName());
        holder.tvDesc.setText(favoritesList.getDescription());
        holder.tvDate.setText(favoritesList.getDate());

        Glide.with(context).load(favoritesList.getUrlProfileImage()).into(holder.profileImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private CircleImageView profileImage;
        private TextView tvName, tvDesc, tvDate;

        public Holder(@NonNull View itemView) {
            super(itemView);

            profileImage= itemView.findViewById(R.id.image_profile);
            tvName= itemView.findViewById(R.id.tv_name);
            tvDate= itemView.findViewById(R.id.tv_date);
            tvDesc= itemView.findViewById(R.id.tv_desc);
        }
    }
}
