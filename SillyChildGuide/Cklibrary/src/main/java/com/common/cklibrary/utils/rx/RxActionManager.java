package com.common.cklibrary.utils.rx;

import rx.Subscription;

/**
 * Created by Administrator on 2017/12/6.
 */

public interface RxActionManager<T> {

    void add(T tag, Subscription subscription);
    void remove(T tag);
    void cancel(T tag);
    void cancelAll();
}