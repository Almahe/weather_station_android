package com.example.wsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview =(WebView)findViewById(R.id.webView);   //Webview setup
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);   //enables js
        webview.getSettings().setDomStorageEnabled(true);   //enable the web storage api
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);


        if (!isOnline()){
            Button refresh = new Button(this);  //Button to refresh page in case of no connection
            refresh.setText("Refresh");
            webview.addView(refresh);
            refresh.setGravity(Gravity.CENTER);


            Toast.makeText(getApplicationContext(), "No Internet!", Toast.LENGTH_SHORT).show();  //Small pop-up text saying no interneet
            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);   //Restarts the main activity when refresh button is pressed
                }
            });
        } else {
            webview.loadUrl("http://84.82.162.221:2004/WeatherStation/webapp/");  //website url
        }
    }

    public boolean isOnline() {     //Checks internet connection
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
