package as.programmer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String [] urls = getResources().getStringArray(R.array.urls);
        int position = getIntent().getIntExtra(Helper.EXTRA_POSITION, 0);

        webView = findViewById(R.id.webView);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.setInitialScale(1);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                swipeRefreshLayout.setRefreshing(true);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!(url.startsWith("intent"))) view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(urls[position]);

    }

    public void onClickBack(View view) {
        if (webView.canGoBack()) webView.goBack();
        else WebActivity.this.finish();
    }

    public void onClickHome(View view) {
        WebActivity.this.finish();
    }

    public void onClickForward(View view) {
        if (webView.canGoForward()) webView.goForward();
    }

}