package com.ming.quickchat.main;

import android.graphics.Color;


import com.ming.quickchat.delegates.bottom.BaseBottomDelegate;
import com.ming.quickchat.delegates.bottom.BottomItemDelegate;
import com.ming.quickchat.delegates.bottom.BottomTabBean;
import com.ming.quickchat.delegates.bottom.ItemBuilder;
import com.ming.quickchat.main.chat.ChatDelegate;
import com.ming.quickchat.main.constract.ConstractDelegate;
import com.ming.quickchat.main.discorver.DiscorverDelegate;
import com.ming.quickchat.main.profile.ProfileDelegate;

import java.util.LinkedHashMap;

/**
 * @author Hortons
 * on 8/13/19
 */


public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-comment}", "主页"), new ChatDelegate());
        items.put(new BottomTabBean("{fa-book}", "联系人"), new ConstractDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscorverDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new ProfileDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
