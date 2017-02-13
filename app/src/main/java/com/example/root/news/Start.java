package com.example.root.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by root on 9/1/17.
 */

public class Start extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent target=new Intent(Start.this, Main.class);
                startActivity(target);
            }
        }, 2000);


    }
}
