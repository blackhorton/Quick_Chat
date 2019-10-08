package com.ming.quickchat.main.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ming.quickchat.R;
import com.ming.quickchat.delegates.bottom.BottomItemDelegate;
import com.ming.quickchat.main.chat_person.ChatActivity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author Hortons
 * on 19-10-3
 */

public class ChatDelegate extends BottomItemDelegate {

    private static final String TAG = "ChatDelegate";
    private List<ChatListInfoEntity> data = new LinkedList<>();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Override
    public Object setLayout() {
        return R.layout.delegate_chat;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        Log.d(TAG, "onBindView");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        Log.d(TAG, "onCreateView");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataList();
        ChatListAdapter adapter = new ChatListAdapter(R.layout.item_chat, data);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        Log.d(TAG, "onViewCreated");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.chat_menu, menu);
        Log.d(TAG, "onCreateOptionsMenu");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initDataList() {
        for (int i = 0; i < 10; i++) {
            ChatListInfoEntity entity = new ChatListInfoEntity();
            entity.setDate(new Date());
            entity.setHeadUrl("https://puui.qpic.cn/fans_admin/0/3_1503767511_1566805659800/0");
            entity.setName("Robot" + i);
            entity.setContent("未读内容");
            entity.setUnReadCount(0);
            data.add(entity);
        }
    }

}
