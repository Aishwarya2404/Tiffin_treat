package com.example.newsample_design;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class add_items extends AppCompatActivity {

    ImageView mImageView;
    Button mchoosebtn,img_add;
    public static sqlhelper msqlhelper;
//    SQLiteDatabase db;
//    sqlhelper helper;
    Button submit;
    String username;
    EditText price_per,price_month,price_year,price_week;
    EditText e1_name,e2_name,e3_name,e4_name,e5_name;
    EditText e1_price,e2_price,e3_price,e4_price,e5_price;
    TextView txt1;
    long res;

    private static final int imag_pick_code=1000;
    private static final int permission_code=1001;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_items);



        msqlhelper=new sqlhelper(this);
//
//        db=helper.getWritableDatabase();

        msqlhelper.queryData("CREATE TABLE IF NOT EXISTS add_items(id INTEGER PRIMARY KEY AUTOINCREMENT,price_per_tiffin varchar(20),price_per_month varchar(30),price_per_year varchar(30),price_per_week varchar(30),e1_name varchar(20),e2_name varchar(10),e3_name varchar(30),e4_name varchar(30),e5_name varchar(30),e1_price varchar(30),e2_price varchar(30),e3_price varchar(30),e4_price varchar(30),e5_price varchar(30),image BLOB)");




        username=getIntent().getExtras().getString("user");
        txt1=findViewById(R.id.txt1);
        txt1.setText("Welcome"+" "+username);
        price_per=findViewById(R.id.price_per);
        price_month=findViewById(R.id.price_month);
        price_year=findViewById(R.id.price_year);
        price_week=findViewById(R.id.price_week);
        e1_name=findViewById(R.id.e1_name);
        e2_name=findViewById(R.id.e2_name);
        e3_name=findViewById(R.id.e3_name);
        e4_name=findViewById(R.id.e4_name);
        e5_name=findViewById(R.id.e5_name);
        e1_price=findViewById(R.id.e1_price);
        e2_price=findViewById(R.id.e2_price);
        e3_price=findViewById(R.id.e3_price);
        e4_price=findViewById(R.id.e4_price);
        e5_price=findViewById(R.id.e5_price);




        mImageView=findViewById(R.id.img);
        submit=findViewById(R.id.btnsubmit);
        mchoosebtn=findViewById(R.id.choose_img_btn);

        mchoosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED){
                        String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,permission_code);
                    }
                    else
                    {
                        pickImageFromGalary();
                    }

                }
                else
                {
                    pickImageFromGalary();
                }
            }
        });

//        img_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Intent i=new Intent(add_items.this,food_list_activity.class);
//               startActivity(i);
//
//            }
//        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                       msqlhelper.insertData(
                               price_per.getText().toString().trim(),
                               price_month.getText().toString().trim(),
                               price_year.getText().toString().trim(),
                               price_week.getText().toString().trim(),
                               e1_name.getText().toString().trim(),
                               e2_name.getText().toString().trim(),
                               e3_name.getText().toString().trim(),
                               e4_name.getText().toString().trim(),
                               e5_name.getText().toString().trim(),
                               e1_price.getText().toString().trim(),
                               e2_price.getText().toString().trim(),
                               e3_price.getText().toString().trim(),
                               e4_price.getText().toString().trim(),
                               e5_price.getText().toString().trim(),
                               imageviewtoByte(mImageView)
                       );
                       Toast.makeText(add_items.this, "added succesfully", Toast.LENGTH_LONG).show();
                       e1_price.setText("");
                   } catch (Exception e) {
                       e.printStackTrace();
                   }

//                db.execSQL(query);
//                Toast.makeText(add_items.this,"successfully",Toast.LENGTH_LONG).show();

            }
        });


    }

    private byte[] imageviewtoByte(ImageView image)
    {
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }


    private void pickImageFromGalary()
    {

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,imag_pick_code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case permission_code:{
                if(grantResults.length>0 && grantResults[0]==
                PackageManager.PERMISSION_GRANTED){
                    pickImageFromGalary();
                }
                else
                {
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
                }
            }
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == imag_pick_code) {
            mImageView.setImageURI(data.getData());

        }
    }
}

