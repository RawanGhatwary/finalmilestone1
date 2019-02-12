package com.example.protocol1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class listActivity extends Activity  {
    String[] data={"iPhone", "Samsung" ,"Windows","Blueberry","Linux"};
    int[] images={R.drawable.iphone,R.drawable.samsung,R.drawable.wind,R.drawable.blueberry,R.drawable.linux};




    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView Mylst=(ListView) findViewById(R.id.Mylst);
        CustomerAdapter customerAdapter=new CustomerAdapter();
        Mylst.setAdapter(customerAdapter);
        Mylst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //finish();
                Intent intent = new Intent(listActivity.this, emptyActivity.class);
                startActivity(intent);
                String msg = "You clicked on item # " + (position+1);
                Toast.makeText(listActivity.this, msg, Toast.LENGTH_LONG).show();

            }
        });







    }
    class CustomerAdapter extends  BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.listitem,null);
            ImageView imgtxt=(ImageView) convertView.findViewById(R.id.imgitem);
            TextView txtitem=(TextView) convertView.findViewById(R.id.txtitem);
            imgtxt.setImageResource(images[position]);
            txtitem.setText(data[position]);


            return convertView;
        }
    }










}


