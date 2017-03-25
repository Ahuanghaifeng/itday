package com.infomation.haiffeng.App.gallery;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.infomation.haiffeng.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 招聘大图浏览
 * Created by w on 2017/1/3.
 */
public class ImgLookActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private List<String> mData;
    private int mPosition;
    private ImgLookAdapter mAdapter;
    private String savePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeimg);
        ButterKnife.bind(this);
        initView();
    }

    public void initView() {
        mPosition = getIntent().getIntExtra("mPosition", 0);
        mData = getIntent().getStringArrayListExtra("data");
        mAdapter = new ImgLookAdapter(this, mData);
        viewpager.setAdapter(mAdapter);
        viewpager.setCurrentItem(mPosition);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        File imgFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"iTDay");
        if (!imgFile.exists()){
            imgFile.mkdir();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.downloader_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_download:
                savePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"iTDay"+mData.get(mPosition).substring(mData.get(mPosition).lastIndexOf("/"));
                startDownload();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startDownload(){
        Target target = new Target(){
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                File imgFile = new File(savePath);
                FileOutputStream ostream = null;
                try {
                    ostream = new FileOutputStream(imgFile);
                    if (savePath.contains(".PNG")|| savePath.contains(".png")){
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                    }else if (savePath.contains(".JPG")|| savePath.contains(".jpg")) {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                    }
                    ostream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(ImgLookActivity.this,"图片下载至:"+ savePath,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.with(this).load(mData.get(mPosition)).into(target);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.ondestory();
        mAdapter = null;
    }
}
