package com.example.resto;

import static android.content.ContentValues.TAG;

import static com.example.resto.Basket.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class details extends AppCompatActivity {

    TextView detailDesc, detailName, detailPrice, detailCategory;
    ImageView detailImage;
    String key = "";
    String imageURL = "";

    Uri uri;


    Button cartbutton, deleteButton, editButton;

    Basket basket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailDesc = findViewById(R.id.detailDesc);
        detailName = findViewById(R.id.detailTitle);
        detailPrice = findViewById(R.id.detailPrice);
        detailCategory = findViewById(R.id.detailCategory);
        detailImage = findViewById(R.id.detailImage);
        deleteButton = findViewById(R.id.delete_btn);
        editButton = findViewById(R.id.edit_btn);
        cartbutton = findViewById(R.id.addtocart_button);

       basket=new Basket();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailDesc.setText(bundle.getString("Description"));
            detailName.setText(bundle.getString("Title"));
            detailPrice.setText(bundle.getString("Price"));
            detailCategory.setText(bundle.getString("Category"));
            key = bundle.getString("key");
            imageURL = bundle.getString("Image");

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("FoodDB");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(imageURL);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("FoodDB");
                        reference.child(String.valueOf(key)).removeValue();
                        Toast.makeText(details.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), menu.class));
                        finish();
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(details.this, Update.class);
                intent.putExtra("Image", imageURL);
                intent.putExtra("Title", detailName.getText().toString());
                intent.putExtra("Description", detailDesc.getText().toString());
                intent.putExtra("Price", detailPrice.getText().toString());
                intent.putExtra("Category", detailCategory.getText().toString());
                intent.putExtra("Key", key);
                startActivity(intent);
            }
        });

        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddtocart();
                basket.addtotal(Double.parseDouble(String.valueOf(detailPrice)));
                //basket.delete(Double.parseDouble(String.valueOf(detailPrice)));


            }
        });

    }

    public void loadAddtocart() {
        String Name = detailName.getText().toString();
        String desc = detailDesc.getText().toString();
        String price = detailPrice.getText().toString();
        String Category = detailCategory.getText().toString();
        DataClass dataClass = new DataClass(Name, desc, price, imageURL, Category);



        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Cart").child(currentDate)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(details.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(details.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}

