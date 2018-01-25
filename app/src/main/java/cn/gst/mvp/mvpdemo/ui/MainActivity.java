package cn.gst.mvp.mvpdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.gst.mvp.mvpdemo.R;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    /**前一个地址是代表的是微软的一个在线预览office文档的工具，后一个是自已的office文档地址*/
    private String url = "https://view.officeapps.live.com/op/view.aspx?src=http://shuoke360.cn/Public/Uploads/Note/20170626/5950a8d17eecc.ppt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }
        });

        WebSettings setting = mWebView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setUseWideViewPort(true);
        mWebView.loadUrl(url);
    }
}
