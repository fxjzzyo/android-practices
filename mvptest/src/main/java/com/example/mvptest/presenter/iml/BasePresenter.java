package com.example.mvptest.presenter.iml;

import android.content.Context;

/**
 * Created by fanlulin on 2019-07-02.
 */
public class BasePresenter {

    Context mContext;
    public void attach(Context context){
        mContext = context;

    }
    public void onPause(){}
    public void onResume(){}
    public void onDestory(){
        mContext = null;
    }

}
