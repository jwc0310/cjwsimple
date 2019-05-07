package com.andy.cjwsimple.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;

public class OkhttpActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private WebViewClient mAdClickWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            Log.d("Andy", "shouldOverrideUrlLoading:" + url);
//            if (isGooglePlayLink(url)) {
//                launchGooglePlay(url);
//                return true;
//            }
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("Andy", "onPageFinished:" + url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            Log.d("Andy", "onReceivedError:" + failingUrl + " " + errorCode + " " + description);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_okhttp);
        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(true);
        webView.setWebViewClient(mAdClickWebViewClient);
        setContentView(webView);
        webView.loadUrl("http://api.i-screen.kr/api/ncpi_link?key=ZKSensacqKU%3D&pub=minmin&p_clk=55555&sub_param1=therings");


//        okHttpClient = new OkHttpClient.Builder()
//                .build();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("http://www.baidu.com");
//                    URLConnection conn = url.openConnection();
//                    Map<String, List<String>> headers = conn.getHeaderFields();
//                    Set<String> keys = headers.keySet();
//
//                    for (String key : keys) {
//                        String value = conn.getHeaderField(key);
//                        Log.e("Andy", "key = " + key + " , value = " + value);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}
