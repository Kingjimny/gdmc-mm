package com.JimnyLibs.android.listener;

import android.view.LayoutInflater;
import android.view.View;

import com.JimnyLibs.android.entity.JsonBase;

/**
 * Created by Jimny
 * on 14-7-27.
 */
public interface OnLoaderDelegateListener<T extends JsonBase> {

    /**
     * 获取发起网络请求的协议Url，同时用来做为持久缓存key使用
     *
     * @return 网络请求Url
     */
    String getUrlCacheKey();

    /**
     * 获取 Fragment layout resId
     *
     * @return Fragment layout resId
     */
    int getFragmentLayoutResId();

    /**
     * 获取 Fragment 正在加载中时候显示的View
     * 对于列表类，一般认为是EmptyView
     */
    View getFragmentLoadingView(LayoutInflater inflater, View rootView);

    /**
     * 当前界面正在获取缓存/网络内容
     *
     * @param isLoading
     * @param reset
     */
    void onLoadingContent(boolean isLoading, boolean reset);

    /**
     * 通过getUrlCacheKey()来获取当前界面缓存的内容，
     *
     * @param cacheJson 缓存的内容，一般为网络请求后的接口返回序列化而成的JSON字符串
     * @return 返回整个接口实体类数据结构
     */
    T onAsyncLoadCache(String cacheJson);

    /**
     * 从网络请求获取内容，运行于异步线程
     *
     * @param reset 是否本次请求是一个重置操作，例如列表下拉刷新最新内容，同时清空原有列表数据
     * @return 返回整个接口实体类数据结构
     */
    T onAsyncLoadNet(boolean reset);

    /**
     * 从缓存或者网络获取内容后返回需要对当前数据所做的更改
     *
     * @param isLoadFromNet
     * @param reqSuccess
     * @param reset
     * @param errCode
     * @param protocolJson  onAsyncLoadCache()/onAsyncLoadNet返回的接口内容
     */
    void onUpdateData(boolean isLoadFromNet, boolean reqSuccess, boolean reset, int errCode, T protocolJson);

    /**
     * 获取缓存或者网络内容后数据已经被更新了
     *
     * @param isLoadFromNet
     * @param isRequestSuccess
     */
    void onDataUpdated(boolean isLoadFromNet, boolean isRequestSuccess);

    /**
     * 判断当前数据是否有效，对于详情类一般是数据是否为空，若不为空则有效，也可以通过判断isInitialized()来确认是否网络请求成功
     *
     * @return
     */
    boolean isDataValid();

    /**
     * 当前数据无效，一般显示一个错误界面，供用户重试
     */
    void onDataInvalid();

    int getErrorLayoutResId();
}
