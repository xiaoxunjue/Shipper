package com.revenant.shipper.receiver;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.revenant.shipper.bean.NotifictionType;
import com.revenant.shipper.ui.activity.NewsActivity;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;

import java.io.IOException;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Xiao_Personal_Push_Code_NeWs;
import static com.revenant.shipper.bean.BaseBean.MyEventCode.Xiao_Sys_Push_Code_NeWs;

public class MyJpushReceiver extends JPushMessageReceiver {
    public MyJpushReceiver() {
        super();
    }

    @Override
    public Notification getNotification(Context context, NotificationMessage notificationMessage) {
        return super.getNotification(context, notificationMessage);
    }

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        String custom = customMessage.extra;
        if (!custom.isEmpty()) {
            NotifictionType customType = GsonUtil.parseJsonWithGson(custom, NotifictionType.class);
//            switch (Integer.valueOf(customType.getMsgId())) {
//                case 50030:
//                case 50020:/*熟车群抢单货源*/
//                    AssetManager assetManager;
//                    MediaPlayer player = null;
//                    player = new MediaPlayer();
//                    assetManager = context.getResources().getAssets();
//                    try {
//                        AssetFileDescriptor fileDescriptor = assetManager.openFd("dingyuep2.wav");
//                        player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getStartOffset());
//                        player.prepare();
//                        player.start();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
//                    break;
//            }
        }
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage);

        String notificationExtras = notificationMessage.notificationExtras;
        if (!notificationExtras.isEmpty()) {
            NotifictionType notifictionType = GsonUtil.parseJsonWithGson(notificationExtras, NotifictionType.class);

//            SPUtils.setPushCode(context, Xiao_Sys_Push_Code_NeWs);

            switch (Integer.valueOf(notifictionType.getMsgId())) {
                case 10010: /*系统消息*/
//                    jumpActivity(context,NewsActivity.class);
                    SPUtils.setPushCode(context, Xiao_Sys_Push_Code_NeWs);
                    break;
                case 10020:/*私人消息*/
                    SPUtils.setPushCode(context, Xiao_Personal_Push_Code_NeWs);
//                    Intent intent = new Intent();
//                    intent.setClass(context, NewsActivity.class);
//                    context.startActivity(intent);
//                    jumpActivity(context,NewsActivity.class);
                    break;
                /*磅单类型*/
                case 20010:/*司机上传装货磅单*/
                    break;
                case 20020:/*司机上传卸货磅单*/
                    break;
                case 20030:/*货主上传磅单*/
                    break;
                /*认证类型*/
                case 30010:/*货主个人认证*/
                    break;
                case 30011:/*货主个人认证成功*/
                    break;
                case 30012:/*货主个人认证失败*/
                    break;
                case 30020:/*货主企业认证*/
                    break;
                case 30021:/*货主企业认证成功*/
                    break;
                case 30022:/*货主企业认证失败*/
                    break;
                case 30030:/*司机认证*/
                    break;
                case 30031:/*司机实名认证成功*/
                    break;
                case 30032:/*司机实名认证失败*/
                    break;
                /*结算类型*/
                case 40010:/*结算成功*/
                    break;
                case 40020:/*结算失败*/
                    break;
                /*货源推送类型*/
                case 50010:/*平台货源*/
                    break;
                case 50020:/*熟车群抢单货源*/
                    break;
                case 50030:/*熟车指派货源*/
                    break;
                case 50040:/*为空车推送货源*/
                    break;
                /*发票类型*/
                case 60011:/*发票开出成功*/
                    break;
                case 60012:/*发票开出失败*/
                    break;

                /*金融类型*/
                case 70010:/*充值*/
                    break;
                case 70011:/*充值成功*/
                    break;
                case 70012:/*充值失败*/
                    break;
                case 70020:/*提现*/
                    break;
                case 70021:/*提现成功*/
                    break;
                case 70022:/*提现失败*/
                    break;
                case 70030:/*转账成功*/
                    break;
                case 70031:/*转账失败*/
                    break;
                case 70040:/*绑定银行卡*/
                    break;
                case 70041:/*绑定成功*/
                    break;
                case 70050:/*更改支付密码*/
                    break;
                case 70051:/*更改成功*/
                    break;
                case 70052:/*更改失败*/
                    break;

//                    /*其他*/
                case 80011:/*取消订单成功*/
                    break;
                case 80012:/*取消订单失败*/
                    break;
            }

        }

    }

    //接收到推送消息
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageDismiss(context, notificationMessage);

    }

    @Override
    public void onRegister(Context context, String s) {
        super.onRegister(context, s);
    }

    //连接极光
    @Override
    public void onConnected(Context context, boolean b) {
        super.onConnected(context, b);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        super.onCommandResult(context, cmdMessage);
    }

    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        super.onMultiActionClicked(context, intent);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    private void jumpActivity(Context context, Class<?> clz) {
        Intent intent = new Intent();
        intent.setClass(context, clz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void jumpBundleActivity(Context context, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}