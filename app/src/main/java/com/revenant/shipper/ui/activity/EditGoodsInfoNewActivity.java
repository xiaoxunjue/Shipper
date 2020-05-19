package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.apkfuns.logutils.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.GoodsInfoEditBean;
import com.revenant.shipper.bean.ShipperBean.HuoYuanDetailBean;
import com.revenant.shipper.ui.fragment.ConversantVehicleDesignateFragment;
import com.revenant.shipper.ui.fragment.ConversantVehicleGroupFragment;
import com.revenant.shipper.ui.fragment.EditGoodsInfoFragment;
import com.revenant.shipper.ui.fragment.EditGoodsTypeFragment;
import com.revenant.shipper.ui.fragment.EditPingTaiFragment;
import com.revenant.shipper.ui.fragment.EditShuCheFragment;
import com.revenant.shipper.ui.fragment.EditZhiPaiFragment;
import com.revenant.shipper.ui.fragment.NetworkingPublishFragment;
import com.revenant.shipper.ui.fragment.PersonalFragment;
import com.revenant.shipper.ui.fragment.PublishPlatformFragment;
import com.revenant.shipper.ui.fragment.SysNewsFragment;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditGoodsInfoNewActivity extends BaseActivity {
    @BindView(R.id.publishtablayout)
    TabLayout publishtablayout;
    @BindView(R.id.publishviewpager)
    ViewPager publishviewpager;
    private List<String> titleList = new ArrayList<>();

    private List<Fragment> fragmentList = new ArrayList<>();

    private NewMainAdapter newMainAdapter;
    private int selectDeauft = 1;
    String goodseditid = "";
    private Context context;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_news;
    }

    @Override
    public void initView() {
        context = this;
        fragmentList = new ArrayList<>();

        titleList = new ArrayList<>();

    }

    @Override
    public void initData() {
        Bundle extras = getIntent().getExtras();
        goodseditid = String.valueOf(extras.getInt("goodsid"));
        geteditgoods(String.valueOf(goodseditid));
//        isPlatform = extras.getInt("isPlatform");
//        EditGoodsInfoFragment editGoodsInfo = EditGoodsInfoFragment.newInstance("EditGoodsInfo");
//        Bundle bundleinfo = new Bundle();
//        bundleinfo.putString("goodsid", String.valueOf(goodseditid));
//        editGoodsInfo.setArguments(bundleinfo);
//
//        fragmentList.add(editGoodsInfo);
//
//        EditGoodsTypeFragment editGoodsType = EditGoodsTypeFragment.newInstance("EditGoodsType");
//        Bundle bundletype = new Bundle();
//        bundletype.putString("goodsid", String.valueOf(goodseditid));
//        editGoodsType.setArguments(bundletype);
//        fragmentList.add(editGoodsType);
//
//        titleList.add("编辑货物信息");
//        titleList.add("编辑货物类型");
//
//        newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), this, fragmentList, titleList);
////
//        viewpagernews.setAdapter(newMainAdapter);
//        tablayoutnews.setupWithViewPager(viewpagernews);
//        setcenterTitle("编辑货源");

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void geteditgoods(String id) {
        OkGo.<String>get(Constants.Look_Goods)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("CCCC" + response.body());


                        GoodsInfoEditBean bean = GsonUtil.parseJsonWithGson(response.body(), GoodsInfoEditBean.class);
                        if (Integer.valueOf(bean.getCode()) == 0) {


                            switch (bean.getData().getInfo().getIsPlatform()) {
                                case 1:
                                    selectDeauft =0;
                                    break;
                                case 2:
                                    selectDeauft = 1;
                                    break;
                                case 0:
                                    selectDeauft = 2;
                                    break;


                            }
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("editgoodsinfo", bean);
                            EditPingTaiFragment editPingTai = EditPingTaiFragment.newInstance(bundle);



                            EditShuCheFragment editShuChe = EditShuCheFragment.newInstance(bundle);
                            editShuChe.setArguments(bundle);

                            EditZhiPaiFragment editZhiPai = EditZhiPaiFragment.newInstance(bundle);
                            editZhiPai.setArguments(bundle);


                            fragmentList.add(editPingTai);
                            fragmentList.add(editShuChe);
                            fragmentList.add(editZhiPai);

                            titleList.add("平台");
                            titleList.add("熟车群抢单");
                            titleList.add("熟车指派");
                            newMainAdapter = new NewMainAdapter(getSupportFragmentManager(), context, fragmentList, titleList);
                            publishviewpager.setAdapter(newMainAdapter);
                            publishtablayout.setupWithViewPager(publishviewpager);
                            publishtablayout.getTabAt(selectDeauft).select();

                            setcenterTitle("编辑货源");
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

}
