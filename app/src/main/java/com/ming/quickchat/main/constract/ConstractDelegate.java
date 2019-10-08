package com.ming.quickchat.main.constract;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ming.quickchat.R;
import com.ming.quickchat.delegates.bottom.BottomItemDelegate;

/**
 * @author Hortons
 * on 19-10-5
 */


public class ConstractDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_constract;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
