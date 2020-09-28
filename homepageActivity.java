package com.example.newsample_design;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class homepageActivity extends AppCompatActivity {

    ImageButton bt_add;
    ImageButton logout;
    ImageButton btn_view;
    String username;
    TextView txt;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        txt=findViewById(R.id.txt1);

        username=getIntent().getExtras().getString("user");
        txt.setText("Welcome"+" "+username);
       // Toast.makeText(homepageActivity.this,username,Toast.LENGTH_LONG).show();
        btn_view=findViewById(R.id.btnview);
        bt_add=  findViewById(R.id.bt_add);
        logout=findViewById(R.id.logout);


        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(homepageActivity.this,delivery_team.class);
//                startActivity(i);

                Intent i = new Intent(homepageActivity.this,RecordListActivity.class);
                startActivity(i);
            }
        });


        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==bt_add){
                    Intent intent   = new Intent(homepageActivity.this,add_items.class);
                    intent.putExtra("user",username);
                    startActivity(intent);
                }
            }
      });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   = new Intent(homepageActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }



}
