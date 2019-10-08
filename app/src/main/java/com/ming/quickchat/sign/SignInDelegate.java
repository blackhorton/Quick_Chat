package com.ming.quickchat.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joanzapata.iconify.widget.IconTextView;
import com.ming.quickchat.R;
import com.ming.quickchat.app.AccountManager;
import com.ming.quickchat.delegates.LatteDelegate;
import com.ming.quickchat.net.RestClient;
import com.ming.quickchat.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * @author Hortons
 * on 2019/8/4
 */


public class SignInDelegate extends LatteDelegate {

    private static final String TAG = "SignInDelegate";


    @BindView(R.id.edit_sign_in_username)
    TextInputEditText editSignInUsername;
    @BindView(R.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;
    @BindView(R.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R.id.tv_link_sign_up)
    AppCompatTextView tvLinkSignUp;
    @BindView(R.id.icon_sig_in_wechat)
    IconTextView iconSigInWechat;
    Unbinder unbinder;

    private ISignListener mSignListener = null;

    private String username;
    private String password;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mSignListener = (ISignListener) activity;
            Log.d(TAG, "onAttach()");
        }
    }

    @OnClick(R.id.btn_sign_in)
    public void onClickBtnSignIn() {
        if (checkForm()) {
            JMessageClient.login(username, password, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    Log.d(TAG, "i:" + i + " message:" + s);
                    switch (i) {
                        case 0 :
//                            AccountManager.setSignState(true);
                            mSignListener.onSignInSuccess();
                            Toast.makeText(_mActivity, "登陆成功", Toast.LENGTH_SHORT).show();
                            UserInfo userInfo = JMessageClient.getMyInfo();
                            Log.d(TAG, "userInfo:" + userInfo.toString());
                            break;
                        case 801004:
                            Toast.makeText(_mActivity, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(_mActivity, "错误代码：" + i + " 错误信息：" + s, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }


    }

    @OnClick(R.id.icon_sig_in_wechat)
    public void onClickWeChat() {
    }

    @OnClick(R.id.tv_link_sign_up)
    public void onClick() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        username = editSignInUsername.getText().toString();
        password = editSignInPassword.getText().toString();

        boolean isPass = true;

        if (username.isEmpty()) {
            editSignInUsername.setError("请输入用户名");
            isPass = false;
        } else {
            editSignInUsername.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignInPassword.setError("请输入至少6位数密码");
            isPass = false;
        } else {
            editSignInPassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

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

}
