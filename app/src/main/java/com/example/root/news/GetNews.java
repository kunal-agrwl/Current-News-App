package com.example.root.news;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.root.news.Main.adapter;

/**
 * Created by root on 3/1/17.
 */

public class GetNews extends AsyncTask<String, Integer,String> {

    @Override
    protected String doInBackground(String... params) {

        String url=params[0];
        try
        {
            HttpURLConnection connection=(HttpURLConnection)new URL(url).openConnection();
            BufferedReader read=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            connection.connect();
            String temp="";
            StringBuffer buffer=new StringBuffer();
            while((temp=read.readLine())!=null)
            {
                buffer.append(temp);
            }
            connection.disconnect();
            return buffer.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Main.flag=true;
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try
        {
            JSONObject object = new JSONObject(result);
            JSONArray array = object.getJSONArray("articles");
            for(int i=0; i<array.length(); i++)
            {
                JSONObject obj=array.getJSONObject(i);
                String title=obj.getString("title");
                String description=obj.getString("description");
                String url=obj.getString("url");
                String urlimage=obj.getString("urlToImage");

                Main.urls.add(url);
                Main.data.add(title);
            }
            Main.list.setAdapter(adapter);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
