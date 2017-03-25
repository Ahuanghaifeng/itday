package com.infomation.haiffeng.App.about;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infomation.haiffeng.R;
import com.infomation.haiffeng.util.AppTools;
import com.infomation.haiffeng.util.DataCleanManager;
import com.infomation.haiffeng.view.UserItemCommon;
import com.infomation.haiffeng.view.UserItemCommonT;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 黄海峰 on 2017/3/19.
 */

public class AboutFragment extends Fragment {

    @BindView(R.id.version)
    UserItemCommonT version;
    @BindView(R.id.hc)
    UserItemCommonT hc;
    @BindView(R.id.about)
    UserItemCommon about;
    @BindView(R.id.help)
    UserItemCommon help;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    public void initView() {
        hc.head.setBackgroundResource(R.mipmap.clearhc);
        hc.name.setText(R.string.clearhc);
        about.head.setBackgroundResource(R.mipmap.about);
        about.name.setText(R.string.about);
        version.head.setBackgroundResource(R.mipmap.version);
        version.name.setText(R.string.version);
        version.value.setText(AppTools.getVersionName(getContext(),"com.infomation.haiffeng"));
        help.head.setBackgroundResource(R.mipmap.help);
        help.name.setText(R.string.zhiyuan);
        hc.setOnClickListener(onClickListener);
        about.setOnClickListener(onClickListener);
        help.setOnClickListener(onClickListener);
        getHc();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.about:
                    Intent intent = new Intent(getContext(),AboutActivity.class);
                    startActivity(intent);
                    break;
                case R.id.hc:
                    DataCleanManager.clearAllCache(getContext());
                    getHc();
                    break;
                case R.id.help:
                    Intent intent1 = new Intent(getContext(),DaShangActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    };

    public void getHc(){
        try {
            hc.value.setText(DataCleanManager.getTotalCacheSize(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
