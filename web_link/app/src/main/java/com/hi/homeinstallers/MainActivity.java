package com.hi.homeinstallers;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String URL_HOMEINSTALLERS = "https://app.homeinstallers.co.uk";

    private WebView webViewurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webViewurl = findViewById(R.id.webView1);

        webViewurl.getSettings().setBuiltInZoomControls(false);
        webViewurl.getSettings().setSupportZoom(false);
        webViewurl.getSettings().setDomStorageEnabled(true);
        webViewurl.getSettings().setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptCookie(true);

        webViewurl.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
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
        webViewurl.loadUrl(URL_HOMEINSTALLERS);
    }

}