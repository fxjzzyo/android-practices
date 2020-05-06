package com.example.android_practice.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android_practice.fragment.ParallaxFragment;

import java.util.List;

/**
 * Created by fanlulin
 * on date 2020/5/4 0004
 */
public class ParallaxPagerAdapter extends FragmentPagerAdapter {

    private List<ParallaxFragment> mParallaxFragments;

    public ParallaxPagerAdapter(FragmentManager fm,List<ParallaxFragment> parallaxFragments) {
        super(fm);
        this.mParallaxFragments = parallaxFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mParallaxFragments.get(position);
    }

    @Override
    public int getCount() {
        return mParallaxFragments.size();
    }
}
