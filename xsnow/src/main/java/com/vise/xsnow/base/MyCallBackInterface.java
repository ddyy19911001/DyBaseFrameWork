package com.vise.xsnow.base;

import android.util.Log;

import com.vise.xsnow.http.callback.ACallback;

public interface MyCallBackInterface<T> {
     void onRequestSuccess(T data);
     void onRequestFail(int errCode, String errMsg);
}
