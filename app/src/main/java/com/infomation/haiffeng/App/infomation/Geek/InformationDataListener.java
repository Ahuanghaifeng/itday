package com.infomation.haiffeng.App.infomation.Geek;

import com.infomation.haiffeng.enity.InformationBean;

import java.util.List;

/**
 * Created by 黄海峰 on 2017/3/18.
 */

public interface InformationDataListener {
    void onSuccess(List<InformationBean> result);
    void onError(String message);
}
