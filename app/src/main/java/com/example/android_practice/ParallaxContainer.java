package com.example.android_practice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.android_practice.activity.ParallaxSplashActivity;
import com.example.android_practice.adapter.ParallaxPagerAdapter;
import com.example.android_practice.fragment.ParallaxFragment;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanlulin
 * on date 2020/5/4 0004
 */
public class ParallaxContainer extends FrameLayout implements ViewPager.OnPageChangeListener {

    private List<ParallaxFragment> mParallaxFragments;
    private ParallaxPagerAdapter adapter;
    private float containerWidth;
    private ImageView iv_man;

    public ParallaxContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setup(int... ids) {
        mParallaxFragments = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ParallaxFragment p = new ParallaxFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", ids[i]);
            bundle.putInt("index", i);
            p.setArguments(bundle);
        }

        // 实例化适配器
        ParallaxSplashActivity splashActivity = (ParallaxSplashActivity) getContext();
        adapter = new ParallaxPagerAdapter(splashActivity.getSupportFragmentManager(), mParallaxFragments);

        // 实例化viewpager
        ViewPager viewPager = new ViewPager(getContext());
        viewPager.setId(R.id.parallax_pager);
        viewPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        viewPager.setAdapter(adapter);
        addView(viewPager, 0);

        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.containerWidth = getWidth();
        //在翻页的过程中，不断根据视图的标签中对应的动画参数，改变视图的位置或者透明度
        //获取到进入的页面
        ParallaxFragment inFragment = null;
        try {
            inFragment = mParallaxFragments.get(position - 1);
        } catch (Exception e) {
        }

        //获取到退出的页面
        ParallaxFragment outFragment = null;
        try {
            outFragment = mParallaxFragments.get(position);
        } catch (Exception e) {
        }

        if (inFragment != null) {
            List<View> parallaxViews = inFragment.getParallaxViews();
            if (parallaxViews != null) {
                for (View view : parallaxViews) {
                    //获取标签，从标签上获取所有的动画参数
                    ParallaxViewTag parallaxViewTag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                    if (parallaxViewTag == null) continue;
                    //translationY改变view的偏移位置，translationY=100，代表view在其原始位置向下移动100
                    //仔细观察进入的fragment中view从远处过来，不断向下移动，最终停在原始位置
                    ViewHelper.setTranslationX(view, (containerWidth - positionOffsetPixels) * parallaxViewTag.xIn);
                    ViewHelper.setTranslationY(view, (containerWidth - positionOffsetPixels) * parallaxViewTag.yIn);
                }
            }
        }

        if (outFragment != null) {
            List<View> parallaxViews = outFragment.getParallaxViews();
            if (parallaxViews != null) {
                for (View view : parallaxViews) {
                    //获取标签，从标签上获取所有的动画参数
                    ParallaxViewTag parallaxViewTag = (ParallaxViewTag) view.getTag(R.id.parallax_view_tag);
                    if (parallaxViewTag == null) continue;
                    //仔细观察退出的fragment中view从原始位置开始向上移动，translationY应为负数
                    ViewHelper.setTranslationX(view, (-positionOffsetPixels) * parallaxViewTag.xOut);
                    ViewHelper.setTranslationY(view, (-positionOffsetPixels) * parallaxViewTag.yOut);
                }
            }
        }


    }

    @Override
    public void onPageSelected(int position) {
        if (position == adapter.getCount() - 1) {
            iv_man.setVisibility(View.INVISIBLE);
        } else {
            iv_man.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {
        AnimationDrawable animation = (AnimationDrawable) iv_man.getBackground();
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                animation.start();
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                animation.stop();
                break;
            default:
                break;
        }
    }


    public void setIv_man(ImageView iv_man) {
        this.iv_man = iv_man;
    }
}
