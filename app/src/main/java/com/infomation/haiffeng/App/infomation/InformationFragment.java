package com.infomation.haiffeng.App.infomation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infomation.haiffeng.R;
import com.infomation.haiffeng.App.infomation.Gank.GInformationDetailFragment;
import com.infomation.haiffeng.App.infomation.Geek.InformationDetailFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 黄海峰 on 2017/3/15.
 */

public class InformationFragment extends Fragment {

    @BindView(R.id.id_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private View view;
    private static final int fragmentNumber = 8;
    private List<Fragment> fragments;
    private InformationFragmentAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_infomation, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {
        fragments = new ArrayList<>();
        for (int i =0;i< fragmentNumber;i++){
            Bundle bundle = new Bundle();
            bundle.putInt("type",i);
            if (i<5){
                InformationDetailFragment fragment = new InformationDetailFragment();
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }else{
                GInformationDetailFragment fragment = new GInformationDetailFragment();
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
        }
        mAdapter = new InformationFragmentAdapter(getFragmentManager(),fragments);
        viewpager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(viewpager);
    }
}
