package com.vise.xsnow.http.request;

import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.core.ApiManager;
import com.vise.xsnow.http.mode.CacheResult;
import com.vise.xsnow.http.mode.MediaTypes;
import com.vise.xsnow.http.subscriber.ApiCallbackSubscriber;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @Description: Put请求
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 2017-04-28 16:06
 */
public class PutRequest extends BaseHttpRequest<PutRequest> {
    private String content;
    private MediaType mediaType;

    public PutRequest(String suffixUrl) {
        super(suffixUrl);
    }

    @Override
    protected <T> Observable<T> execute(Type type) {
        if(this.mediaType!=null&&content!=null&&!content.equals("")) {
            RequestBody requestBody = RequestBody.create(mediaType, content);
            return apiService.putBody(suffixUrl,requestBody).compose(this.<T>norTransformer(type,getNowReqeustUrl()));
        }
        return apiService.put(suffixUrl, params).compose(this.<T>norTransformer(type,getNowReqeustUrl()));
    }

    public PutRequest setJson(String json) {
        this.content = json;
        this.mediaType = MediaTypes.APPLICATION_JSON_TYPE;
        return this;
    }

    @Override
    protected <T> Observable<CacheResult<T>> cacheExecute(Type type) {
        return this.<T>execute(type).compose(ViseHttp.getApiCache().<T>transformer(cacheMode, type));
    }

    @Override
    protected <T> void execute(ACallback<T> callback) {
        DisposableObserver disposableObserver = new ApiCallbackSubscriber(callback);
        if (super.tag != null) {
            ApiManager.get().add(super.tag, disposableObserver);
        }
        if (isLocalCache) {
            this.cacheExecute(getSubType(callback)).subscribe(disposableObserver);
        } else {
            this.execute(getType(callback)).subscribe(disposableObserver);
        }
    }
}
