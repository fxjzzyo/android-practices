package com.example.mvptest.presenter;

/**
 * Created by fanlulin on 2019-07-02.
 */
public interface IMvpMainView extends IMvpLoadingView{
    //显示信息
    void showToash(String msg);
    //更新UI
    void updateView();
}
