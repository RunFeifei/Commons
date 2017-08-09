package com.fei.root.commons;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.fei.root.commons.base.BaseActivity;
import com.feifei.common.MultiApplication;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.text)
    TextView text;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.text)
    public void onViewClicked() {
        startActivity(new Intent(this, ToastActivity.class));
    }
}
