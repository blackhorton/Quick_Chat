package com.ming.quickchat.main.chat_person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ming.quickchat.R;
import com.ming.quickchat.util.preference.PreferencesUtil;
import com.ming.quickchat.util.time.TimeUtil;

import java.util.Date;
import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.UserInfo;


/**
 * @author ming
 */


public class ChatActivity extends FragmentActivity implements View.OnClickListener {

    public static final String COPY_IMAGE = "EASEMOBIMG";

    private InputMethodManager manager;

    private TextView mFromNickNameTv;

    private LinearLayout mMoreLl;
    private LinearLayout mEmojiIconContainerLl;
    private LinearLayout mBtnContainerLl;
    private ImageView mEmoticonsNormalIv;
    private ImageView mEmoticonsCheckedIv;

    private Button mMoreBtn;
    private Button mSendBtn;

    private View buttonSetModeVoice;
    private View buttonPressToSpeak;

    private EditText mEditTextContent;
    private RelativeLayout mEditTextRl;

    private ListView mMessageLv;
    private MessageAdapter messageAdapter;
    List<Message> messageList;

    // intent传值
    private String fromUserId;
    private String fromUserNickName;
    private String fromUserAvatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        JMessageClient.registerEventReceiver(this);
        PreferencesUtil.getInstance().init(this);
        initView();
        setUpView();
    }

    private void initView() {
        mFromNickNameTv = findViewById(R.id.tv_from_nick_name);

        mMessageLv = findViewById(R.id.list);

        mMoreLl = findViewById(R.id.more);
        mEmojiIconContainerLl = findViewById(R.id.ll_face_container);
        mBtnContainerLl = findViewById(R.id.ll_btn_container);
        mEmoticonsNormalIv = findViewById(R.id.iv_emoticons_normal);
        mEmoticonsCheckedIv = findViewById(R.id.iv_emoticons_checked);

        mMoreBtn = findViewById(R.id.btn_more);
        mSendBtn = findViewById(R.id.btn_send);

        buttonPressToSpeak = findViewById(R.id.btn_press_to_speak);
        buttonSetModeVoice = findViewById(R.id.btn_set_mode_voice);
        mEditTextContent = findViewById(R.id.et_sendmessage);
        mEditTextRl = findViewById(R.id.edittext_layout);

        mEditTextContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    mEditTextRl.setBackgroundResource(R.mipmap.input_bar_bg_active);
                } else {
                    mEditTextRl.setBackgroundResource(R.mipmap.input_bar_bg_normal);
                }
            }
        });

        mEditTextContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditTextRl.setBackgroundResource(R.mipmap.input_bar_bg_active);
                mMoreLl.setVisibility(View.GONE);
                mEmoticonsNormalIv.setVisibility(View.VISIBLE);
                mEmoticonsCheckedIv.setVisibility(View.GONE);
                mEmojiIconContainerLl.setVisibility(View.GONE);
                mBtnContainerLl.setVisibility(View.GONE);
            }
        });

        mEditTextContent.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (TextUtils.isEmpty(charSequence)) {
                    mMoreBtn.setVisibility(View.VISIBLE);
                    mSendBtn.setVisibility(View.GONE);
                } else {
                    mMoreBtn.setVisibility(View.GONE);
                    mSendBtn.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setUpView() {
//        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        fromUserId = getIntent().getStringExtra("fromUserId");
        fromUserNickName = getIntent().getStringExtra("fromUserNickName");
        fromUserAvatar = getIntent().getStringExtra("fromUserAvatar");
        //假数据
        fromUserId = "1111";
        fromUserNickName = "Hortons";
        fromUserAvatar = "https://www.baidu.com";

        mFromNickNameTv.setText(fromUserNickName);
        messageList = Message.findWithQuery(Message.class, "select * from message where from_user_id = ? or to_user_id = ? order by timestamp asc", fromUserId, fromUserId);

        messageAdapter = new MessageAdapter(this, messageList);
        mMessageLv.setAdapter(messageAdapter);

        mMessageLv.setSelection(mMessageLv.getCount() - 1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                String content = mEditTextContent.getText().toString();
                sendTextMsg(content);
                break;
        }
    }


    public void back(View view) {
        finish();
    }

    /**
     * 显示键盘图标
     *
     * @param view
     */
    public void setModeKeyboard(View view) {
        mEditTextRl.setVisibility(View.VISIBLE);
        mMoreLl.setVisibility(View.GONE);
        view.setVisibility(View.GONE);
        buttonSetModeVoice.setVisibility(View.VISIBLE);
        // mEditTextContent.setVisibility(View.VISIBLE);
        // buttonSend.setVisibility(View.VISIBLE);
        buttonPressToSpeak.setVisibility(View.GONE);

    }

    /**
     * 显示或隐藏图标按钮页
     *
     * @param view
     */
    public void more(View view) {
        if (mMoreLl.getVisibility() == View.GONE) {
            hideKeyboard();
            mMoreLl.setVisibility(View.VISIBLE);
            mBtnContainerLl.setVisibility(View.VISIBLE);
            mEmojiIconContainerLl.setVisibility(View.GONE);
        } else {
            if (mEmojiIconContainerLl.getVisibility() == View.VISIBLE) {
                mEmojiIconContainerLl.setVisibility(View.GONE);
                mBtnContainerLl.setVisibility(View.VISIBLE);
                mEmoticonsNormalIv.setVisibility(View.VISIBLE);
                mEmoticonsCheckedIv.setVisibility(View.INVISIBLE);
            } else {
                mMoreLl.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 点击文字输入框
     *
     * @param v
     */
    public void editClick(View v) {
        mMessageLv.setSelection(mMessageLv.getCount() - 1);
        if (mMoreLl.getVisibility() == View.VISIBLE) {
            mMoreLl.setVisibility(View.GONE);
            mEmoticonsNormalIv.setVisibility(View.VISIBLE);
            mEmoticonsCheckedIv.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * 隐藏软键盘
     */
    private void hideKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 发送文字消息
     *
     * @param content 消息内容
     */
    private void sendTextMsg(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setCreateTime(TimeUtil.getTimeStringAutoShort2(new Date().getTime(), true));
        message.setFromUserId(PreferencesUtil.getInstance().getUserId());
        message.setToUserId(fromUserId);
        message.setToUserName(fromUserNickName);
        message.setToUserAvatar(fromUserAvatar);
        message.setTimestamp(new Date().getTime());
        messageList.add(message);

        Message.save(message);

        messageAdapter.notifyDataSetChanged();
        mMessageLv.setSelection(mMessageLv.getCount() - 1);
        mEditTextContent.setText("");
    }

    /*接收到的消息*/
    public void onEvent(MessageEvent event) {
        final cn.jpush.im.android.api.model.Message msg = event.getMessage();
        Message message = new Message();
        message.setCreateTime(TimeUtil.getTimeStringAutoShort2(new Date().getTime(), true));
        UserInfo fromUserInfo = (UserInfo) msg.getTargetInfo();
        message.setFromUserId(fromUserInfo.getUserName());
        message.setFromUserName(fromUserNickName);

        List<Friend> friendList = Friend.find(Friend.class, "user_id = ?", message.getFromUserId());
        if (null != friendList && friendList.size() > 0) {
            message.setFromUserAvatar(friendList.get(0).getUserAvatar());
        }

        message.setToUserId(PreferencesUtil.getInstance().getUserId());
        TextContent messageContent = (TextContent) msg.getContent();
        message.setContent(messageContent.getText());
        message.setTimestamp(new Date().getTime());
        Message.save(message);

        // 获取会话
        Conversation conversation = JMessageClient.getSingleConversation(fromUserInfo.getUserName());
        // 如果是当前会话
        if (fromUserInfo.getUserName().equals(fromUserId)) {
            messageList.add(message);
            mMessageLv.post(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.setData(messageList);
                    messageAdapter.notifyDataSetChanged();
                    mMessageLv.smoothScrollToPosition(mMessageLv.getAdapter().getCount() - 1);
                }
            });
            // 清除未读数
            conversation.resetUnreadCount();
        } else {
            // 未读数++
//            conversation.setUnReadMessageCnt(conversation.getUnReadMsgCnt() + 1);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }
}
