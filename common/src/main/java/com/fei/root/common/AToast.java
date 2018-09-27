package com.fei.root.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import rx.functions.Action0;

/**
 * Created by PengFeifei on 17-8-9.
 * 系统Toast有通知权限,WindowManager有悬浮窗权限
 * 在此采用PopupWindow模拟Toast
 */

public class AToast {

    private PopupWindow popupWindow;
    private Activity activity;

    private AToast(Activity activity) {
        this.activity = new WeakReference<>(activity).get();
        popupWindow = new PopupWindow(this.activity);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);
        popupWindow.setAnimationStyle(R.style.Toast);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public static AToast get(Activity activity) {
        return new AToast(activity);
    }

    public AToast setText(CharSequence charSequence) {
        LayoutInflater inflate = (LayoutInflater)
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.transient_notification, null);
        TextView textView = (TextView) view.findViewById(R.id.message);
        textView.setText(charSequence);
        popupWindow.setContentView(view);
        return this;
    }

    public AToast setText(@StringRes int stringId) {
        LayoutInflater inflate = (LayoutInflater)
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(R.layout.transient_notification, null);
        TextView textView = (TextView) view.findViewById(R.id.message);
        textView.setText(stringId);
        popupWindow.setContentView(view);
        return this;
    }

    public AToast setView(View view) {
        popupWindow.setContentView(view);
        return this;
    }

    public AToast setView(@LayoutRes int layoutId) {
        LayoutInflater inflate = (LayoutInflater)
                activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflate.inflate(layoutId, null);
        if (view == null) {
            return this;
        }
        popupWindow.setContentView(new WeakReference<View>(view).get());
        return this;
    }

    public AToast setOnDissmissListenner(PopupWindow.OnDismissListener onDismissListener) {
        popupWindow.setOnDismissListener(onDismissListener);
        return this;
    }

    public void show() {
        show(null);
    }

    public void show(Action0 afterShowCallBack) {
        if (activity.isDestroyed() || activity.isFinishing()) {
            return;
        }
        int y = activity.getResources().getDimensionPixelSize(R.dimen.toast);
        y = (int) (1.75 * y);
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, y);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (activity.isDestroyed() || activity.isFinishing()) {
                    return;
                }
                popupWindow.dismiss();
                if (afterShowCallBack == null) {
                    return;
                }
                afterShowCallBack.call();
            }
        }, 1000);
    }


}
