package com.infomation.haiffeng.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import rx.subjects.PublishSubject;

/**
 * Created by 黄海峰 on 2017/3/18.
 */

public class BaseFragment extends Fragment{

    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
    }
}
