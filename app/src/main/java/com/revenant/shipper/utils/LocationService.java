package com.revenant.shipper.utils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.utils
 * @ClassName: LocationService
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/13 12:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/13 12:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LocationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
