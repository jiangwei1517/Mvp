package com.jiangwei.mvplib;

/**
 * author:  jiangwei18 on 16/9/19 18:56
 * email:  jiangwei18@baidu.com
 * Hi:   jwill金牛
 */
public interface Presenter<V extends BaseView> {

    void attach(V view);

    void detach();
}
