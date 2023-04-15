package com.example.android.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "heath";

    private ArrayList<String> columnNames;
    private String tableName;

    public Database(Context context, ArrayList<String> columnNames, String tableName) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.columnNames = columnNames;
        this.tableName = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables and initialize data
        StringBuilder createTableSql = new StringBuilder("CREATE TABLE "+ tableName +" (");
        for (int i = 0; i < columnNames.size(); i++) {
            createTableSql.append(columnNames.get(i)).append(" TEXT");
            if (i < columnNames.size() - 1) {
                createTableSql.append(", ");
            }
        }
        createTableSql.append(")");
        db.execSQL(createTableSql.toString());
    }
    public void registerUser(String usersname, String email, String password, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", usersname);
        cv.put("email", email);
        cv.put("password", password);
        db.insert(tableName, null, cv);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Migrate data from old schema to new schema
    }

    public int login(String username,String password){
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from user  where username=? and password=? ", str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }

    public void addCart(String username, String product, String price, String  otype){
        ContentValues cv =  new ContentValues();
        SQLiteDatabase db = getWritableDatabase();

        cv.put("username", username);
        cv.put("producr", product);
        cv.put("price", price);
        cv.put("otype", otype);
        db.insert("cart",null,cv);
        db.close();

    }

    public int checkCart(String username, String product){
        this.addCart(username, product, "price", "otype");
        int result=0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
//        if(db != null){
//            Cursor c = db.rawQuery("select * from cart where username = ? and product = ? ", str);
//            if (c.moveToFirst()){
//                result=1;
//            }
//            db.close();
//        }else{

            Cursor c = db.rawQuery("select * from cart where username = ? and product = ? ", str);
            if (c.moveToFirst()){
                result=1;
            }
            db.close();
//        }
        return result;

    }

    public void removeCart(String usrname, String otype){
        String str[] = new String[2];
        str[0] = usrname;
        str[1] = otype;
        SQLiteDatabase db = getReadableDatabase();
        db.delete("cart", "username = ? and  otype = ?",str);
        db.close();
    }
}
