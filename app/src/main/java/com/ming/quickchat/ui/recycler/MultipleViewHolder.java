package com.ming.quickchat.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * @author Hortons
 * on 8/17/19
 */


public class MultipleViewHolder extends BaseViewHolder {


    public MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
