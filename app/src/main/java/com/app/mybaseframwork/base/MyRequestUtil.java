package com.app.mybaseframwork.base;

import com.vise.xsnow.common.GsonUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.io.File;
import java.util.HashMap;

import yin.deng.normalutils.utils.LogUtils;

public class MyRequestUtil {
    public static int SUCCESS=10000;
    public static boolean needVerifySuccessCode=true;//是否需要验证服务器错误码是否成功
    private static MyRequestUtil myRequestUtil;
    public  static MyRequestUtil getInstance(){
        if(myRequestUtil ==null){
            myRequestUtil =new MyRequestUtil();
        }
        return myRequestUtil;
    }

    /**
     * 数据返回的抽象类
     */
    public abstract static class OnDataOkListener<T>{
        public abstract void onDataOk(T data);
        //不重写遇到服务器返回错误直接吐司
        public  void onDataFail(int code,String msg){
            MyToastUtil.show(msg);
        }
    }

    //因网络状况引起的报错
    private void onNetErr(int code,String errMsg){

    }


    //是否需要对服务器错误码进行单独处理
    private <T extends BaseInfo> boolean needDealWithCode(T data) {
        /**
         * 这里是对服务器错误码单独处理的逻辑
         */
        return false;
    }

    //对特定的错误码进行单独处理
    private <T extends BaseInfo> void dealWithServerErro(T data) {

    }


    /**
     * 处理服务器返回的数据
     * @param data
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void dealWithSuccessData(Object data,OnDataOkListener<T> dataOkListener,Class<T> classOfT){
        String jsonStr = GsonUtil.gson().toJson(data);
        T obj=GsonUtil.gson().fromJson(jsonStr,classOfT);
        if(!needVerifySuccessCode){
            dataOkListener.onDataOk(obj);
            return;
        }
        if(obj.code==SUCCESS) {
            dataOkListener.onDataOk(obj);
        }else{
            if(needDealWithCode(obj)){
                dealWithServerErro(obj);
            }else {
                dataOkListener.onDataFail(obj.code, obj.msg);
            }
        }
    }


    /**
     * hashmap参数类型的Post请求
     * @param apiUrl
     * @param params
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void sendPost(String apiUrl,Class<T> tClass,HashMap<String,Object> params,OnDataOkListener<T> dataOkListener) {
        ViseHttp.POST(apiUrl)
                .addParams(params)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        dealWithSuccessData(data,dataOkListener,tClass);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }


    /**
     * json参数类型的post请求
     * @param apiUrl
     * @param params
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void sendPostJson(String apiUrl,Class<T> tClass,Object params,OnDataOkListener<T> dataOkListener) {
        String json=GsonUtil.gson().toJson(params);
        ViseHttp.POST(apiUrl)
                .setJson(json)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        dealWithSuccessData(data,dataOkListener,tClass);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }


    /**
     * 无参post请求
     * @param apiUrl
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void sendPost(String apiUrl,Class<T> tClass,OnDataOkListener<T> dataOkListener) {
        ViseHttp.POST(apiUrl)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        dealWithSuccessData(data,dataOkListener,tClass);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }


    /**
     * 上传post请求
     * @param apiUrl
     * @param params
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void sendUpload(String apiUrl,Class<T> tClass, HashMap<String, File> params,OnDataOkListener<T> dataOkListener) {
        ViseHttp.UPLOAD(apiUrl)
                .addFiles(params)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        dealWithSuccessData(data,dataOkListener,tClass);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }


    /**
     * hashmap参数类型get请求
     * @param apiUrl
     * @param params
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void sendGet(String apiUrl,Class<T> tClass,HashMap<String,Object> params,OnDataOkListener<T> dataOkListener) {
        ViseHttp.GET(apiUrl)
                .addParams(params)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        dealWithSuccessData(data,dataOkListener,tClass);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }

    /**
     * 无参get请求
     * @param apiUrl
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void sendGet(String apiUrl,Class<T> tClass,OnDataOkListener<T> dataOkListener) {
        ViseHttp.GET(apiUrl)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        dealWithSuccessData(data,dataOkListener,tClass);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }

    /**
     * json类型参数的put请求
     * @param apiUrl
     * @param params
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void sendPut(String apiUrl,Class<T> tClass,Object params,OnDataOkListener<T> dataOkListener) {
        String json=GsonUtil.gson().toJson(params);
        ViseHttp.PUT(apiUrl)
                .setJson(json)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        dealWithSuccessData(data,dataOkListener,tClass);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }


    /**
     * hashmap类型的delete请求
     * @param apiUrl
     * @param params
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void sendDelete(String apiUrl,Class<T> tClass,HashMap<String,Object> params,OnDataOkListener<T> dataOkListener) {
        ViseHttp.DELETE(apiUrl)
                .addParams(params)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        dealWithSuccessData(data,dataOkListener,tClass);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }


    /**
     * 无参类型的delete请求
     * @param apiUrl
     * @param dataOkListener
     * @param <T>
     */
    public <T extends BaseInfo>void sendDelete(String apiUrl,Class<T> tClass,OnDataOkListener<T> dataOkListener) {
        ViseHttp.DELETE(apiUrl)
                .request(new ACallback<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        dealWithSuccessData(data,dataOkListener,tClass);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        onNetErr(errCode,errMsg);
                    }
                });
    }
}
