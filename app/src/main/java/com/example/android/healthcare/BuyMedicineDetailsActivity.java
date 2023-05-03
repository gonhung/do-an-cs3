package com.example.android.healthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.healthcare.Model.Cart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnBack, btnAddToCart;
    private  String tablename = "cart";
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName = findViewById(R.id.textViewBMDPackagename);
        tvTotalCost = findViewById(R.id.textViewBMDTotalCost);
        edDetails = findViewById(R.id.editTextTextBMDMultiLine);
        btnBack = findViewById(R.id.buttonBMDBack);
        btnAddToCart = findViewById(R.id.buttonBMDAddToCart);
        edDetails.setKeyListener(null);
        database = new Database();
        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+intent.getStringExtra("text3"));

//        ArrayList cartModel = new ArrayList<>();
//        cartModel.add("username");
//        cartModel.add("product");
//        cartModel.add("price");
//        cartModel.add("otype");
//
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("share_prefer", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", null).toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());
                database.getmDatabase().child("cart").addValueEventListener(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int productFound = 0;
//                                if(!snapshot.hasChild("cart")){
//                                    Cart cart = new Cart(username, product, price, "medicine");
//                                    database.writeTable(tablename, cart);
//                                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
////                                    return;
//                                }
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    Cart cart = dataSnapshot.getValue(Cart.class);
                                    Log.d("database carr", "data: "+ cart.getProduct());
                                    if(cart.getProduct().equals(product)){
                                        Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                                        productFound = 1;
                                        Log.d("database carr", "productFound in loop: "+ productFound);

                                        return;
                                    }
                                }
                                Log.d("database carr", "productFound: "+ productFound);

                                if (productFound == 0) {
                                    database.addCart(username, product, price, "medicine");
                                    Toast.makeText(getApplicationContext(), "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        }
                );
            }
        });
    }

}