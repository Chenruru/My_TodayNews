package test.bwie.com.my_todaynews.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * date: 2017/4/12
 * author:陈茹
 * 类的用途:
 */

public class NextUtil {
    private Context context;
    //判断是不是WiFi
    public static boolean isWiFi(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null&&info.getType()==manager.TYPE_WIFI){
            return true;
        }
        return false;
    }
    //判断是不是流量
    public static boolean isMobile(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info!=null&&info.getType()==manager.TYPE_MOBILE){
            return true;
        }
        return false;
    }
}
