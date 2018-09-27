package com.fei.root.commons.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.fei.root.commons.R;


/**
 * Created by PengFeifei on 2018/8/6.
 */
public class RedDotTextView extends AppCompatTextView {


    public RedDotTextView(Context context) {
        super(context);
    }

    public RedDotTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RedDotTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        Drawable drawable = getResources().getDrawable(R.drawable.shape_dot);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        float width = Math.min(getMeasuredWidth(), getPaint().measureText(getText().toString()));
        canvas.translate(width - drawable.getIntrinsicWidth(), 0);
        drawable.draw(canvas);
        canvas.restore();
    }
}
