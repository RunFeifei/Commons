package com.fei.root.commons;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fei.root.common.MultiApplication;
import com.fei.root.common.toast.AToast;
import com.fei.root.commons.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by PengFeifei on 17-8-9.
 */

public class ToastActivity extends BaseActivity {

    private static int i = 100000000;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_toast;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.btn)
    public void onBtnShowShortToastSafeClicked() {
        AToast.get(this).setText("" + i++).show();
    }

    @OnClick(R.id.btn1)
    public void btn1() {
    }







}
