package com.infomation.haiffeng.http;

import com.infomation.haiffeng.base.ActivityLifeCycleEvent;
import com.infomation.haiffeng.enity.GHttpResult;
import com.infomation.haiffeng.enity.HttpResult;
import com.infomation.haiffeng.util.Constants;

import rx.Observable;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 *
 *
 //  ┏┓　　　┏┓
 //┏┛┻━━━┛┻┓
 //┃　　　　　　　┃
 //┃　　　━　　　┃
 //┃　┳┛　┗┳　┃
 //┃　　　　　　　┃
 //┃　　　┻　　　┃
 //┃　　　　　　　┃
 //┗━┓　　　┏━┛
  //   ┃　　　┃   神兽保佑
  //   ┃　　　┃   代码无BUG！
  //   ┃　　　┗━━━┓
  //   ┃　　　　　　　┣┓
  //   ┃　　　　　　　┏┛
  //   ┗┓┓┏━┳┓┏┛
  //     ┃┫┫　┃┫┫
  //     ┗┻┛　┗┻┛
 *
 * Created by helin on 2016/10/10 11:32.
 */

public class HttpUtil {

    /**
     * 构造方法私有
     */
    private HttpUtil() {

    }

    /**
     * 在访问HttpMethods时创建单例
     */
    private static class SingletonHolder {
        private static final HttpUtil INSTANCE = new HttpUtil();
    }

    /**
     * 获取单例
     */
    public static HttpUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 添加线程管理并订阅
     * @param ob
     * @param subscriber
     * @param cacheKey 缓存kay
     * @param event Activity 生命周期
     * @param lifecycleSubject
     * @param isSave 是否缓存
     * @param forceRefresh 是否强制刷新
     */
    public void toSubscribe(Observable ob, final ProgressSubscriber subscriber, String cacheKey, final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject, boolean isSave, boolean forceRefresh,int type) {
        if (type == Constants.GEEKTYPE){
            //数据预处理
            Observable.Transformer<HttpResult<Object>, Object> result = RxHelper.handleResult(event,lifecycleSubject);
            Observable observable = ob.compose(result)
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            //显示Dialog和一些其他操作
                            subscriber.showProgressDialog();
                        }
                    });
            RetrofitCache.load(cacheKey,observable,isSave,forceRefresh).subscribe(subscriber);
        }else if (type == Constants.GANKTYPE){
            //数据预处理
            Observable.Transformer<GHttpResult<Object>, Object> result = RxHelper.handleResultG(event,lifecycleSubject);
            Observable observable = ob.compose(result)
                    .doOnSubscribe(new Action0() {
                        @Override
                        public void call() {
                            //显示Dialog和一些其他操作
                            subscriber.showProgressDialog();
                        }
                    });
            RetrofitCache.load(cacheKey,observable,isSave,forceRefresh).subscribe(subscriber);
        }

    }

}
