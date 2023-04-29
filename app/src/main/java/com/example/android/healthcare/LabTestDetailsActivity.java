package com.example.android.healthcare;

import static com.example.android.healthcare.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackagename , tvTotalCost;
    EditText edDetails;
    Button btnAddToCart,btnBack;

    private String tableName = "cart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_lab_test_details);

         tvPackagename = findViewById(id.textViewBMDPackagename);
        tvTotalCost = findViewById(id.textViewLDTotalCost);
        edDetails = findViewById(id.editTextLDTextMultiLine);
        btnAddToCart = findViewById(id.buttonLDAddToCart);
        btnBack = findViewById(id.buttonLDBack);
//
        ArrayList userModel = new ArrayList<>();
        userModel.add("username");
        userModel.add("product");
        userModel.add("price");
        userModel.add("otype");
//        Database db = new Database(LabTestDetailsActivity.this, userModel, tableName);

         edDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvPackagename.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Toas Cost : "+intent.getStringExtra("text3")+"/- ");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackagename.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());


                ArrayList userModel = new ArrayList<>();
                userModel.add("username");
                userModel.add("product");
                userModel.add("price");
                userModel.add("otype");
//                Database db = new Database(LabTestDetailsActivity.this, userModel, tableName);

//                if(db.checkCart(username, product) == 1){
//                    Toast.makeText(LabTestDetailsActivity.this, "Product already exits", Toast.LENGTH_SHORT).show();
//                }else{
//                    db.addCart(username, product, String.valueOf(price).toString(), "lab");
//                    Toast.makeText(LabTestDetailsActivity.this, "Record inserted to card", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
//
//                }
            }
        });


    }
}