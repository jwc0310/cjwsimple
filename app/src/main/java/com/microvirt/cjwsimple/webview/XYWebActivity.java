package com.microvirt.cjwsimple.webview;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class XYWebActivity extends Activity {

    private WebView mWebView;
    private String activityUrl = "http://www.xyaz.cn/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWebView = new WebView(this);
        setContentView(mWebView);
        WebSettings setting = mWebView.getSettings();
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS); //SINGLE_COLUMN
        setting.setLoadWithOverviewMode(true);
        setting.setBuiltInZoomControls(true);
        setting.setSupportZoom(true);
        setting.setJavaScriptEnabled(true);
        setting.setBuiltInZoomControls(true);
        setting.setDisplayZoomControls(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mWebView != null) {
            mWebView.loadUrl(TextUtils.isEmpty(activityUrl) ? activityUrl : activityUrl);
            mWebView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mWebView.reload();
                }
            }, 500);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.loadUrl(TextUtils.isEmpty(activityUrl) ? activityUrl : activityUrl);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
