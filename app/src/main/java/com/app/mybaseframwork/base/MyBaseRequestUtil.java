package com.app.mybaseframwork.base;

import com.vise.xsnow.common.GsonUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.io.File;
import java.util.HashMap;

import yin.deng.normalutils.utils.LogUtils;

public class MyBaseRequestUtil {
    public static int SUCCESS=200;
    private static MyBaseRequestUtil myBaseRequestUtil;
    public  static MyBaseRequestUtil getInstance(){
        if(myBaseRequestUtil==null){
            myBaseRequestUtil=new MyBaseRequestUtil();
        }
        return myBaseRequestUtil;
    }

    /**
     * 返回接口对象
     */
    public interface OnDataOkListener<T>{
        void onDataOk(T data);
        void onDataFail(int code,String msg);
    }

    //因网络状况引起的报错
    private void onNetErr(int code,String errMsg){

    }


    //是否需要对服务器错误码进行单独处理
    private <T extends BaseInfo> boolean needDealWithCode(T data) {
        if(data.code==-1) {
            return true;
        }
        return false;
    }

    //对特定的错误码进行单独处理
    private <T extends BaseInfo> void dealWithServerErro(T data) {

    }


    public <T extends BaseInfo>void sendPost(String apiUrl,HashMap<String,Object> params,OnDataOkListener dataOkListener) {
        ViseHttp.POST(apiUrl)
                .addParams(params)
                .request(new ACallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        if(data.code==SUCCESS) {
                            dataOkListener.onDataOk(data);
                        }else{
                            if(needDealWithCode(data)){
                                dealWithServerErro(data);
                            }else {
                                dataOkListener.onDataFail(data.code, data.msg);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }




    public <T extends BaseInfo>void sendPostJson(String apiUrl,Object params,OnDataOkListener dataOkListener) {
        String json=GsonUtil.gson().toJson(params);
        LogUtils.v("rx-http->传参：\n"+json);
        ViseHttp.POST(apiUrl)
                .setJson(json)
                .request(new ACallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        if(data.code==SUCCESS) {
                            dataOkListener.onDataOk(data);
                        }else{
                            if(needDealWithCode(data)){
                                dealWithServerErro(data);
                            }else {
                                dataOkListener.onDataFail(data.code, data.msg);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }


    public <T extends BaseInfo>void sendPost(String apiUrl,OnDataOkListener dataOkListener) {
        ViseHttp.POST(apiUrl)
                .request(new ACallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        if(data.code==SUCCESS) {
                            dataOkListener.onDataOk(data);
                        }else{
                            if(needDealWithCode(data)){
                                dealWithServerErro(data);
                            }else {
                                dataOkListener.onDataFail(data.code, data.msg);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }


    public <T extends BaseInfo>void upload(String apiUrl, HashMap<String, File> params,OnDataOkListener dataOkListener) {
        ViseHttp.UPLOAD(apiUrl)
                .addFiles(params)
                .request(new ACallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        if(data.code==SUCCESS) {
                            dataOkListener.onDataOk(data);
                        }else{
                            if(needDealWithCode(data)){
                                dealWithServerErro(data);
                            }else {
                                dataOkListener.onDataFail(data.code, data.msg);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }



    public <T extends BaseInfo>void sendGet(String apiUrl,HashMap<String,Object> params,OnDataOkListener dataOkListener) {
        ViseHttp.GET(apiUrl)
                .addParams(params)
                .request(new ACallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        if(data.code==SUCCESS) {
                            dataOkListener.onDataOk(data);
                        }else{
                            if(needDealWithCode(data)){
                                dealWithServerErro(data);
                            }else {
                                dataOkListener.onDataFail(data.code, data.msg);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }

    public <T extends BaseInfo>void sendGet(String apiUrl,OnDataOkListener dataOkListener) {
        ViseHttp.GET(apiUrl)
                .request(new ACallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        if(data.code==SUCCESS) {
                            dataOkListener.onDataOk(data);
                        }else{
                            if(needDealWithCode(data)){
                                dealWithServerErro(data);
                            }else {
                                dataOkListener.onDataFail(data.code, data.msg);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }

    public <T extends BaseInfo>void sendPut(String apiUrl,Object params,OnDataOkListener dataOkListener) {
        String json=GsonUtil.gson().toJson(params);
        LogUtils.v("rx-http->传参：\n"+json);
        ViseHttp.PUT(apiUrl)
                .setJson(json)
                .request(new ACallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        if(data.code==SUCCESS) {
                            dataOkListener.onDataOk(data);
                        }else{
                            if(needDealWithCode(data)){
                                dealWithServerErro(data);
                            }else {
                                dataOkListener.onDataFail(data.code, data.msg);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }

    public <T extends BaseInfo>void sendDelete(String apiUrl,HashMap<String,Object> params,OnDataOkListener dataOkListener) {
        ViseHttp.DELETE(apiUrl)
                .addParams(params)
                .request(new ACallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        if(data.code==SUCCESS) {
                            dataOkListener.onDataOk(data);
                        }else{
                            if(needDealWithCode(data)){
                                dealWithServerErro(data);
                            }else {
                                dataOkListener.onDataFail(data.code, data.msg);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }

    public <T extends BaseInfo>void sendDelete(String apiUrl,OnDataOkListener dataOkListener) {
        ViseHttp.DELETE(apiUrl)
                .request(new ACallback<T>() {
                    @Override
                    public void onSuccess(T data) {
                        if(data.code==SUCCESS) {
                            dataOkListener.onDataOk(data);
                        }else{
                            if(needDealWithCode(data)){
                                dealWithServerErro(data);
                            }else {
                                dataOkListener.onDataFail(data.code, data.msg);
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }
}
