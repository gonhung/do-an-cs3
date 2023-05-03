package com.example.android.healthcare;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.healthcare.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView tv;
    private Database database;
    private String tableName = "User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login2);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);
        database = new Database();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    String username = edUsername.getText().toString();
                    String password = edPassword.getText().toString();
//                    if(username.length()==0 || password.length()==0){
//                       Toast.makeText(getApplicationContext(),"Please fill All details",Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Query query = database.getmDatabase().child(tableName).orderByChild("username").equalTo(username);
//                        query.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.exists()) {
//                                    // Loop through the matching users
//                                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
//                                        // Get the user object and check the password
//
//                                        User user = userSnapshot.getValue(User.class);
//                                        if (user.getPassword().equals(password)) {
//                                            Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
//                                            SharedPreferences sharedPreferences = getSharedPreferences("share_prefer", Context.MODE_PRIVATE);
//                                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                                            editor.putString("username", username).apply();
//                                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
//                                        }else {
//                                            Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_SHORT).show();
//                                            return;
//                                        }
//                                    }
//                                }else {
//                                    Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_SHORT).show();
//                                    return;
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                // Handle errors
//                                Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        });
//                    }
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