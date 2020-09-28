package com.example.newsample_design;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordListAdapter extends BaseAdapter {
    private Context context;
    private int layout;

    private ArrayList<Model> recordlist;

    public RecordListAdapter(Context context, int layout, ArrayList<Model> recordlist) {
        this.context = context;
        this.layout = layout;
        this.recordlist = recordlist;
    }

    public int getCount() {
        return recordlist.size();
    }

    @Override
    public Object getItem(int i) {
        return recordlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txt_per,txt_month,txt_year,txt_week;

    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View row = view;
        ViewHolder holder=new ViewHolder();

        if(row==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(layout,null);
            holder.txt_per=row.findViewById(R.id.txt_per_tiffin);
            holder.txt_month=row.findViewById(R.id.txt_per_month);
            holder.txt_year=row.findViewById(R.id.txt_per_year);
            holder.txt_week=row.findViewById(R.id.txt_per_week);
            holder.imageView=row.findViewById(R.id.imgIcon);
            row.setTag(holder);
        }
        else {
            holder=(ViewHolder)row.getTag();

        }
        Model model=recordlist.get(i);
        holder.txt_per.setText(" "+"  "+model.getPrice_per_tiffin());
        holder.txt_month.setText(" "+"  "+model.getPrice_per_month());
        holder.txt_year.setText(" "+"  "+model.getPrice_per_year());
        holder.txt_week.setText(" "+"  "+model.getPrice_per_week());

        byte[] recordImage=model.getImage();
        Bitmap bitmap= BitmapFactory.decodeByteArray(recordImage,0,recordImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
