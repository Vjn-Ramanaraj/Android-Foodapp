package com.example.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class paymentSuccess extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.home) {
                    startActivity(new Intent(paymentSuccess.this, Home.class));
                    // Handle Home menu item click
                    return true;
                } else if (itemId == R.id.add) {
                    startActivity(new Intent(paymentSuccess.this, upload.class));
                    return true;
                } else if (itemId == R.id.cart) {
                    // Handle Add to Cart menu item click
                    startActivity(new Intent(paymentSuccess.this, Cart.class));
                    return true;
                }
//                else if (itemId == R.id.navsearch) {
//                    // Handle Search menu item click
//                    startActivity(new Intent(paymentSuccess.this, menu.class));
//                    return true;
             //   }

                return false;
            }
        });

    }
}