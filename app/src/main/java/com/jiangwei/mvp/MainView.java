package com.jiangwei.mvp;

import com.jiangwei.mvplib.BaseView;

/**
 * author: jiangwei18 on 17/4/11 12:21 email: jiangwei18@baidu.com Hi: jwill金牛
 */

public interface MainView extends BaseView {

    void onLoad(String loading);

    void onError(String error);
}
