package com.infomation.haiffeng.enity;

import java.util.List;

/**
 * Created by 黄海峰 on 2017/3/19.
 */

public class GankInformationBean {

    /**
     * _id : 58ca931a421aa90efc4fb6dc
     * createdAt : 2017-03-16T21:28:58.449Z
     * desc : 深入了解Java之垃圾回收
     * images : ["http://img.gank.io/57e7df17-30a4-4cab-9d3d-aa723a78eb4c"]
     * publishedAt : 2017-03-17T12:07:03.767Z
     * source : web
     * type : Android
     * url : http://itfeifei.win/2017/03/15/%E6%B7%B1%E5%85%A5%E4%BA%86%E8%A7%A3Java%E4%B9%8B%E5%9E%83%E5%9C%BE%E5%9B%9E%E6%94%B6/
     * used : true
     * who : null
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private Object who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Object getWho() {
        return who;
    }

    public void setWho(Object who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
