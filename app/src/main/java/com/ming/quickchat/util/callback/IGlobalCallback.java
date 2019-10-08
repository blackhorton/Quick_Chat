package com.ming.quickchat.util.callback;

import android.support.annotation.Nullable;


/**
 * @author Hortons
 * on 19-9-1
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
