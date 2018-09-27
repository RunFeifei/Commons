package com.fei.root.commons;

import android.os.Bundle;

import com.fei.root.common.AToast;
import com.fei.root.commons.base.BaseActivity;

import butterknife.OnClick;
import rx.functions.Action0;

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
    }

    @OnClick(R.id.btn1)
    public void btn1() {
        AToast.get(this).setText("ddededededededed").show(new Action0() {
            @Override
            public void call() {
                finish();
            }
        });
    }


}
