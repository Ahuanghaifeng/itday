package com.infomation.haiffeng.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infomation.haiffeng.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by w on 2016/10/8.
 */
public class UserItemCommon extends LinearLayout{

    @BindView(R.id.iv_item)
    public ImageView head;
    @BindView(R.id.tv_item)
    public TextView name;

    public UserItemCommon(Context context) {
        super(context);
        initView(context);
    }
    public UserItemCommon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_user,this,true);
        ButterKnife.bind(this);
    }

}
