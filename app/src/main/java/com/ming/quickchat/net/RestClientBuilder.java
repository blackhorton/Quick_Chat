package com.ming.quickchat.net;

import android.content.Context;


import com.ming.quickchat.net.callback.IError;
import com.ming.quickchat.net.callback.IFailure;
import com.ming.quickchat.net.callback.IRequest;
import com.ming.quickchat.net.callback.ISuccess;
import com.ming.quickchat.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Hortons
 * on 2019/7/25
 */


public class RestClientBuilder {

    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    /**
     * 命名规则
     * 类变量变量名前加上m
     */
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;


    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 强制限定传入参数类型为WeakHashMap，避免检查
     *
     * @param params
     * @return
     */
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    /**
     * 传入原始数据
     *
     * @param raw
     * @return
     */
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(raw, MediaType.parse("application/json;charset=UTF-8"));
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFalure) {
        this.mIFailure = iFalure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

//    public final RestClientBuilder context(Context context) {
//        this.mContext = context;
//        return this;
//    }
//
//    public final RestClientBuilder loaderStyle(LoaderStyle loaderStyle) {
//        this.mLoaderStyle = loaderStyle;
//        return this;
//    }

    private Map<String, Object> checkParams() {
        if (PARAMS == null) {
            return new WeakHashMap<>();
        }
        return PARAMS;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    /**
     *  使用默认的Loader
     * @param context
     * @return
     */
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS,
                mDownloadDir, mExtension, mName,
                mIRequest, mISuccess, mIFailure,
                mIError, mBody, mFile, mContext,
                mLoaderStyle);
    }
}
