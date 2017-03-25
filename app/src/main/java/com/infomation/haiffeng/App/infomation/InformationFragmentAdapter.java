package com.infomation.haiffeng.App.infomation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by 黄海峰 on 2017/3/18.
 */

public class InformationFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;

    public InformationFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "GitHub";
            case 1:
                return "hacker";
            case 2:
               return "SegmentFault";
            case 3:
                return "JobBole";
            case 4:
                return "头条";
            case 5:
                return "Android";
            case 6:
                return "IOS";
            case 7:
                return "All";
        }
        return "";
    }
}
