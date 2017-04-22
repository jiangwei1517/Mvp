package com.jiangwei.mvplib;

import java.lang.ref.WeakReference;

import android.support.annotation.Nullable;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * author: jiangwei18 on 16/9/19 18:56 email: jiangwei18@baidu.com Hi: jwill金牛
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {
    // 防止内存泄露
    private WeakReference<V> mWeakView;
    private static CompositeSubscription compositeSubscription = new CompositeSubscription();

    public BasePresenter(@Nullable V view) {
        attach(view);
    }

    public V getView() {
        if (mWeakView != null) {
            return mWeakView.get();
        } else {
            throw new IllegalStateException("ActivityView can't be null");
        }
    }

    @Override
    public void attach(V view) {
        mWeakView = new WeakReference<V>(view);
    }

    @Override
    public void detach() {
        if (mWeakView != null) {
            mWeakView.clear();
            mWeakView = null;
        }
    }

    public void addSubscribe(Subscription subscription) {
        if (compositeSubscription != null) {
            compositeSubscription.add(subscription);
        }
    }

    public void unSubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()
                && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

}
