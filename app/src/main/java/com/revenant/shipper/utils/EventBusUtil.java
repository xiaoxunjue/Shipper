package com.revenant.shipper.utils;



import com.revenant.shipper.bean.BaseBean.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2018/5/31.
 */

public class EventBusUtil {
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    public static void sendEvent(MessageEvent event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(MessageEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
