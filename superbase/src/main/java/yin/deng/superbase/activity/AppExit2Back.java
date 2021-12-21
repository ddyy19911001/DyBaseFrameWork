package yin.deng.superbase.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Timer;
import java.util.TimerTask;

import yin.deng.normalutils.utils.BaseToastUtil;
import yin.deng.superbase.R;

/**
 * 主要功能：App应用退出
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月04日 14:13
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
public class AppExit2Back {


	private static Boolean isExit = false;

	/**
	 * 退出App程序应用
	 * @param  context 上下文
	 * @return boolean True退出|False提示
	 */
	public static boolean exitApp(Context context) {
	    Timer tExit = null;
	    if (isExit == false) {
	        isExit = true;
	        //信息提示
			new BaseToastUtil(context,context.getResources().getString(R.string.exit_twice_tips)).show();
	        //创建定时器
	        tExit = new Timer();
	        //如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
	        tExit.schedule(new TimerTask() {
	            @Override
	            public void run() {
	            	//取消退出
	                isExit = false;
	            }
	        }, 2000);
	    } else {
        	AppActivityListManager.getScreenManager().removeAllActivity();
        	//创建ACTION_MAIN
        	Intent intent = new Intent(Intent.ACTION_MAIN);
        	intent.addCategory(Intent.CATEGORY_HOME);
        	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	Context content = ((Activity) context);
        	//启动ACTION_MAIN
        	content.startActivity(intent);
        	android.os.Process.killProcess(android.os.Process.myPid());
	    }
		LogUtils.i("AppExit2Back-->>exitApp"+ isExit + "");
		LogUtils.i("AppExit2Back-->>exitApp"+ "最大内存：" + Runtime.getRuntime().maxMemory());
		LogUtils.i("AppExit2Back-->>exitApp"+ "占用内存：" + Runtime.getRuntime().totalMemory());
		LogUtils.i("AppExit2Back-->>exitApp"+ "空闲内存：" + Runtime.getRuntime().freeMemory());
	    	return isExit;
	}
}
