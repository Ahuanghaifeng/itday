package com.infomation.haiffeng.App.infomation.Gank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.infomation.haiffeng.R;
import com.infomation.haiffeng.App.WebView.WebActivity;
import com.infomation.haiffeng.base.BaseFragment;
import com.infomation.haiffeng.App.clickinterface.OnItemClickListener;
import com.infomation.haiffeng.enity.GankInformationBean;
import com.infomation.haiffeng.App.infomation.InformationModel;
import com.zly.www.easyrecyclerview.EasyDefRecyclerView;
import com.zly.www.easyrecyclerview.listener.OnLoadListener;
import com.zly.www.easyrecyclerview.listener.OnRefreshListener;
import com.zly.www.easyrecyclerview.ptrlib.header.MaterialHeader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 黄海峰 on 2017/3/19.
 */

public class GInformationDetailFragment extends BaseFragment implements OnItemClickListener,
        OnRefreshListener,OnLoadListener,GInformationDataListener{

    @BindView(R.id.erv)
    EasyDefRecyclerView recyclerView;

    private int mType;
    private Context mContext;
    private InformationModel mHolder;
    private GInformationDealAdapter mAdapter;
    private List<GankInformationBean> mData;

    /**
     * 控件是否初始化完成
     */
    private boolean isViewCreated;
    /**
     * 数据是否已加载完毕
     */
    private boolean isLoadDataCompleted;
    private int page = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information_detail, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {
        mContext = getActivity();
        mData = new ArrayList<>();
        Bundle bundle = getArguments();
        mType = bundle.getInt("type");
        mHolder = new InformationModel(mContext,mType,lifecycleSubject);
        mHolder.setRequestGListener(this);
        mAdapter = new GInformationDealAdapter();
        recyclerView.setHeaderView(new MaterialHeader(mContext));
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setOnLoadListener(this);
        mAdapter.setOnItemClickListener(this);
        isViewCreated = true;
        if (getUserVisibleHint()) {
            recyclerView.autoRefresh();
        }
    }

    @Override
    public void OnItemClick(View view, int position) {
        Intent intent = new Intent(mContext, WebActivity.class);
        intent.putExtra("url",mData.get(position).getUrl());
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),view, "transitionItem");
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
    }

    @Override
    public void onGSuccess(List<GankInformationBean> result) {
        isLoadDataCompleted = true;
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
    public void onLoadListener() {
        page++;
        mHolder.requetGankData(page);
    }

    @Override
    public void onRefreshListener() {
        page=1;
        mHolder.requetGankData(page);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {
            recyclerView.autoRefresh();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoadDataCompleted = false;
    }
}
