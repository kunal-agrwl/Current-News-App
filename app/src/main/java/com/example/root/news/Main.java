package com.example.root.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * Created by root on 3/1/17.
 */

public class Main extends Activity implements View.OnClickListener {

    public static boolean flag;
    Button refresh;
    public static ListView list;
    public static ArrayList<String> data,urls;
    public static ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        refresh=(Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        list=(ListView)findViewById(R.id.list);
        data=new ArrayList<String> ();
        urls=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this, R.layout.text,data);
        getNews();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent web=new Intent(Main.this, Web.class);
                web.putExtra("url",urls.get(position));
                startActivity(web);

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    void getNews()
    {
        flag=false;
        adapter.clear();
        data.clear();
        urls.clear();
        new GetNews().execute(API.news_source[new Random().nextInt(API.size)]);
        if(flag)
            Toast.makeText(this,"Please check your internet connectivity",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onClick(View v)
    {
        getNews();
    }
}
