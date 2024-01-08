package com.example.resto;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;

public class Home extends AppCompatActivity {
    CardView fastfood, maindish, dessert;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fastfood = findViewById(R.id.fastfoodCard);
        maindish = findViewById(R.id.maindishCard);
        dessert = findViewById(R.id.dessertCard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    startActivity(new Intent(Home.this, Home.class));
                    // Handle Home menu item click
                    return true;
                } else if (itemId == R.id.add) {
                    startActivity(new Intent(Home.this, upload.class));
                    return true;
                } else if (itemId == R.id.cart) {
                    // Handle Add to Cart menu item click
                    startActivity(new Intent(Home.this, Cart.class));
                    return true;
                }
//                else if (itemId == R.id.navsearch) {
//                    // Handle Search menu item click
//                    startActivity(new Intent(Home.this, menu.class));
//                    return true;
//                }

                return false;
            }
            });


        fastfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category=new Category();
                category.setStatus("Fastfood");
                intent = new Intent(Home.this, menu.class);
                startActivity(intent);
                Log.d(TAG, "start  menu");

            }
        });

        maindish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category=new Category();
                category.setStatus("MainDish");
                intent = new Intent(Home.this, menu.class);
                startActivity(intent);
                Log.d(TAG, "start  menu");
            }
        });

        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category=new Category();
                category.setStatus("Dessert");
                intent = new Intent(Home.this, menu.class);
                startActivity(intent);
                Log.d(TAG, "start  menu");
            }
        });
    }
}
