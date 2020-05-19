package com.revenant.shipper.utils;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.utils
 * @ClassName: RestartUtils
 * @Description: java类作用描述
 * @Author: Revenant
 * @CreateDate: 2020/4/13 9:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/13 9:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RestartUtils {

    public static final int STATUS_FORCE_KILLED = -1;//应用在后台被强杀了
    public static final int STATUS_NORMAL = 2; //APP正常态
    public static final String START_LAUNCH_ACTION = "start_launch_action";

    private int appStatus = STATUS_FORCE_KILLED; //默认为被后台回收了

    private static RestartUtils appStatusManager;

    public static RestartUtils getInstance() {
        if (appStatusManager == null) {
            appStatusManager = new RestartUtils();
        }
        return appStatusManager;
    }

    public int getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(int appStatus) {
        this.appStatus = appStatus;
    }
}
