package com.example.android_practice.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_practice.ParallaxLayoutInflater;
import com.example.android_practice.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ParallaxFragment extends Fragment {

    private List<View> parallaxViews = new ArrayList<>();

    public ParallaxFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater original, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        int layoutId = bundle.getInt("layoutId");
        int index = bundle.getInt("index");

        ParallaxLayoutInflater parallaxLayoutInflater  = new ParallaxLayoutInflater(original, getActivity(),this);

        return parallaxLayoutInflater.inflate(layoutId,null);
    }


    public List<View> getParallaxViews() {
        return parallaxViews;
    }
}
