package com.fei.root.commons;

import android.os.Bundle;

import com.fei.root.common.Rxbus.BusEvent;
import com.fei.root.common.Rxbus.BusObservable;
import com.fei.root.common.Rxbus.BusSubscriber;
import com.fei.root.commons.base.BaseActivity;
import com.fei.root.commons.views.BonusProgressBar;

import butterknife.BindView;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bar)
    BonusProgressBar bar;
    int progress = 0;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @OnClick(R.id.text)
    public void onViewClicked() {
        progress += 5;
//        bar.setProgress(progress);
        bar.showNum("we3dede");
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
