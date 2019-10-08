package com.ming.quickchat.main.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ming.quickchat.R;
import com.ming.quickchat.delegates.bottom.BottomItemDelegate;
import com.ming.quickchat.main.chat.UserInfoEnum;
import com.ming.quickchat.util.preference.PreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * @author Hortons
 * on 19-10-5
 */


public class ProfileDelegate extends BottomItemDelegate {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_id)
    TextView tvId;

    @BindView(R.id.iv_sex)
    ImageView ivSex;
    Unbinder unbinder;

    private UserInfo userInfo;
    private PreferencesUtil preferencesUtil;


    @Override
    public Object setLayout() {
        return R.layout.delegate_profile;
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        preferencesUtil = PreferencesUtil.getInstance();
//        userInfo = (UserInfo) preferencesUtil.getObject(UserInfoEnum.MY_USER_INFO.name());
//        tvName.setText(userInfo.getNickname());
//        tvId.setText(String.valueOf(userInfo.getUserID()));
//        if (userInfo.getGender().equals(UserInfo.Gender.male)) {
//            ivSex.setImageResource(R.mipmap.ic_sex_male);
//            ivSex.setVisibility(View.VISIBLE);
//        } else if (userInfo.getGender().equals(UserInfo.Gender.female)){
//            ivSex.setImageResource(R.mipmap.ic_sex_female);
//            ivSex.setVisibility(View.VISIBLE);
//        } else {
//            ivSex.setImageResource(R.mipmap.ic_sex_female);
//            ivSex.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
