package com.example.android.healthcare;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView tv;
    private String tableName = "User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login2);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//                String username = edUsername.getText().toString();
//                String password = edPassword.getText().toString();
//                ArrayList userModel = new ArrayList<>();
//                userModel.add("username");
//                userModel.add("password");
//                Database db = new Database(LoginActivity.this, userModel, tableName);
//                if(username.length()==0 || password.length()==0){
//                    Toast.makeText(getApplicationContext(),"Please fill All details",Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    if(db.login(username,password)==1){
//                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
//                        SharedPreferences sharedPreferences = getSharedPreferences("share_prefer", Context.MODE_PRIVATE);
//                        SharedPreferences sharedpreferences;
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("username", username);
//                        editor.apply();
//                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//                    }else {
//                        Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_SHORT).show();
//                    }
//
//                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ResgisterActivity.class));

            }
        });
    }

}