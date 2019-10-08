package com.ming.quickchat.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * @author Hortons
 * on 8/18/19
 */


public class HolderCreator implements CBViewHolderCreator<ImageHolder> {

    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
