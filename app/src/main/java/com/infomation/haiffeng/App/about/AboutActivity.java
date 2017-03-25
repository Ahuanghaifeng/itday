package com.infomation.haiffeng.App.about;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.infomation.haiffeng.App.WebView.WebActivity;
import com.infomation.haiffeng.R;
import com.infomation.haiffeng.base.BaseActivity;

/**
 * Created by 黄海峰 on 2017/3/20.
 */

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void csdn(View view){
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url",((TextView)view).getText());
        intent.putExtra("title","CSDN");
        startActivity(intent);
    }

    public void github(View view){
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url",((TextView)view).getText());
        intent.putExtra("title","Github");
        startActivity(intent);
    }


}
