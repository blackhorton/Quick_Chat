package com.ming.quickchat.app;


import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.ming.quickchat.main.chat.UserInfoEnum;
import com.ming.quickchat.util.preference.PreferencesUtil;
import com.ming.quickchat.util.storage.LattePreference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * @author Hortons
 * on 8/8/19
 * 用户管理类
 */


public class AccountManager {

    private static final String TAG = "AccountManager";

    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存用户登录状态，登陆后调用
     * @param state 状态值
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }


    /**
     * 判断是否有登录过
     * @return
     */
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    /**
     * 检查是否有登录
     * @param checker   登录情况的回调接口
     */
    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }

    /**
     * 检查是否有登录
     * @param checker   登录情况的回调接口
     */
    public static void checkHasLogin(IUserChecker checker) {
        UserInfo userInfo = JMessageClient.getMyInfo();
//        List<UserInfo> data = new ArrayList<>();
        if (userInfo != null) {
            checker.onSignIn();
//            data.add(userInfo);
//            String json = UserInfo.collectionToJson(data);
//            JSONObject jsonObject = JSONObject.parseObject(json);
//            PreferencesUtil.getInstance().saveParam(UserInfoEnum.MY_USER_INFO.name(), userInfo);
//            Log.d("AccountManager", "json:" + json);
        } else {
            checker.onNotSignIn();
        }
    }


}
