package com.fei.root.common;

import android.util.TypedValue;

/**
 * Created by PengFeifei on 17-8-10.
 */

public class Display {

    private Display() {
    }

    public static int getScreenHeight() {
        return MultiApplication.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return MultiApplication.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static float dp2px(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, MultiApplication.getContext().getResources().getDisplayMetrics());
    }

    public static float px2dp(float px) {
        final float density = MultiApplication.getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }
}
