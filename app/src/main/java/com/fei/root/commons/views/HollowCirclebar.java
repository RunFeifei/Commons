package com.fei.root.commons.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by PengFeifei on 2018/11/10.
 */
public class HollowCirclebar extends View {

    private Paint paint;
    private float lastSweep;
    private float sweepAngle;
    private float padding = 5;

    public HollowCirclebar(Context context) {
        super(context);
        init(context);
    }

    public HollowCirclebar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HollowCirclebar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.TRANSPARENT);
        paint = new Paint();
        paint.setStrokeWidth((dp2px(4)));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    boolean zero = false;

    public void setProgress(float progress) {

        float endAngle = progress * 360.0f;

        float start = progress == 0f ? 360 : 0f;
        float end = progress == 0f ? 0f : endAngle;
        int time = progress == 0f ? 300 : 400;
        if (progress == 0) {
            zero = true;
        }


        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.setDuration(time);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (Float) animation.getAnimatedValue();
                Log.e("TAG-->", sweepAngle + "");
                postInvalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                lastSweep = endAngle;
            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF oval = new RectF(padding, padding, getWidth() - padding, getHeight() - padding);
        if (!zero) {
            canvas.drawArc(oval, -90, lastSweep, false, paint);
        }
        canvas.drawArc(oval, -90, sweepAngle, false, paint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int[] colors = new int[]{
                Color.parseColor("#D74D35"), Color.parseColor("#FFC926"),
                Color.parseColor("#FFC826"), Color.parseColor("#FF3619"),
                Color.parseColor("#C42918"), Color.parseColor("#FF1600"),
        };
        float[] stops = new float[]{
                0f, 0.2f,
                0.4f, 0.6f,
                0.8f, 1.0f,
        };
        SweepGradient radialGradient = new SweepGradient(w / 2, h / 2, colors, stops);
        paint.setShader(radialGradient);


    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }


}
