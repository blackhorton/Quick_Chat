package com.ming.quickchat.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;


import com.ming.quickchat.activity.ProxyActivity;
import com.ming.quickchat.app.Latte;
import com.ming.quickchat.delegates.LatteDelegate;
import com.ming.quickchat.sign.ISignListener;
import com.ming.quickchat.sign.SignInDelegate;
import com.ming.quickchat.sign.SignUpDelegate;
import com.ming.quickchat.ui.launcher.ILauncherListener;
import com.ming.quickchat.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

/**
 * @author Ming
 * on 2019/7/4
 */
public class ExampleActivity extends ProxyActivity implements
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
//        StatusBarCompat.translucentStatusBar(this, false);
        StatusBarCompat.setStatusBarColor(this, Color.WHITE, 100);
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
        return new LauncherDelegate();
//        return new LauncherScrollDelegate();
//        return new SignInDelegate();
//        return new SignUpDelegate();

    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new EcBottomDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().startWithPop(new SignInDelegate());
    }

    /**
     * 轮播图回调接口
     *
     * @param tag
     */
    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户已登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                //启动到栈顶，清除之前的fragment
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
