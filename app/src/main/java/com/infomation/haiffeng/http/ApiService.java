package com.infomation.haiffeng.http;


import com.infomation.haiffeng.enity.GHttpResult;
import com.infomation.haiffeng.enity.GankInformationBean;
import com.infomation.haiffeng.enity.HttpResult;
import com.infomation.haiffeng.enity.InformationBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by helin on 2016/10/9 17:09.
 */

public interface ApiService {

    @GET("github/repo_list")
    Observable<HttpResult<List<InformationBean>>> getGitInformation();

    @GET("hacker/news")
    Observable<HttpResult<List<InformationBean>>> getHackerInformation();

    @GET("segmentfault/blogs")
    Observable<HttpResult<List<InformationBean>>> getSegmentInformation();

    @GET("jobbole/news")
    Observable<HttpResult<List<InformationBean>>> getJobboleInformation();

    @GET("toutiao/posts")
    Observable<HttpResult<List<InformationBean>>> getToutiaoInformation();

    @GET("Android/10/{page}")
    Observable<GHttpResult<List<GankInformationBean>>> getGAndroidInformation(@Path("page") String page);

    @GET("iOS/10/{page}")
    Observable<GHttpResult<List<GankInformationBean>>> getGIOSInformation(@Path("page") String page);

    @GET("all/10/{page}")
    Observable<GHttpResult<List<GankInformationBean>>> getGAllInformation(@Path("page") String page);

    @GET("福利/10/{page}")
    Observable<GHttpResult<List<GankInformationBean>>> getGalleryInformation(@Path("page") String page);
}
