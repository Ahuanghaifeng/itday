package com.infomation.haiffeng.App.infomation;

import android.content.Context;

import com.infomation.haiffeng.base.ActivityLifeCycleEvent;
import com.infomation.haiffeng.enity.GankInformationBean;
import com.infomation.haiffeng.enity.InformationBean;
import com.infomation.haiffeng.http.Api;
import com.infomation.haiffeng.http.HttpUtil;
import com.infomation.haiffeng.http.ProgressSubscriber;
import com.infomation.haiffeng.App.infomation.Gank.GInformationDataListener;
import com.infomation.haiffeng.App.infomation.Geek.InformationDataListener;
import com.infomation.haiffeng.util.AppTools;
import com.infomation.haiffeng.util.Constants;

import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by 黄海峰 on 2017/3/18.
 */

public class InformationModel {

    private Context mContext;
    private PublishSubject<ActivityLifeCycleEvent> mLifecycleSubject;
    private String catchKey;
    private int mType;
    private InformationDataListener mListener;
    private GInformationDataListener mGListener;

    public InformationModel(Context context, int type, PublishSubject<ActivityLifeCycleEvent> lifecycleSubject){
        mContext = context;
        mLifecycleSubject = lifecycleSubject;
        mType = type;
        catchKey = "InformationBean"+mType;
    }

    public void requestData(){
        switch (mType){
            case 0:
                requestGeekViData();
                break;
            case 1:
                requestGeekViData();
                break;
            case 2:
                requestGeekViData();
                break;
            case 3:
                requestGeekViData();
                break;
            case 4:
                requestGeekViData();
                break;
            case 5:
                requetGankData(1);
                break;
            case 6:
                requetGankData(1);
                break;
            case 7:
                requetGankData(1);
                break;
        }
    }

    /**
     * 获取geekvi.net里面的内容
     * 包括5大类
     */
    private void requestGeekViData(){
        Observable observable = null;
        switch (mType){
            case 0 :
                observable = Api.getDefault().getGitInformation();
                break;
            case 1:
                observable = Api.getDefault().getHackerInformation();
                break;
            case 2:
                observable = Api.getDefault().getSegmentInformation();
                break;
            case 3:
                observable = Api.getDefault().getJobboleInformation();
                break;
            case 4:
                observable = Api.getDefault().getToutiaoInformation();
                break;
        }
        HttpUtil.getInstance().toSubscribe(observable, new ProgressSubscriber<List<InformationBean>>(mContext) {
            @Override
            protected void _onNext(List<InformationBean> result) {
                if (mListener!=null){
                    mListener.onSuccess(result);
                }
            }

            @Override
            protected void _onError(String message) {
                if (mListener!=null){
                    mListener.onError(message);
                }
            }
        },catchKey, ActivityLifeCycleEvent.DESTROY, mLifecycleSubject, true, getNetState()?true:false, Constants.GEEKTYPE);
    }

    public void setRequestListener(InformationDataListener listener){
        mListener = listener;
    }

    public void setRequestGListener(GInformationDataListener listener){
        mGListener = listener;
    }

    public void requetGankData(int page){
        Observable observable = null;
        String path = String.valueOf(page);
        switch (mType){
            case 5:
                observable = Api.getGankDefault().getGAndroidInformation(path);
                break;
            case 6:
                observable = Api.getGankDefault().getGIOSInformation(path);
                break;
            case 7:
                observable = Api.getGankDefault().getGAllInformation(path);
                break;
        }
        HttpUtil.getInstance().toSubscribe(observable, new ProgressSubscriber<List<GankInformationBean>>(mContext) {
            @Override
            protected void _onNext(List<GankInformationBean> result) {
                if (mGListener!=null){
                    mGListener.onGSuccess(result);
                }
            }

            @Override
            protected void _onError(String message) {
                if (mGListener!=null){
                    mGListener.onGError(message);
                }
            }
        },catchKey, ActivityLifeCycleEvent.DESTROY, mLifecycleSubject, true, getNetState()?true:false,Constants.GANKTYPE);
    }

    /**
     * 判断有无网络连接
     * @return
     */
    public boolean getNetState(){
        String state = AppTools.getMobileNetworkType(mContext);
        if (state.equals(AppTools.TYPE_NONE)){
            return false;
        }
        return true;
    }
}
