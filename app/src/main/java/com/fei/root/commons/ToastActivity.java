package com.fei.root.commons;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fei.root.commons.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by PengFeifei on 17-8-9.
 */

public class ToastActivity extends BaseActivity {


    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_toast;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        viewpager.setOffscreenPageLimit(3);
        viewpager.setPageMargin(30);
        viewpager.setAdapter(new Adapter());
    }

    private class Adapter extends PagerAdapter {


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            RoundImageView imageView = new RoundImageView(ToastActivity.this);
            if (position == 0) {
                imageView.setImageDrawable(ContextCompat.getDrawable(ToastActivity.this, R.drawable.banner_01));
            }
            if (position == 1) {
                imageView.setImageDrawable(ContextCompat.getDrawable(ToastActivity.this, R.drawable.banner_02));
            }
            if (position == 2) {
                imageView.setImageDrawable(ContextCompat.getDrawable(ToastActivity.this, R.drawable.banner_03));
            }
            imageView.setBackground(ContextCompat.getDrawable(ToastActivity.this, R.drawable.shape_rect_round));
            container.addView(imageView);
           /*
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ViewPager.LayoutParams layoutParams=(ViewPager.LayoutParams)imageView.getLayoutParams();

            imageView.setLayoutParams(layoutParams);*/
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(780,225);
            container.setLayoutParams(params);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
