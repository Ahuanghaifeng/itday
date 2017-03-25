package com.infomation.haiffeng.App.gallery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infomation.haiffeng.App.infomation.Gank.GInformationDataListener;
import com.infomation.haiffeng.R;
import com.infomation.haiffeng.base.BaseFragment;
import com.infomation.haiffeng.enity.GankInformationBean;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 黄海峰 on 2017/3/19.
 */

public class GalleryFragment extends BaseFragment implements GInformationDataListener,RecyclerArrayAdapter.OnMoreListener,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;

    private View view;
    private RecyclerArrayAdapter<GankInformationBean> mAdapter;
    private List<GankInformationBean> mData;
    private ArrayList<String> mimgs;
    private boolean isLoad = false;
    private int page = 1;
    private GalleryModel mHolder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView(){
        mData = new ArrayList<>();
        mimgs = new ArrayList<>();
        mHolder = new GalleryModel(getContext(),lifecycleSubject);
        mHolder.setRequestGListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY,10,0,0);
        itemDecoration.setDrawLastItem(false);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new RecyclerArrayAdapter<GankInformationBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new GalleryViewHolder(parent,viewType);
            }
        };
        mAdapter.setMore(R.layout.view_more,this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getImgs();
                Intent intent = new Intent(getContext(),ImgLookActivity.class);
                intent.putStringArrayListExtra("data",mimgs);
                intent.putExtra("mPosition",position);
                startActivity(intent);
            }
        });
        onRefresh();
    }

    public void getImgs(){
        if (mimgs.size()<=0){
            for (int i =0;i<mData.size();i++){
                mimgs.add(mData.get(i).getUrl());
            }
        }
    }

    @Override
    public void onGSuccess(List<GankInformationBean> result) {
        if (page==1){
            mData.clear();
            mAdapter.clear();
        }
        mData.addAll(result);
        mAdapter.addAll(result);
    }

    @Override
    public void onGError(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHolder  = null;
        mData = null;
        mAdapter = null;
    }

    @Override
    public void onMoreShow() {
        page++;
        mHolder.requestData(page);
    }

    @Override
    public void onMoreClick() {

    }

    @Override
    public void onRefresh() {
        page=0;
        mHolder.requestData(page);
    }
}
