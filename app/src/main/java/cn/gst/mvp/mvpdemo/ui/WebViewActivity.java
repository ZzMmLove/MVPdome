package cn.gst.mvp.mvpdemo.ui;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import cn.gst.mvp.mvpdemo.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebview;
    private TextView beginLoading;
    private TextView endLoading;
    private TextView loading;
    private TextView mtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebview = (WebView) findViewById(R.id.webView1);
        beginLoading = (TextView) findViewById(R.id.text_beginLoading);
        endLoading = (TextView) findViewById(R.id.text_endLoading);
        loading = (TextView) findViewById(R.id.text_Loading);
        mtitle = (TextView) findViewById(R.id.title);
        WebSettings webSettings = mWebview.getSettings();
        String url = "http://www.baidu.com";
        mWebview.loadUrl(url);
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });

        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mtitle.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                String progress = null;
                if (newProgress < 100){
                     progress = newProgress + "%";
                    loading.setText(progress);
                }else if (newProgress == 100){
                    progress = newProgress + "%";
                    loading.setText(progress);
                }
            }
        });

        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                endLoading.setText("结束加载了");

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                beginLoading.setText("开始加载了");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()){
            mWebview.canGoBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (mWebview != null){
            //先把内容设置为空，再清除历史，再移除，再销毁，最后再置空
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();
            ((ViewGroup)mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview=null;
        }
        super.onDestroy();
    }
}
