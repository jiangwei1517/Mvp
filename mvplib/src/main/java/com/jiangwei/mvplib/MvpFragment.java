package com.jiangwei.mvplib;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * author: jiangwei18 on 16/9/19 19:09 email: jiangwei18@baidu.com Hi: jwill金牛
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    private P mPresenter;

    protected abstract P createPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
    }

    public P getPresenter() {
        if (mPresenter != null) {
            return mPresenter;
        } else {
            throw new IllegalStateException("Presenter can't be null");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detach();
        }
    }
}
