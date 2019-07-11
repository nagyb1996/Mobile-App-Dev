package edu.wit.mobileapp.myrtchart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.webView1);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.equals("file:///android_asset/chart.html")) {
                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        public void run() {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    webview.loadUrl("javascript::setData(" + Math.random() * 10000 + ")");
                                }
                            });
                        }
                    }, 0, 20);
                }
            }
        });

        // Enable JavaScript
        webview.getSettings().setJavaScriptEnabled(true);
        // Zoom out the page to fit the display
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.loadUrl("file:///android_asset/chart.html");
    }
}
