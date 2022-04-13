package com.example.web_link;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    private WebView webViewurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webViewurl = (WebView) findViewById(R.id.webView1);

        webViewurl.getSettings().setBuiltInZoomControls(false);
        webViewurl.getSettings().setSupportZoom(false);
        webViewurl.getSettings().setDomStorageEnabled(true);
        webViewurl.getSettings().setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptCookie(true);

        final Activity activity = this;
        webViewurl.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // ignore ssl error
                if (handler != null) {
                    handler.proceed();
                } else {
                    super.onReceivedSslError(view, null, error);
                }
            }

        });

        webViewurl.loadUrl("https://app.homeinstallers.co.uk");


    }

}