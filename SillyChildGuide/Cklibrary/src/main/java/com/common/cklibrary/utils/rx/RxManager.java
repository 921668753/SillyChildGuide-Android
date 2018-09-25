package com.common.cklibrary.utils.rx;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import rx.Subscription;

/**
 * Created by Admin on 2017/11/10.
 */
public class RxManager implements RxActionManager<Object> {

    private static RxManager sInstance = null;

    private Map<Object, Subscription> maps;

    public static RxManager get() {

        if (sInstance == null) {
            synchronized (RxManager.class) {
                if (sInstance == null) {
                    sInstance = new RxManager();
                }
            }
        }
        return sInstance;
    }

    private RxManager() {
        try {
            maps = new ArrayMap<Object, Subscription>();
        } catch (NoClassDefFoundError error) {
            maps = new HashMap<Object, Subscription>();
        }
    }

    @Override
    public void add(Object tag, Subscription subscription) {
        maps.put(tag, subscription);
    }

    @Override
    public void remove(Object tag) {
        if (!maps.isEmpty()) {
            maps.remove(tag);
        }
    }

    public void removeAll() {
        if (!maps.isEmpty()) {
            maps.clear();
        }
    }

    @Override
    public void cancel(Object tag) {
        if (maps.isEmpty()) {
            return;
        }
        if (maps.get(tag) == null) {
            return;
        }
        if (!maps.get(tag).isUnsubscribed()) {
            maps.get(tag).unsubscribe();
            maps.remove(tag);
        }
    }

    @Override
    public void cancelAll() {
        if (maps.isEmpty()) {
            return;
        }
        Set<Object> keys = maps.keySet();
        for (Object apiKey : keys) {
            cancel(apiKey);
        }
    }
}
