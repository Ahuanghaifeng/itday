package com.infomation.haiffeng.enity;

/**
 * Created by helin on 2016/10/10 11:44.
 * 实体的基类
 */
public class GHttpResult<T> {

    private String error;
    private T results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
