package com.infomation.haiffeng.App.gallery;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.infomation.haiffeng.view.ZoomImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by w on 2017/1/3.
 */
public class ImgLookAdapter extends PagerAdapter{

    private List<String> mData;
    private Context mContext;
    private List<View> mViews;

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        if (container.indexOfChild(view) == -1) {
            container.addView(view);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    public ImgLookAdapter(Context context,List<String> data){
        mContext = context;
        mData = data;
        mViews = new ArrayList<>();
        int i = 0;
        for (String str : mData){
            ZoomImageView zoomImageView = getItemView(i);
            mViews.add(zoomImageView);
            i++;
        }
    }

    public ZoomImageView getItemView(int i){
        ZoomImageView zoomImageView = new ZoomImageView(mContext);
        zoomImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ImgLookActivity)mContext).finish();
            }
        });
        Picasso.with(mContext).load(mData.get(i)).into(zoomImageView);
        return zoomImageView;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public void ondestory(){
        mData = null;
        mContext = null;
        if (mViews!=null){
            for (int i =0;i<mViews.size();i++){
                View view = mViews.get(i);
                view = null;
            }
        }
        mViews = null;
    }

}
