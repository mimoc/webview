package com.example.web_link;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

public class WebViewActivity extends Activity {

    private WebView webViewurl;
    ImageView back;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        //back = (ImageView) findViewById(R.id.backbutn);
        webViewurl = (WebView) findViewById(R.id.webView1);

        webViewurl.getSettings().setBuiltInZoomControls(false);
        webViewurl.getSettings().setSupportZoom(false);
        //webViewurl.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //webViewurl.getSettings().setAllowFileAccess(true);
        webViewurl.getSettings().setDomStorageEnabled(true);
        webViewurl.getSettings().setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptCookie(true);

        final Activity activity = this;
        //webViewurl.setWebChromeClient(new WebChromeClient());
        webViewurl.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // ignore ssl error
                if (handler != null){
                    handler.proceed();
                } else {
                    super.onReceivedSslError(view, null, error);
                }
            }

            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("MyApplication", consoleMessage.message() + " -- From line " +
                        consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
                return true;
            }
        });
////        webViewurl.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
//                Log.d("MyApplication", consoleMessage.message() + " -- From line " +
//                        consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
//                return true;
//            }
//        });
        webViewurl.loadUrl("https://app.homeinstallers.co.uk");

        /*back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {z
                // TODO Auto-generated method stub
                finish();
            }
        });*/
    }
}
