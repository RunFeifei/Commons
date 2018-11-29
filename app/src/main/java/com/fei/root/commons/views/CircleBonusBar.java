package com.fei.root.commons.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fei.root.commons.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by PengFeifei on 2018/11/10.
 */
public class CircleBonusBar extends FrameLayout {

    @BindView(R.id.textTip)
    TextView textTip;
    @BindView(R.id.imgPacket)
    ImageView imgPacket;
    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.layNum)
    LinearLayout layNum;
    @BindView(R.id.circleBar)
    HollowCirclebar circleBar;

    public CircleBonusBar(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CircleBonusBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleBonusBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_video_bonus_bar_circle, this);
        ButterKnife.bind(this, view);
    }

    public void setNum(CharSequence charSequence) {
        layNum.setVisibility(VISIBLE);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                textNum.setText("+" + charSequence);
                layNum.setVisibility(GONE);
            }
        }, 2000);
    }

    public void setTip(CharSequence charSequence) {
        textTip.setVisibility(VISIBLE);
        postDelayed(new Runnable() {
            @Override
            public void run() {
                textTip.setText(charSequence);
                textTip.setVisibility(GONE);
            }
        }, 2000);
    }

    public void setProgress(float progress) {
        circleBar.setProgress(progress);
    }


    @OnClick(R.id.textTip)
    public void onTextTipClicked() {
    }

    @OnClick(R.id.imgPacket)
    public void onImgPacketClicked() {
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }


}
