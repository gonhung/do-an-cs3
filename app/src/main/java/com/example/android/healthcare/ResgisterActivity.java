package com.example.android.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.healthcare.Model.User;

import java.util.ArrayList;

public class ResgisterActivity extends AppCompatActivity {

    EditText edUsername,edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    private String tableName = "User";
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgister);
//        dbHelper = new Database(this);
        edUsername = findViewById(R.id.editTextLTBFullName);
        edPassword = findViewById(R.id.editTextLTBContac);
        edEmail = findViewById(R.id.editTextLTBAddress);
        edConfirm = findViewById(R.id.editTextLTBPincode);
        btn = findViewById(R.id.buttonAppBack);
        tv = findViewById(R.id.textViewExistingUser);

        database = new Database();



        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResgisterActivity.this,LoginActivity.class));

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString().trim();
                String email = edEmail.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String confirm = edConfirm.getText().toString();

                if(username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill All details",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.compareTo(confirm)==0){
                        if(isValid(password)){
                            User user = new User(username, email, password);
                            database.writeTable(tableName, user);
                            database.register(username, password);
                            Toast.makeText(getApplicationContext(),"Record Inserted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ResgisterActivity.this,LoginActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(),"Password must contain at least 8 characters, having letter, digit and specail symbol",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Password and Confirm password didn't match",Toast.LENGTH_SHORT).show();

                    }
                }

            }

        });
    }
    public static boolean isValid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length() < 8){
            return false;
        }else {
            for (int p =0; p < passwordhere.length(); p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for(int r =0; r < passwordhere.length(); r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s =0; s < passwordhere.length(); s++){
                char c = passwordhere.charAt(s );
                if(c>=35&&c<=46||c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;

            return false;
        }
    }
}