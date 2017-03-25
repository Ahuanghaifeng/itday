package com.infomation.haiffeng.App.gallery;

import android.content.Context;

import com.infomation.haiffeng.base.ActivityLifeCycleEvent;
import com.infomation.haiffeng.enity.GankInformationBean;
import com.infomation.haiffeng.http.Api;
import com.infomation.haiffeng.http.HttpUtil;
import com.infomation.haiffeng.http.ProgressSubscriber;
import com.infomation.haiffeng.App.infomation.Gank.GInformationDataListener;
import com.infomation.haiffeng.util.AppTools;
import com.infomation.haiffeng.util.Constants;

import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by 黄海峰 on 2017/3/19.
 */

public class GalleryModel {

    private Context mContext;
    private PublishSubject<ActivityLifeCycleEvent> mLifecycleSubject;
    private String catchKey;
    private GInformationDataListener mGListener;

    public GalleryModel(Context context, PublishSubject<ActivityLifeCycleEvent> lifecycleSubject){
        mContext = context;
        mLifecycleSubject = lifecycleSubject;
        catchKey = "catchImages";
    }

    /**
     * 获取图片数据
     * @param page
     */
    public void requestData(int page){
        Observable observable = Api.getGankDefault().getGalleryInformation(String.valueOf(page));
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
        },catchKey, ActivityLifeCycleEvent.DESTROY, mLifecycleSubject, true, getNetState()?true:false, Constants.GANKTYPE);
    }

    public void setRequestGListener(GInformationDataListener listener){
        mGListener = listener;
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
