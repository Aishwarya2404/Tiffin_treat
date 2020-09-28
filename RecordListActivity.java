package com.example.newsample_design;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RecordListActivity extends AppCompatActivity {

    sqlhelper msqlhelper;
    ListView mListView;
    ArrayList<Model> mlist;
    RecordListAdapter mAdapter=null;
    ImageView imageViewIcon;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        mListView=findViewById(R.id.listView);
        mlist=new ArrayList<>();
        mAdapter=new RecordListAdapter(this,R.layout.row,mlist);
        mListView.setAdapter(mAdapter);

        msqlhelper=new sqlhelper(this);


        //get all data from db

        Cursor cursor=msqlhelper.getData("SELECT * FROM add_items");
        mlist.clear();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String price_per=cursor.getString(1);
            String price_month=cursor.getString(2);
            String price_year=cursor.getString(3);
            String price_week=cursor.getString(4);
            byte[] image=cursor.getBlob(15);

            //add to list
            mlist.add(new Model(id,price_per,price_month,price_year,price_week,image));
        }
        mAdapter.notifyDataSetChanged();
        if(mlist.size()==0)
        {
            Toast.makeText(this,"no record",Toast.LENGTH_LONG).show();

        }

    }
}