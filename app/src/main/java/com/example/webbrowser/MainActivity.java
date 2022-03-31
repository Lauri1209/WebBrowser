package com.example.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WebView web;
    EditText addressText;
    ImageButton searchB;
    ImageButton syncB;
    ImageButton previous;
    ImageButton next;
    Button SO;
    Button INI;
    String url = "http://www.google.fi";
    String mLastUrl;
    ArrayList<String> urlCache;
    int urlCachePointer;

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addressText = findViewById(R.id.addressText);
        searchB = findViewById(R.id.searchButton);
        syncB = findViewById(R.id.syncButton);
        previous = findViewById(R.id.previousButton);
        next = findViewById(R.id.nextButton);
        SO = findViewById(R.id.shoutOutButton);
        INI = findViewById(R.id.initializeButton);

        web = findViewById(R.id.webView);
        urlCache = new ArrayList<>();
        urlCache.add(url);

        web.setWebViewClient(new WebViewClient());

        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl(url);

        addressText.setSelectAllOnFocus(true);
        addressText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                searchB.performClick();
                return false;
            }
        });
    }

    public void search(View v) {
        url = addressText.getText().toString();
        if (!url.equals("")) {
            if(url.equals("index.html")){
                url = "file:///android_asset/index.html";
            } else if (!url.contains("http://")) {
                url = "http://" + url;
            }
            web.loadUrl(url);
        }
    }

    public void refresh(View v) {
        web.loadUrl(web.getUrl());
    }
    public void previousPage(View v) {
        if (web.canGoBack()) {
            web.goBack();
        }
    }
    public void nextPage(View v) {
        if (web.canGoForward()) {
            web.goForward();
        }
    }
    public void initialize(View v) {
        web.evaluateJavascript("javascript:initialize()", null);
    }
    public void shoutOut(View v) {
        web.evaluateJavascript("javascript:shoutOut()", null);
    }
}