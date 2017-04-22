package com.jiangwei.mvp;

import com.jiangwei.mvplib.BasePresenter;

import android.support.annotation.Nullable;

/**
 * author: jiangwei18 on 17/4/11 12:22 email: jiangwei18@baidu.com Hi: jwill金牛
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(@Nullable MainView view) {
        super(view);
    }

    public void getData() {
        getView().onLoad("loading");
        getView().onError("error");
    }
}
