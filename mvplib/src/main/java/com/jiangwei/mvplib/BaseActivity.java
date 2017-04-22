package com.jiangwei.mvplib;

import android.support.v7.app.AppCompatActivity;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * author: jiangwei18 on 16/9/19 19:04 email: jiangwei18@baidu.com Hi: jwill金牛
 */
public class BaseActivity extends AppCompatActivity {
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
    protected void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }
}
