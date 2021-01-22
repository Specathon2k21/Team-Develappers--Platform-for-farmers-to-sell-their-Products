package com.example.aaachat.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aaachat.R;
import com.example.aaachat.adapter.FavoritesListAdapter;
import com.example.aaachat.adapter.ItemListAdapter;
import com.example.aaachat.model.FavoritesList;
import com.example.aaachat.model.ItemList;

import java.util.ArrayList;
import java.util.List;


public class AllItemsFragment extends Fragment {


    public AllItemsFragment() {
        // Required empty public constructor
    }

    private int []arr;
    private List<ItemList> list = new ArrayList<>();
    private RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getChatList();

    }


    private void getChatList() {
        //list.add("","");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_items, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setAdapter(new ItemListAdapter(list, this.getContext(),arr));
        return view;
    }
}