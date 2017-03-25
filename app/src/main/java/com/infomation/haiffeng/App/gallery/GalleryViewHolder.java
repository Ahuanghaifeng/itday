package com.infomation.haiffeng.App.gallery;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.infomation.haiffeng.R;
import com.infomation.haiffeng.enity.GankInformationBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by 黄海峰 on 2017/3/20.
 */

public class GalleryViewHolder extends BaseViewHolder<GankInformationBean> {

    private ImageView ivContent;

    public GalleryViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, R.layout.item_gallery);
        ivContent = $(R.id.iv_content);
    }

    @Override
    public void setData(GankInformationBean data) {
        Picasso.with(getContext()).load(data.getUrl()).config(Bitmap.Config.RGB_565).into(ivContent);
    }
}
