package com.fei.root.commons;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fei.root.common.Rxbus.BusEvent;
import com.fei.root.common.Rxbus.BusObservable;
import com.fei.root.common.Rxbus.BusSubscriber;
import com.fei.root.commons.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {


    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.layTest)
    LinearLayout layTest;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        resgisBusEvent();

    }

    @OnClick(R.id.text)
    public void onViewClicked() {
        startActivity(new Intent(this, ToastActivity.class));
    }


    private void resgisBusEvent() {
        BusSubscriber.bind(this).bindEvent(111)
                .onNext(new Action1<BusEvent>() {
                    @Override
                    public void call(BusEvent busEvent) {
                        busEvent.getContent();
                    }
                }).create();

        BusObservable.bind().sendStickyEvent(new BusEvent(111, "11"));
    }
}
