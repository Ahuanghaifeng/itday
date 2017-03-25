package com.infomation.haiffeng.App;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.infomation.haiffeng.App.about.AboutFragment;
import com.infomation.haiffeng.R;
import com.infomation.haiffeng.App.gallery.GalleryFragment;
import com.infomation.haiffeng.App.infomation.InformationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragments;
    private int mLastPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.information, "文章").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.mipmap.tuku, "图库").setActiveColorResource(R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.mipmap.about, "关于").setActiveColorResource(R.color.colorJu))
                .setFirstSelectedPosition(0)
                .initialise();

        getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    public void getFragments() {
        fragments = new ArrayList<>();
        InformationFragment informationFragment = new InformationFragment();
        GalleryFragment galleryFragment = new GalleryFragment();
        AboutFragment aboutFragment = new AboutFragment();
        fragments.add(informationFragment);
        fragments.add(galleryFragment);
        fragments.add(aboutFragment);
    }

    public void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =  fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragments.get(0));
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =  fragmentManager.beginTransaction();
        if (fragments.get(position).isAdded()){
            transaction.hide(fragments.get(mLastPosition)).show(fragments.get(position));
        }else{
            transaction.hide(fragments.get(mLastPosition)).add(R.id.frame_layout,fragments.get(position));
        }
        transaction.commit();
        mLastPosition = position;
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
