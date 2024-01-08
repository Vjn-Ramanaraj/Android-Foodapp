package com.example.resto;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class menu extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView=findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.search);
        searchView.clearFocus();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    startActivity(new Intent(menu.this, Home.class));
                    // Handle Home menu item click
                    return true;
                } else if (itemId == R.id.add) {
                    startActivity(new Intent(menu.this, upload.class));
                    return true;
                } else if (itemId == R.id.cart) {
//                     Handle Add to Cart menu item click
                    startActivity(new Intent(menu.this, Cart.class));
                    return true;
                }
//                else if (itemId == R.id.navsearch) {
//                    // Handle Search menu item click
//                    startActivity(new Intent(menu.this, menu.class));
//                    return true;
//                }

                return false;
            }
        });


        GridLayoutManager gridLayoutManager = new GridLayoutManager(menu.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);


        AlertDialog.Builder builder=new AlertDialog.Builder(menu.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog= builder.create();
        dialog.show();

        dataList =new ArrayList<>();




      adapter=new MyAdapter(menu.this,dataList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("FoodDB");
        dialog.show();

        eventListener=databaseReference.orderByChild("dataCategories").equalTo(Category.Status).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass=itemSnapshot.getValue(DataClass.class);

                   dataClass.setKey(itemSnapshot.getKey());


                    dataList.add(dataClass);

                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            public boolean onQueryTextSubmit(String query){
                return false;
            }

            public boolean onQueryTextChange(String newText){
               searchList(newText);

                return true;
            }
        });

    }
    public void searchList(String text) {
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass : dataList) {
            if (dataClass.getDataName().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);


    }}

