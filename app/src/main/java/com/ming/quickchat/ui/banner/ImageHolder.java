package com.ming.quickchat.ui.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * @author Hortons
 * on 8/18/19
 */

/**
 * 存放BannerImage的Holder
 */
public class ImageHolder implements Holder<String> {

    private AppCompatImageView mImageView = null;

    @Override
    public View createView(Context context) {
        mImageView = new AppCompatImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(data)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //已经有RecyclerView的动画，就不需要图片的动画
                .dontAnimate()
                .centerCrop()
                .into(mImageView);
    }
}
