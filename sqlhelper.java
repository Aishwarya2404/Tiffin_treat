package com.example.newsample_design;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class sqlhelper extends SQLiteOpenHelper
{
    static String databasename="treat.db";
    static int version=1;

    public sqlhelper(@Nullable Context context)

    {
        super(context, databasename,null, version);
    }

    public void queryData(String sql)
    {
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String price_per,String price_month,String price_year,String price_week,
                           String e1_name,String e2_name,String e3_name,String e4_name,String e5_name,
                           String e1_price,String e2_price,String e3_price,String e4_price,String e5_price,
                           byte[] image)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="insert into add_items values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,price_per);
        statement.bindString(2,price_month);
        statement.bindString(3,price_year);
        statement.bindString(4,price_week);

        statement.bindString(5,e1_name);
        statement.bindString(6,e2_name);
        statement.bindString(7,e3_name);
        statement.bindString(8,e4_name);
        statement.bindString(9,e5_name);

        statement.bindString(10,e1_price);
        statement.bindString(11,e2_price);
        statement.bindString(12,e3_price);
        statement.bindString(13,e4_price);
        statement.bindString(14,e5_price);

        statement.bindBlob(15,image);

        statement.executeInsert();
    }



    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query="create table foodmaker_reg(Id INTEGER PRIMARY KEY AUTOINCREMENT,Username varchar(20),Email varchar(30),Password varchar(30),Mobile int,Address varchar(20),city varchar(10))";
        db.execSQL(query);

        String query1="create table foodmaker_add_items(Id INTEGER PRIMARY KEY AUTOINCREMENT,price_per_tiffin varchar(20),price_per_month varchar(30),price_per_year varchar(30),price_per_week varchar(30),e1_name varchar(20),e2_name varchar(10),e3_name varchar(30),e4_name varchar(30),e5_name varchar(30),e1_price varchar(30),e2_price varchar(30),e3_price varchar(30),e4_price varchar(30),e5_price varchar(30))";
        db.execSQL(query1);

        String query2="create table food(image BLOB)";
        db.execSQL(query2);
    }

    public Cursor getData(String sql)
    {
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
