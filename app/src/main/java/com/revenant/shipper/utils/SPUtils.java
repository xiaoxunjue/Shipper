package com.revenant.shipper.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.revenant.shipper.App;


/**
 * Created by ZHT on 2017/4/26.
 * SharedPreferences工具类
 */

public class SPUtils {

    private SPUtils() {
    }

    private final String TAG = SPUtils.class.getSimpleName();
    public static final String SHARED_PREFS_FILE = "SAVE";

    /**
     * 这里把常用的UserToken 单独封装出来以便提高效率.
     *
     * @param context
     * @return
     */
    public static String getUserToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("shipper_user_token", "");
    }

    public static void setUserToken(Context context, String userToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("shipper_user_token", userToken);
        editor.commit();
    }

    public static void clearUserToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("shipper_user_token");
        editor.commit();
    }

//    public static String getUserStatus(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
//        return sharedPreferences.getString("user_authentication", "-1");
//    }

    public static void setUserStatus(Context context, String user_authentication) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("shipper_user_authentication", user_authentication);
        editor.commit();
    }

    public static void clearUserStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("shipper_user_authentication");
        editor.commit();
    }


    public static String getJpRidToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("shipper_Jpush_rid", "");
    }

    public static void setJpRidToken(Context context, String userToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("shipper_Jpush_rid", userToken);
        editor.commit();
    }

    public static boolean getUserShowwWallet(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("shipper_user_show_wallet", true);
    }

    public static void setUserShowwWallet(Context context, boolean userToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("shipper_user_show_wallet", userToken);
        editor.commit();
    }

    public static int getAccounId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("shipper_accoun_id", 0);
    }

    public static void setAccounId(Context context, int userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("shipper_accoun_id", userId);
        editor.commit();
    }

    public static void clearAccounId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("shipper_accoun_id");
        editor.commit();
    }

    public static int getUpdateNum(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("shipper_update",0);
    }

    public static void setUpdateNum(Context context, int mobile) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("shipper_update", mobile);
        editor.commit();
    }

    public static String getUsermobile(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("shipper_mobile", "");
    }

    public static void setUsermobile(Context context, String mobile) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("shipper_mobile", mobile);
        editor.commit();
    }

    public static void clearUsermobile(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("shipper_mobile");
        editor.commit();
    }

    public static void clearJpRidToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("shipper_Jpush_rid");
        editor.commit();
    }

    public static int getPushCode(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("shipper_PushCode", 0);
    }

    public static void setPushCode(Context context, int shopId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("shipper_PushCode", shopId);
        editor.commit();
    }
    /**
     * 世界消息主键
     *
     * @param context
     * @return
     */
    public static int getRealmid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("realmid", 0);
    }

    public static void setRealmid(Context context, int realmid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("realmid", realmid);
        editor.commit();
    }


    /**
     * layoutKey 单独封装出来以便提高效率.
     *
     * @param context
     * @return
     */
    public static boolean getLayoutKey(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("layout_key", false);
    }

    public static void setLayoutKey(Context context, boolean layoutKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("layout_key", layoutKey);
        editor.commit();
    }


    /**
     * 这里把常用的UserId 单独封装出来以便提高效率.
     *
     * @param context
     * @return
     */
    public static int getUserId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("user_id", 0);
    }

    public static void setUserId(Context context, int userId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("user_id", userId);
        editor.commit();
    }

    public static int getShopId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("shop_id", 0);
    }

    public static void setShopId(Context context, int shopId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("shop_id", shopId);
        editor.commit();
    }

    public static void clearUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user_id");
        editor.commit();
    }


    /**
     * 这里把常用的UserId 单独封装出来以便提高效率.
     *
     * @param context
     * @return
     */
    public static boolean getPayKey(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("pay_key", false);
    }

    public static void setPayKey(Context context, boolean PayKey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("pay_key", PayKey);
        editor.commit();
    }


    /**
     * 保存用户的手机号
     *
     * @param context
     */
    public static String getUserPhone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("shipper_user_phone", null);
    }


    public static void setUserPhone(Context context, String mUserPhone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("shipper_user_phone", mUserPhone);
        editor.commit();
    }


    /**
     * 保存用户的等级
     *
     * @param context
     */
    public static int getVipLevel(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("vip_Level", 0);
    }


    public static void setVipLevel(Context context, int vipLevel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("vip_Level", vipLevel);
        editor.commit();
    }

    private static SharedPreferences getSharedPreferences() {
        return App.getApplication()
                .getSharedPreferences(App.getApplication().getPackageName(), Context.MODE_PRIVATE);
    }

    /**
     * 保存boolean类型配置信息
     *
     * @param key
     * @param value
     */
    public static void saveBoolean(String key, boolean value) {
        getSharedPreferences().edit()
                .putBoolean(key, value)
                .apply();
    }

    /**
     * 获取boolean类型配置信息
     *
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    /**
     * 保存int类型配置信息
     *
     * @param key
     * @param value
     */
    public static void saveInt(String key, int value) {
        getSharedPreferences().edit()
                .putInt(key, value)
                .apply();
    }

    /**
     * 获取int类型配置信息
     *
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }

    /**
     * 保存long类型配置信息
     *
     * @param key
     * @param value
     */
    public static void saveLong(String key, long value) {
        getSharedPreferences().edit()
                .putLong(key, value)
                .apply();
    }

    /**
     * 获取long类型配置信息
     *
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(String key, Long defValue) {
        return getSharedPreferences().getLong(key, defValue);
    }

    /**
     * 保存String类型配置信息
     *
     * @param key
     * @param value
     */
    public static void saveString(String key, String value) {
        getSharedPreferences().edit()
                .putString(key, value)
                .apply();
    }

    /**
     * 获取String类型配置信息
     *
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }
}
