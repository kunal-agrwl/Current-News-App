package com.example.root.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by root on 9/1/17.
 */

public class Web extends Activity{

    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        Intent get=getIntent();
        url=get.getExtras().getString("url");
        Toast.makeText(this,"Please wait a second while the url loads :) ",Toast.LENGTH_LONG).show();
        WebView webView=(WebView)findViewById(R.id.web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    webView.loadUrl(url);
    }
}
