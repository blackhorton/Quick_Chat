package com.ming.quickchat.delegates;

/**
 * @author Hortons
 * on 2019/7/6
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
