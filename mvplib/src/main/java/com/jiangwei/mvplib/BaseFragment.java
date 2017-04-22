package com.jiangwei.mvplib;

import android.support.v4.app.Fragment;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * author: jiangwei18 on 17/4/11 11:24 email: jiangwei18@baidu.com Hi: jwill金牛
 */

public class BaseFragment extends Fragment {
    private static CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void addSubscribe(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void unSubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()
                && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }

}
