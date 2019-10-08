package com.ming.quickchat.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ming.quickchat.app.AccountManager;
import com.ming.quickchat.database.UserProfile;

/**
 * @author Hortons
 * on 2019/8/5
 */


public class SignHandler {

    private static final String TAG = SignHandler.class.getSimpleName();

    public static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        //插入用户信息到数据库里面
//        DatabaseManager.getInstance().getDao().insert(profile);

        //登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();
    }


    public static void onSignUp(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        //插入用户信息到数据库里面
//        UserProfileDao dao = DatabaseManager.getInstance().getDao();
//        if (dao.hasKey(profile)) {
//            dao.insert(profile);
//            //已经注册并登录成功
//            AccountManager.setSignState(true);
//            signListener.onSignUpSuccess();
//        } else {
//            Toast.makeText(Latte.getApplicationContext(), "注册失败，存在该用户", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "注册失败，存在该用户:" + profile.toString());
//        }

    }
}
