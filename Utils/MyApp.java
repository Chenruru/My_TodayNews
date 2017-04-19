package test.bwie.com.my_todaynews.Utils;

import android.app.Application;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import org.xutils.x;


import cn.smssdk.SMSSDK;
import test.bwie.com.my_todaynews.Activity_.Xiangji;

/**
 * date: 2017/4/10
 * author:陈茹
 * 类的用途:
 */

public class MyApp extends Application {
    {
        PlatformConfig.setQQZone("1106036236", "mjFCi0oxXZKZEWJs");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false); //输出debug日志，开启会影响性能
        //  Utils.init(this);
        SMSSDK.initSDK(this, "1cffdc4766f7c", "\n" +
                "70ea233dc562d47db298f7438892a7cb");
        Config.DEBUG = true;
        UMShareAPI.get(this);
    }


}
