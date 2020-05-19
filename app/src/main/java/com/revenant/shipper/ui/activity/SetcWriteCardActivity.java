package com.revenant.shipper.ui.activity;

import android.content.Intent;
import android.view.View;

import com.manyi.mobile.etcsdk.activity.ReadETCBlueTooth;
import com.manyi.mobile.etcsdk.activity.ReadEtcNFC;
import com.manyi.mobile.etcsdk.activity.ReadEtcUSB;
import com.manyi.mobile.utils.Common;
import com.manyi.mobile.widget.CustomDialog;
import com.revenant.shipper.R;
import com.revenant.shipper.base.BaseActivity;
import com.xinlian.cardsdk.CardSDK;

public class SetcWriteCardActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_sect_write_card;
    }

    @Override
    public void initView() {
        setcenterTitle("ETC圈存写卡");
    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void initData() {

    }


    /**
     * 写卡功能
     *
     * @param v
     */
    public void ndcEtcQc(View v) {
        gotoNFC();
    }

    /**
     * 写卡功能
     *
     * @param v
     */
    public void mposEtcQc(View v) {
        startActivity(new Intent(this, ReadETCBlueTooth.class).putExtra(
                "isRead", false).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    /**
     * USB写卡功能
     *
     * @param v
     */
    public void usbEtcQc(View v) {
        startActivity(new Intent(this, ReadEtcUSB.class).putExtra("isRead",
                false).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

    }

    /**
     * NFC
     */
    private void gotoNFC() {
        final int ret = CardSDK.instance().getNFCStatus(this);
        if (ret == -1) {
            Common.showToast(this, "对不起，因为您的手机不支持NFC功能，所以不能使用手机写卡功能！");
        } else if (ret == 0) {
            final CustomDialog dialognfc = new CustomDialog(this);
            dialognfc.setDisMiss(false);
            dialognfc.setlinecolor();
            dialognfc.setTitle("提示");
            dialognfc.setContentboolean(true);
            dialognfc.setDetial("NFC未打开，请先打开NFC功能");
            dialognfc.setLeftText("确认");
            dialognfc.setRightText("取消");
            dialognfc.setLeftOnClick(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    dialognfc.dismiss();
                    startActivity(new Intent("android.settings.NFC_SETTINGS"));
                }
            });
            dialognfc.setRightOnClick(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dialognfc.dismiss();
                }
            });
            dialognfc.show();
        } else {
            startActivity(new Intent(this, ReadEtcNFC.class).putExtra("isRead",
                    false).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }
    }
}
