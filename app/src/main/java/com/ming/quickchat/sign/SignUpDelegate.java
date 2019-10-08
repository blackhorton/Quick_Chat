package com.ming.quickchat.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
import cn.jpush.im.android.api.options.RegisterOptionalUserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * @author Hortons
 * on 2019/8/3
 */


public class SignUpDelegate extends LatteDelegate {

    private static final String TAG = SignUpDelegate.class.getSimpleName();


    @BindView(R.id.edit_sign_up_username)
    TextInputEditText editSignUpName;
    @BindView(R.id.edit_sign_up_nick_name)
    TextInputEditText editSignUpNickName;
    @BindView(R.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R.id.btn_sign_up)
    Button btnSignUp;
    @BindView(R.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;
    Unbinder unbinder;

    private ISignListener mISignListener = null;

    private String name;
    private String nickname;
    private String password;
    private String rePassword;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;

        }
    }

    @OnClick(R.id.btn_sign_up)
    public void onClickSignUp() {
        if (checkForm()) {
            RegisterOptionalUserInfo userInfo = new RegisterOptionalUserInfo();
            userInfo.setNickname(nickname);
            JMessageClient.register(name, password, userInfo, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    Log.d(TAG, "i:" + i + " message:" + s);

                    switch (i) {
                        case 0:
//                            AccountManager.setSignState(true);
                            mISignListener.onSignUpSuccess();
                            break;
                        case 898001:
                            editSignUpName.setError("用户名已存在，请重新输入");
                            Toast.makeText(_mActivity, "用户名已存在，请输入重新输入", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(_mActivity, "错误代码：" + i + " 错误信息：" + s , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @OnClick(R.id.tv_link_sign_in)
    public void onClick() {
        getSupportDelegate().start(new SignInDelegate());
    }


    private boolean checkForm() {
        name = editSignUpName.getText().toString();
        nickname = editSignUpNickName.getText().toString();
        password = editSignUpPassword.getText().toString();
        rePassword = editSignUpRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            editSignUpName.setError("请输入用户名");
            isPass = false;
        } else {
            editSignUpName.setError(null);
        }

        if (nickname.isEmpty()) {
            editSignUpNickName.setError("请输入昵称");
            isPass = false;
        } else {
            editSignUpNickName.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignUpPassword.setError("请输入至少6位数密码");
            isPass = false;
        } else {
            editSignUpPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            editSignUpRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            editSignUpRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
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
