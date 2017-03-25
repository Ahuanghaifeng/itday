package com.infomation.haiffeng.App.infomation.Gank;

import com.infomation.haiffeng.enity.GankInformationBean;

import java.util.List;

/**
 * Created by 黄海峰 on 2017/3/19.
 */

public interface GInformationDataListener {
    void onGSuccess(List<GankInformationBean> result);
    void onGError(String msg);
}
