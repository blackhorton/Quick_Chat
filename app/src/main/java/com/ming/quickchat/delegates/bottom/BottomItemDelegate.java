package com.ming.quickchat.delegates.bottom;

import android.widget.Toast;

import com.ming.quickchat.R;
import com.ming.quickchat.app.Latte;
import com.ming.quickchat.delegates.LatteDelegate;


/**
 * @author Hortons
 * on 8/11/19
 */


public abstract class BottomItemDelegate extends LatteDelegate {

    /**
     * 再点一次退出程序时间设置
     */

    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + Latte.getApplicationContext().getString(R.string.app_name), Toast.LENGTH_SHORT).show();
        }
        return true;
    }

//    private long mExitTime = 0;
//    private static final int EXIT_TIME = 2000;

//    @Override
//    public void onResume() {
//        super.onResume();
//        View rootView = getView();
//        if (rootView != null) {
//            rootView.setFocusableInTouchMode(true);
//            rootView.requestFocus();
//            rootView.setOnKeyListener(this);
//        }
//    }

//    @Override
//    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
//            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
//                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_LONG).show();
//                mExitTime = System.currentTimeMillis();
//            } else {
//                _mActivity.finish();
//                if (mExitTime != 0) {
//                    mExitTime = 0;
//                }
//            }
//            return true;
//        }
//        return false;
//    }
}
