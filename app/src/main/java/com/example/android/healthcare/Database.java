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

    public int login(String table, String username, String password){
        int[] result = new int[1];

        Query query = mDatabase.child(table).orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Loop through the matching users
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Get the user object and check the password

                        User user = userSnapshot.getValue(User.class);
                        if (user.getPassword().equals(password)) {
                            Log.d("data return", "user.getPassword(): "+ user.getPassword());
                            result[0] = 1;
                            break;
                        }
                    }
                }else{
                result[0] = 0;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
        Log.d("data return", "data: "+ result[0]);

        return result[0];
    }
//
//    public void addCart(String username, String product, String price, String  otype){
//        ContentValues cv =  new ContentValues();
//        SQLiteDatabase db = getWritableDatabase();
//
//        cv.put("username", username);
//        cv.put("product", product);
//        cv.put("price", price);
//        cv.put("otype", otype);
//        db.insert("cart",null,cv);
//        db.close();
//
//    }
//
//    public int checkCart(String username, String product){
//        this.addCart(username, product, "price", "otype");
//        int result=0;
//        String str[] = new String[2];
//        str[0] = username;
//        str[1] = product;
//        SQLiteDatabase db = getReadableDatabase();
////        if(db != null){
////            Cursor c = db.rawQuery("select * from cart where username = ? and product = ? ", str);
////            if (c.moveToFirst()){
////                result=1;
////            }
////            db.close();
////        }else{
//
//            Cursor c = db.rawQuery("select * from cart where username = ? and product = ? ", str);
//            if (c.moveToFirst()){
//                result=1;
//            }
//            db.close();
////        }
//        return result;
//
//    }
//
//    public void removeCart(String usrname, String otype){
//        String str[] = new String[2];
//        str[0] = usrname;
//        str[1] = otype;
//        SQLiteDatabase db = getReadableDatabase();
//        db.delete("cart", "username = ? and  otype = ?",str);
//        db.close();
//    }
//
//    public ArrayList getCartDate(String usernaem, String ptype){
//        ArrayList<String> arr  = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        String str[] = new String[2];
//        str[0] = usernaem;
//        str[1] =  otype;
//        Cursor c = db.rawQuery("select * from cart where username = ? and otype =?",str);
//        if(c.moveToFirst()){
//            do {
//                String product = c.getString(1);
//                String price = c.getString(2);
//                arr.add(product+"$"+price);
//            }while (c.moveToNext());
//        }
//        db.close();
//        return arr;
//     }
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
