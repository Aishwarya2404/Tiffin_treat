package com.example.newsample_design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsample_design.homepageActivity;
import com.example.newsample_design.reg_activity;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Button btnlogin;
    TextView txtreg;
    EditText email,password;
    SQLiteDatabase db;
    sqlhelper helper;
    String mail,passwo,myemail,mypass,myuser;
    TextView forgotpass;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        forgotpass=findViewById(R.id.forgotpass);


        email=findViewById(R.id.Email);
        password=findViewById(R.id.pass);

        helper=new sqlhelper(this);
        db=helper.getWritableDatabase();

        btnlogin=findViewById(R.id.btnlog);


        txtreg=findViewById(R.id.txtreg);
        txtreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, reg_activity.class);
                startActivity(i);

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(email.getText().length()==0 ||  password.getText().length()==0)
                {
                    if(email.getText().length()==0)
                    {
                        email.setError("....plese Enter the username");
                        Toast.makeText(MainActivity.this,"check it",Toast.LENGTH_LONG).show();
                    }
                    if(password.getText().length()==0)
                    {
                        password.setError("....plese Enter the password");
                        Toast.makeText(MainActivity.this,"check it",Toast.LENGTH_LONG).show();
                    }

                }
                else
                    {
                        mail=email.getText().toString();
                        passwo=password.getText().toString();
                        Cursor c=db.rawQuery("select *from foodmaker_reg where Email='"+mail+"'",null);
                        if(c!=null)
                        {

                            int userindex=c.getColumnIndex("Username");
                            int emailindex=c.getColumnIndex("Email");
                            int passindex=c.getColumnIndex("Password");
                            if(c.moveToFirst())
                            {
                                do
                                {
                                    myuser=c.getString(userindex);
                                    myemail=c.getString(emailindex);
                                    mypass=c.getString(passindex);

                                }while(c.moveToNext());


                                if(mypass.equals(passwo))
                                {
                                    Toast.makeText(MainActivity.this,"successfully login",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(MainActivity.this,homepageActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                    i.putExtra("user",myuser);
                                    startActivity(i);
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this,"wrong password",Toast.LENGTH_LONG).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"not login",Toast.LENGTH_LONG).show();
                            }



                        }
                    }

//                if (v==btnlogin){
//                    Intent intent   = new Intent(MainActivity.this, homepageActivity.class);
//                    startActivity(intent);
//                }
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
