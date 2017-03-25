package com.infomation.haiffeng.App.WebView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.infomation.haiffeng.R;
import com.infomation.haiffeng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by w on 2016/10/13.
 */
public class WebActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        webView.loadUrl(url);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);    //启用JS脚本
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        final ProgressDialog prDialog = ProgressDialog.show(WebActivity.this, null, "加载中...");
        prDialog.setCancelable(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                prDialog.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()){
                    webView.goBack(); // goBack()表示返回WebView的上一页面
                }else{
                    finish();
                }
            }
        });
    }

    public void shareUrl(){
        String url = webView.getUrl();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, url);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "分享到"));
    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
        webView = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share_url:
                shareUrl();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
