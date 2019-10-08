package com.ming.quickchat.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ming.quickchat.R;
import com.ming.quickchat.app.AccountManager;
import com.ming.quickchat.app.IUserChecker;
import com.ming.quickchat.delegates.LatteDelegate;
import com.ming.quickchat.ui.launcher.ILauncherListener;
import com.ming.quickchat.ui.launcher.OnLauncherFinishTag;
import com.ming.quickchat.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;


/**
 * @author Hortons
 * on 2019/8/2
 */


public class LauncherDelegate extends LatteDelegate {


    @BindView(R.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;
    Unbinder unbinder;
    private Timer mTimer = null;

    private ScheduledThreadPoolExecutor executor;

    /**
     * 倒计时时间总数
     */
    private int mCount = 3;

    private ILauncherListener mILauncherListener;

    @OnClick(R.id.tv_launcher_timer)
    public void onClick() {
        checkIsShowScroll();
        executor.shutdown();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    /**
     * 初始化倒计时
     */
    private void initTimer() {
        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                getProxyActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mTvTimer != null) {
                            //注意此处的pattern
                            mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                            mCount--;
                            if (mCount < 0) {
                                checkIsShowScroll();
                            }
                        }
                    }
                });
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 判断是否显示滑动启动页
     */
    private void checkIsShowScroll() {
//        //判断是否第一次启动应用
//        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
//            //sharePreference里面没有HAS_FIRST_LAUNCHER_APP的键值对，判断为第一次启动app，并启动滚动页
//            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);

//        AccountManager.checkAccount(new IUserChecker() {
//            @Override
//            public void onSignIn() {
//                if (mILauncherListener != null) {
//                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
//                }
//            }
//
//            @Override
//            public void onNotSignIn() {
//                if (mILauncherListener != null) {
//                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
//                }
//            }
//        });

        AccountManager.checkHasLogin(new IUserChecker() {
            @Override
            public void onSignIn() {
                if (mILauncherListener != null) {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                }
            }

            @Override
            public void onNotSignIn() {
                if (mILauncherListener != null) {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                }
            }
        });
    }

}
