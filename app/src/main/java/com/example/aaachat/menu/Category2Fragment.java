package com.example.aaachat.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aaachat.R;
import com.example.aaachat.adapter.ItemListAdapter;
import com.example.aaachat.model.ItemList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Category2Fragment extends Fragment {

    private List<ItemList> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private DatabaseReference ref= FirebaseDatabase.getInstance().getReference();

    public Category2Fragment() {
        Query query=ref.child("cereal");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot: snapshot.getChildren()){
                    ItemList item=new ItemList(datasnapshot.child("name").getValue().toString(),datasnapshot.child("image").getValue().toString());
                    list.add(item);
                }
//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_items, container, false);

        recyclerView=view.findViewById(R.id.recyclerView);
        ItemListAdapter adapter=new ItemListAdapter(list,this.getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2, GridLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        return view;
    }
}