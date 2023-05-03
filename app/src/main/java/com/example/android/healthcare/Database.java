package com.example.android.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.MutableInt;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android.healthcare.Model.Cart;
import com.example.android.healthcare.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public Database() {
        // Get a reference to the database service
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
    }

    public void writeTable(String tableName, Object data) {

        mDatabase.child(tableName.toString()).push().setValue(data);
    }

    public  void  register(String username, String password){
        mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful()) {
                    return;
                }

            }
        });
    }

    public DatabaseReference getmDatabase() {
        return mDatabase;
    }

    public void addCart(String username, String product, float price, String  otype){
        Cart cart = new Cart(username, product, price, otype);
        this.writeTable("cart", cart);
    }

//    public void removeCart(String usrname, String otype){
//        String str[] = new String[2];
//        str[0] = usrname;
//        str[1] = otype;
//        SQLiteDatabase db = getReadableDatabase();
//        db.delete("cart", "username = ? and  otype = ?",str);
//        db.close();
//    }
//

    public ArrayList getCartDate(String tableName, String username, String ptype){
        ArrayList<Cart> arr  = new ArrayList<>();
         mDatabase.child(tableName).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Cart cart = dataSnapshot.getValue(Cart.class);
                            if(cart.getUsername().equals(username) && cart.getOtype().equals(ptype)){
                                Log.d("in here", "Data:   "+ cart.getUsername());
                                Cart cartIt = new Cart(cart.getUsername(), cart.getProduct(), cart.getPrice(),cart.getOtype());

                                arr.add(cartIt);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );

        return arr;
    }
//
//     public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype){
//        ContentValues cv = new ContentValues();
//        cv.put("username", username);
//        cv.put("fullname", fullname);
//        cv.put("address", address);
//        cv.put("contactno", contact);
//        cv.put("pincode",pincode);
//        cv.put("date", date);
//        cv.put("time",time);
//        cv.put("amount", price);
//        cv.put("otype", otype);
//        SQLiteDatabase db =  getWritableDatabase();
//        db.insert("orderplace",null,cv);
//        db.close();
//
//     }
//     public ArrayList getOderData(String username){
//        ArrayList<String> arr  = new ArrayList<>();
//        SQLiteDatabase db =  getReadableDatabase();
//        String str []= new String[1];
//        str[0] = username;
//        Cursor c = db.rawQuery("select * from orderplace where username = ? ",str);
//        if(c.moveToFirst()){
//            do{
//                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
//
//            }while (c.moveToNext());
//        }
//        db.close();
//        return arr;
//     }
}
