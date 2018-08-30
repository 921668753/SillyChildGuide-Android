package com.common.cklibrary.utils.rx;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;


/**
 * 没有背压处理（Backpressure）的 RxBus
 * Created by Admin on 2017/11/8.
 */

public class RxBus {

    private final Subject<Object, Object> mBus;

    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        return Holder.BUS;
    }

    public void post(@NonNull Object obj) {
        mBus.onNext(obj);
    }

    public <T> Observable<T> register(Class<T> tClass) {
        return mBus.ofType(tClass);
    }

    public Observable<Object> register() {
        return mBus;
    }

    public boolean hasObservers() {
        return mBus.hasObservers();
    }

    public void unregisterAll() {
        //会将所有由mBus生成的Observable都置completed状态,后续的所有消息都收不到了
        mBus.onCompleted();
    }

    private static class Holder {
        private static final RxBus BUS = new RxBus();
    }

}