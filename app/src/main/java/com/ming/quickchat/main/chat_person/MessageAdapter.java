package com.ming.quickchat.main.chat_person;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.ming.quickchat.R;
import com.ming.quickchat.util.preference.PreferencesUtil;

import java.util.List;

public class MessageAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    private List<Message> messageList;

    public MessageAdapter(Context context, List<Message> messageList) {
        inflater = LayoutInflater.from(context);
        PreferencesUtil.getInstance().init(context);
        this.messageList = messageList;
    }

    public void setData(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = messageList.get(position);

        if (PreferencesUtil.getInstance().getUserId().equals(message.getFromUserId())) {
            convertView = inflater.inflate(R.layout.item_sent_message, null);
        } else {
            convertView = inflater.inflate(R.layout.item_received_message, null);
        }
        TextView mTimeStampTv = convertView.findViewById(R.id.timestamp);
        TextView mContentTv = convertView.findViewById(R.id.tv_chatcontent);
        ImageView mAvatarIv = convertView.findViewById(R.id.iv_avatar);

        mTimeStampTv.setText(message.getCreateTime());
        mContentTv.setText(message.getContent());

        if (PreferencesUtil.getInstance().getUserId().equals(message.getFromUserId())) {
            if (!TextUtils.isEmpty(PreferencesUtil.getInstance().getUserAvatar())) {
                mAvatarIv.setImageURI(Uri.parse(PreferencesUtil.getInstance().getUserAvatar()));
            }
        } else {
            if (!TextUtils.isEmpty(message.getFromUserAvatar())) {
                Glide.with(parent.getContext())
                        .load(Uri.parse(message.getFromUserAvatar()))
                        .into(mAvatarIv);
//                mAvatarIv.setImageURI(Uri.parse(message.getFromUserAvatar()));
            }
        }

        if (position != 0) {
            Message lastMessage = messageList.get(position - 1);
            if (message.getCreateTime().equals(lastMessage.getCreateTime())) {
                mTimeStampTv.setVisibility(View.GONE);
            }
        }

        return convertView;
    }

    public static class ViewHolder {
        ImageView iv;
        TextView tv;
        ProgressBar pb;
        ImageView staus_iv;
        ImageView head_iv;
        TextView tv_userId;
        ImageView playBtn;
        TextView timeLength;
        TextView size;
        LinearLayout container_status_btn;
        LinearLayout ll_container;
        ImageView iv_read_status;
        // 显示已读回执状态
        TextView tv_ack;
        // 显示送达回执状态
        TextView tv_delivered;

        TextView tv_file_name;
        TextView tv_file_size;
        TextView tv_file_download_state;
    }
}
