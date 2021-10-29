package com.dy.fastframework.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dy.fastframework.R;


public class MyImageLoadUtil {
    public static int mPlaceHolder;
    public static int mErroHolder;




    private static void checkInit(int placeHolder,int erroHolder){
        mPlaceHolder = placeHolder;
        mErroHolder = erroHolder;
    }



    /**
     *初始化配置之后使用
     * @param view
     * @param picUrl
     */
    public static void loadImage(Context context,ImageView view, String picUrl){
        checkInit(mPlaceHolder,mErroHolder);
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(context).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(context).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(context).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else{
            Glide.with(context).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }
    }

    public static void loadImage(Context mContext,ImageView view, int picUrl){
        checkInit(mPlaceHolder,mErroHolder);
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }
    }

    public static void loadImageThumb(Context mContext,ImageView view, int picUrl){
        checkInit(mPlaceHolder,mErroHolder);
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }
    }

    public static void loadImageThumb(Context mContext,ImageView view, String picUrl){
        checkInit(mPlaceHolder,mErroHolder);
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }
    }

    public static void loadImageThumb(Context mContext,ImageView view, String picUrl, int placeholder){
        checkInit(placeholder,placeholder);
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }
    }

    public static void loadImageThumb(Context mContext,ImageView view, String picUrl, int placeholder, int erroHolder){
        checkInit(placeholder,erroHolder);
        if(erroHolder!=0){
            mErroHolder=erroHolder;
        }
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).override(view.getWidth()).into(view);
        }
    }


    public static void loadImage(Context mContext,ImageView view, int picUrl, int placeHolder, int erroHolder){
        checkInit(placeHolder,erroHolder);
        if(mPlaceHolder!=0&&mErroHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else if(mPlaceHolder==0&&mErroHolder==0){
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else if(mPlaceHolder==0&&mErroHolder!=0){
            Glide.with(mContext).load(picUrl).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else{
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }
    }

    public static void loadImage(Context mContext,ImageView view, int picUrl, int placeHolder){
        checkInit(placeHolder,placeHolder);
        if(mPlaceHolder!=0) {
            Glide.with(mContext).load(picUrl).placeholder(mPlaceHolder).error(mErroHolder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }else{
            Glide.with(mContext).load(picUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(view);
        }
    }



}
