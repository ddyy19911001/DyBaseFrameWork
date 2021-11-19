package com.app.mybaseframwork.base;

import com.app.mybaseframwork.BuildConfig;
import com.dy.fastframework.application.SuperBaseApp;

public class BaseApp extends SuperBaseApp {
    @Override
    protected String setBaseUrl() {
        return BuildConfig.HOST_URL;
    }

    @Override
    public boolean closeDebugLog() {
        return false;
    }
}
