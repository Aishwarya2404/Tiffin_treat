package com.example.newsample_design;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class reg_activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{




    EditText username,email,pass,phone,address;
    String item;
     Button btnreg;
    Spinner spinner;
    SQLiteDatabase db;
    sqlhelper helper;
    String city_names[]={"Anand","Surat","Rajkot","Baroda","Ahemedabad"};

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        final String[] str = new String[1];
        username=findViewById(R.id.username);
        email=findViewById(R.id.emailid);
        pass=findViewById(R.id.password);
        phone=findViewById(R.id.mobile);
        address=findViewById(R.id.address);
        btnreg=findViewById(R.id.btnreg);


        helper=new sqlhelper(this);
        db=helper.getWritableDatabase();

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmInput();

            }
        });



        spinner=findViewById(R.id.spcity);
        ArrayAdapter<String> aa=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,city_names);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(this);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//            {
//                item=city_names[position];
//                Toast.makeText(parent.getContext(), item,Toast.LENGTH_LONG).show();
//            }




    }

    private boolean validateEmail()
    {
        String emailid=email.getText().toString().trim();

        if(emailid.isEmpty())
        {
            email.setError("Field can't be empty");
            return true;

        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches())
        {
            email.setError("please enter a valid email address");
            return true;

        }

        
        return false;
    }

    private Boolean validatePass()
    {
        String passw=pass.getText().toString().trim();
        Pattern pattern;
        Matcher matcher;
        final String PASS_PATTERN="^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{6,}$";
        pattern =Pattern.compile(PASS_PATTERN);
        matcher=pattern.matcher(passw);
        if(passw.isEmpty())
        {
            pass.setError("Field can't be empty");
            return true;
        }
        else if(!matcher.matches())
        {
            pass.setError("password too weak");
            return true;
        }
        return false;
    }

    private boolean validateUsername()
    {
        String user=username.getText().toString().trim();

        if(user.isEmpty())
        {
            username.setError("Field can't be empty");
            return true;

        }
        else if(user.length()>=25)
        {
            username.setError("Username too long");
            return true;

        }


        return false;
    }

    private boolean validateMobile()
    {
        String mobile=phone.getText().toString().trim();

        if(mobile.isEmpty())
        {
            phone.setError("Field can't be empty");
            return true;

        }
        return false;
    }

    private boolean validateAddress()
    {
        String add=address.getText().toString().trim();

        if(add.isEmpty())
        {
            address.setError("Field can't be empty");
            return true;

        }
        return false;
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        item=city_names[position];
        //Toast.makeText(parent.getContext(), item,Toast.LENGTH_LONG).show();
    }

    private void confirmInput()
    {
          if(validateEmail()==true | validatePass()==true | validateUsername()==true | validateMobile()==true |validateAddress()==true)
          {
              Toast.makeText(this,"check it",Toast.LENGTH_LONG).show();
              return;
          }
          else{
              //Integer int1=Integer.parseInt(phone.getText().toString());
              String query="insert into foodmaker_reg(Username,Email,Password,Mobile,Address,city) values('"+username.getText().toString()+"','"+email.getText().toString()+"','"+pass.getText().toString()+"','"+phone.getText().toString()+"','"+address.getText().toString()+"','"+item+"')";
              db.execSQL(query);
              Toast.makeText(this,"successfully",Toast.LENGTH_LONG).show();

              Intent i=new Intent(reg_activity.this,MainActivity.class);
               startActivity(i);
          }

//

    }




    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}