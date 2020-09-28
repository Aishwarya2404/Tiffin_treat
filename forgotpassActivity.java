package com.example.newsample_design;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toolbar;

public class forgotpassActivity extends AppCompatActivity {

   Toolbar toolbar;
   ProgressBar progressBar;
   EditText userEmail;
   Button userPass;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
    }
}