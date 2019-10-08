package com.ming.quickchat.main.chat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ming.quickchat.R;
import com.ming.quickchat.util.time.TimeUtil;
import com.ming.quickchat.util.time.TimeUtils;

import java.util.List;

import retrofit2.http.OPTIONS;


/**
 * @author Hortons
 * on 19-10-3
 */


public class ChatListAdapter extends BaseQuickAdapter<ChatListInfoEntity, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .centerCrop();

    public ChatListAdapter(int layoutResId, @Nullable List<ChatListInfoEntity> data) {
        super(layoutResId, data);
    }

    public ChatListAdapter(@Nullable List<ChatListInfoEntity> data) {
        super(data);
    }

    public ChatListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ChatListInfoEntity item) {
        helper.setText(R.id.tv_item_chat_name, item.getName());
        helper.setText(R.id.tv_item_chat_content, item.getContent());
        helper.setText(R.id.tv_item_chat_time, TimeUtil.getTime(item.getDate()));
        ImageView headImage = helper.itemView.findViewById(R.id.iv_item_chart_image_head);
        Glide.with(mContext)
                .load(item.getHeadUrl())
                .apply(OPTIONS)
                .into(headImage);
    }
}
