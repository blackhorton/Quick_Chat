package com.ming.quickchat.ui.recycler;

import com.google.auto.value.AutoValue;

/**
 * @author Hortons
 * on 8/19/19
 */

/**
 *
 */
@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

//    public static RgbValue create(int red, int green, int blue) {
//        return new AutoValue_RgbValue(red, green, blue);
//    }
}
