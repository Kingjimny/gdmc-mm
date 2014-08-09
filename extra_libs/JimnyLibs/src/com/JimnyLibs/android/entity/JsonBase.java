package com.JimnyLibs.android.entity;

/**
 * Created by Jimny
 * on 14-7-27.
 */
public interface JsonBase<T> {

    public T getData();

    public void setData(T data);

    public int getCode();

    public void setCode(int code);

    public String getMsg();

    public void setMsg(String msg);

    public String toCacheString();
}
