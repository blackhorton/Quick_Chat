package com.ming.quickchat.main;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ming.quickchat.R;
import com.ming.quickchat.app.Latte;
import com.ming.quickchat.util.icon.FontEcModule;
import com.ming.quickchat.net.interceptors.DebugInterceptor;
import com.orm.SugarContext;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import leakcanary.LeakCanary;

/**
 * @author Ming
 * on 2019/7/4
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        SugarContext.init(this);
        // 设置开启极光推送（JPush）的日志,发布时请关闭日志
        JPushInterface.setDebugMode(true);
        // 设置开启极光 IM （JMessage）的日志,发布时请关闭日志
        JMessageClient.setDebugMode(true);
        JPushInterface.init(this);
        JMessageClient.init(this, true);

        Latte.init(this)
                .withLoaderDelayed(1000)
                .withIcon(new FontEcModule())
                .withIcon(new FontAwesomeModule())
//                .withApiHost("http://127.0.0.1/")
                .withApiHost("http://192.168.125.103:80/RestService/api/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavascriptInterface("latte")
                .configure();
//        DatabaseManager.getInstance().init(this);
    }
}
