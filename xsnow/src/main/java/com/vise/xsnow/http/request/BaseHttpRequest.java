package com.vise.xsnow.http.request;

import android.text.TextUtils;
import android.util.Log;

import com.vise.xsnow.common.GsonUtil;
import com.vise.xsnow.common.ViseConfig;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.api.ApiService;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.func.ApiFunc;
import com.vise.xsnow.http.func.ApiRetryFunc;
import com.vise.xsnow.http.mode.ApiHost;
import com.vise.xsnow.http.mode.CacheMode;
import com.vise.xsnow.http.mode.CacheResult;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @Description: 通用的请求基类
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 17/7/22 15:23.
 */
public abstract class BaseHttpRequest<R extends BaseHttpRequest> extends BaseRequest<R> {

    protected ApiService apiService;//通用接口服务
    protected String suffixUrl = "";//链接后缀
    protected int retryDelayMillis;//请求失败重试间隔时间
    protected int retryCount;//重试次数
    protected boolean isLocalCache;//是否使用本地缓存
    protected CacheMode cacheMode;//本地缓存类型
    protected String cacheKey;//本地缓存Key
    protected long cacheTime;//本地缓存时间
    public Map<String, Object> params = new LinkedHashMap<>();//请求参数
    private String logTag;

    public BaseHttpRequest() {

    }

    public BaseHttpRequest(String suffixUrl) {
        if (!TextUtils.isEmpty(suffixUrl)) {
            this.suffixUrl = suffixUrl;
        }
    }

    public <T> Observable<T> request(Type type) {
        generateGlobalConfig();
        generateLocalConfig();
        printLog();
        return execute(type);
    }

    private void printLog() {

    }

    public <T> Observable<CacheResult<T>> cacheRequest(Type type) {
        generateGlobalConfig();
        generateLocalConfig();
        return cacheExecute(type);
    }


    /**
     * 此处设置展示log日志的内容
     * @return
     */
    SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
    public <T> void request(ACallback<T> callback) {
        generateGlobalConfig();
        generateLocalConfig();
        printRequestLog();
        execute(callback);
    }

    public void printRequestLog() {
        String paramsStr=this.params.size()==0?"null": GsonUtil.gson().toJson(this.params);
        String headerStr=((this.headers.headersMap==null||this.headers.headersMap.size()==0)?"null": this.headers.toJSONString());
        httpGlobalConfig.startTimer((this.baseUrl==null?httpGlobalConfig.getBaseUrl():this.baseUrl)+suffixUrl+"/params="+paramsStr);
        logTag=httpGlobalConfig.getTag();
        String url = (this.baseUrl == null ? httpGlobalConfig.getBaseUrl() : this.baseUrl) + suffixUrl;
        String requestBodyLog="（请求发送："+url+"）\n请求地址："+url+"\n"+"请求时间："+format.format(new Date())+"\n请求头："+headerStr+"\n请求参数："+paramsStr;
        Log.i(logTag, requestBodyLog);
    }


    public String getNowReqeustUrl(){
        return (this.baseUrl==null?httpGlobalConfig.getBaseUrl():this.baseUrl)+suffixUrl;
    }




    @Override
    protected void generateLocalConfig() {
        super.generateLocalConfig();
        if (httpGlobalConfig.getGlobalParams() != null) {
            params.putAll(httpGlobalConfig.getGlobalParams());
        }
        if (retryCount <= 0) {
            retryCount = httpGlobalConfig.getRetryCount();
        }
        if (retryDelayMillis <= 0) {
            retryDelayMillis = httpGlobalConfig.getRetryDelayMillis();
        }
        if (isLocalCache) {
            if (cacheKey != null) {
                ViseHttp.getApiCacheBuilder().cacheKey(cacheKey);
            } else {
                ViseHttp.getApiCacheBuilder().cacheKey(ApiHost.getHost());
            }
            if (cacheTime > 0) {
                ViseHttp.getApiCacheBuilder().cacheTime(cacheTime);
            } else {
                ViseHttp.getApiCacheBuilder().cacheTime(ViseConfig.CACHE_NEVER_EXPIRE);
            }
        }
        if (baseUrl != null && isLocalCache && cacheKey == null) {
            ViseHttp.getApiCacheBuilder().cacheKey(baseUrl);
        }
        apiService = retrofit.create(ApiService.class);
    }

    protected abstract <T> Observable<T> execute(Type type);

    protected abstract <T> Observable<CacheResult<T>> cacheExecute(Type type);

    protected abstract <T> void execute(ACallback<T> callback);

    protected <T> ObservableTransformer<ResponseBody, T> norTransformer(final Type type, final String requestUrl) {
        return new ObservableTransformer<ResponseBody, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResponseBody> apiResultObservable) {
                String paramsStr=params.size()==0?"null": GsonUtil.gson().toJson(params);
                return apiResultObservable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .map(new ApiFunc<T>(type,requestUrl+"/params="+paramsStr))
                        .observeOn(AndroidSchedulers.mainThread())
                        .retryWhen(new ApiRetryFunc(retryCount, retryDelayMillis));
            }
        };
    }

    /**
     * 添加请求参数
     *
     * @param paramKey
     * @param paramValue
     * @return
     */
    public R addParam(String paramKey, String paramValue) {
        if (paramKey != null && paramValue != null) {
            this.params.put(paramKey, paramValue);
        }
        return (R) this;
    }

    /**
     * 添加请求参数
     *
     * @param params
     * @return
     */
    public R addParams(Map<String, Object> params) {
        if (params != null) {
            this.params.putAll(params);
        }
        return (R) this;
    }

    /**
     * 移除请求参数
     *
     * @param paramKey
     * @return
     */
    public R removeParam(String paramKey) {
        if (paramKey != null) {
            this.params.remove(paramKey);
        }
        return (R) this;
    }

    /**
     * 设置请求参数
     *
     * @param params
     * @return
     */
    public R params(Map<String, Object> params) {
        if (params != null) {
            this.params = params;
        }
        return (R) this;
    }

    /**
     * 设置请求失败重试间隔时间（毫秒）
     *
     * @param retryDelayMillis
     * @return
     */
    public R retryDelayMillis(int retryDelayMillis) {
        this.retryDelayMillis = retryDelayMillis;
        return (R) this;
    }

    /**
     * 设置请求失败重试次数
     *
     * @param retryCount
     * @return
     */
    public R retryCount(int retryCount) {
        this.retryCount = retryCount;
        return (R) this;
    }

    /**
     * 设置是否进行本地缓存
     *
     * @param isLocalCache
     * @return
     */
    public R setLocalCache(boolean isLocalCache) {
        this.isLocalCache = isLocalCache;
        return (R) this;
    }

    /**
     * 设置本地缓存类型
     *
     * @param cacheMode
     * @return
     */
    public R cacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
        return (R) this;
    }

    /**
     * 设置本地缓存Key
     *
     * @param cacheKey
     * @return
     */
    public R cacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
        return (R) this;
    }

    /**
     * 设置本地缓存时间(毫秒)，默认永久
     *
     * @param cacheTime
     * @return
     */
    public R cacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
        return (R) this;
    }

    public String getSuffixUrl() {
        return suffixUrl;
    }

    public int getRetryDelayMillis() {
        return retryDelayMillis;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public boolean isLocalCache() {
        return isLocalCache;
    }

    public CacheMode getCacheMode() {
        return cacheMode;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public Map<String, Object> getParams() {
        return params;
    }

}
