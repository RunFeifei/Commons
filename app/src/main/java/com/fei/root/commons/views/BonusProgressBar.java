package com.fei.root.commons.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.FloatRange;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fei.root.commons.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by PengFeifei on 2018/11/8.
 */
public class BonusProgressBar extends FrameLayout {


    @BindView(R.id.textNum)
    TextView textNum;
    @BindView(R.id.layProgress)
    FrameLayout layProgress;
    @BindView(R.id.imgFinish)
    ImageView imgFinish;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.layTip)
    FrameLayout layTip;
    @BindView(R.id.viewProgress)
    View viewProgress;

    private Unbinder butterKinfeBinder;
    private float maxProgress;

    public BonusProgressBar(Context context) {
        super(context);
        init(context);
    }

    public BonusProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_video_bonus_bar, this);
        butterKinfeBinder = ButterKnife.bind(this, view);
        maxProgress = dp2px(120);
        resetProgress();
    }

    private void resetProgress() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewProgress.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new FrameLayout.LayoutParams(0, (int) dp2px(3));
        }
        layoutParams.width = 0;
        viewProgress.setLayoutParams(layoutParams);
    }

    public void setProgress(@FloatRange(from = 0.0, to = 100.0) float progress) {
        if (progress > 100) {
            return;
        }
        ValueAnimator animator = ValueAnimator.ofInt(getStartProgress(), getEndProgress(progress));
        animator.setDuration(300);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewProgress.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new FrameLayout.LayoutParams(0, (int) dp2px(3));
                }
                layoutParams.width = (Integer) animation.getAnimatedValue();
                viewProgress.setLayoutParams(layoutParams);
            }
        });
    }

    public void showNum(CharSequence charSequence) {
        textNum.setText(charSequence);
        int max = dp2px(120);
        ValueAnimator animator = ValueAnimator.ofInt(0, max);
        animator.setDuration(200);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) textNum.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new ConstraintLayout.LayoutParams(0, (int) dp2px(3));
                }
                layoutParams.width = (Integer) animation.getAnimatedValue();
                textNum.setLayoutParams(layoutParams);
            }
        });
    }

    private void showTip(boolean show) {
        layTip.setVisibility(show ? VISIBLE : INVISIBLE);
    }


    /*****************************TOOLS*****************************************************/

    private int getStartProgress() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) viewProgress.getLayoutParams();
        return layoutParams == null ? 0 : layoutParams.width;
    }

    /**
     * 需要强转?? 还是四舍五入
     */
    private int getEndProgress(float progress) {
        return Math.round(maxProgress / 100 * progress);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (butterKinfeBinder == null) {
            return;
        }
        butterKinfeBinder.unbind();
    }

    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }
}
