package com.example.mywebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.webView1);

        //Enable JavaScript
        webview.getSettings().setJavaScriptEnabled(true);

        // Zoom out of the page to fit the display
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);

        // Enable pinch to zoom setting
        webview.getSettings().setBuiltInZoomControls(true);

        webview.loadUrl("https://youtube.com/");
    }

    @Override
    public void onBackPressed(){
        if(webview.canGoBack()){
            webview.goBack();
        }else super.onBackPressed();
    }
}
