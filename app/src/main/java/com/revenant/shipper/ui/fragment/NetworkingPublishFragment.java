package com.revenant.shipper.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.VerticalTypeItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.AreaShowBean;
import com.revenant.shipper.bean.ShipperBean.LoginUserBean;
import com.revenant.shipper.ui.activity.ShipperMainActivity;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Utils;
import com.revenant.shipper.utils.apkUpdate.StringFormatUtil;

import org.angmarch.views.NiceSpinner;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.revenant.shipper.utils.Constants.Name_header_token;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetworkingPublishFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.load_text)
    TextView loadText;
    @BindView(R.id.unload_text)
    TextView unloadText;
    @BindView(R.id.unload_personal_phone)
    EditText unloadPersonalPhone;
    @BindView(R.id.goods_name)
    EditText goodsName;
    @BindView(R.id.goods_type)
    TextView goodsType;
    @BindView(R.id.goods_count)
    EditText goodsCount;
    @BindView(R.id.goods_weight)
    EditText goodsWeight;
    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.loadLayout)
    LinearLayout loadLayout;
    CityPickerView mPicker = new CityPickerView();
    private String loadingCode;
    private String unloadCode;

    private List<String> typelista = Arrays.asList("4.2米以下",
            "4.2米", "5.6米", "6.2米", "6.8米", "7.2米", "8.2米", "8.6米", "9.6米", "11.7米", "12.5米", "13米", "13.5米", "15米", "16米", "17.5米", "18米");
    private List<String> typelistb = Arrays.asList("普通", "箱式", "罐式", "冷藏式", "保温式", "牵引式", "封闭", "平板", "集装", "自卸", "特殊结构", "仓栅式", "罐式", "集装箱", "厢式", "专项作业", "车辆运输", "平板", "自卸", "普通");
    private List<String> typelistc = Arrays.asList("煤炭", "石油", "金属", "钢铁", "矿建", "水泥", "木材", "非金属矿石", "化肥及农药", "盐", "粮食", "机械设备电器", "轻工原料", "有色金属", "轻工医药", "鲜活冷冻", "商品汽车", "其他");
    private List<AreaShowBean> showBeanLista = new ArrayList<>();
    private List<AreaShowBean> showBeanListb = new ArrayList<>();
    private List<AreaShowBean> showBeanListc = new ArrayList<>();
    private int defaulta = 0;
    private int defaultb = 0;
    private int defaultc = 0;
    VerticalTypeItemAdapter adaptera;
    VerticalTypeItemAdapter adapterb;
    VerticalTypeItemAdapter adapterc;
    private String load_province = "";
    private String load_city = "";
    private String load_country = "";

    private String unload_province = "";
    private String unload_city = "";
    private String unload_country = "";
    private String Name_header;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_networking_published;
    }

    @Override
    protected void initView() {
        mPicker.init(getActivity());
    }


    @Override
    public void initData() {

        AreaShowBean showBean = new AreaShowBean();
        showBean.setAreaname("不限车长");
        showBean.setSelect(1);
        showBeanLista.add(showBean);
        for (int i = 0; i < typelista.size(); i++) {
            AreaShowBean showBeana = new AreaShowBean();
            showBeana.setAreaname(typelista.get(i));
            showBeana.setSelect(0);
            showBeanLista.add(showBeana);
        }

        AreaShowBean showBeanb = new AreaShowBean();
        showBeanb.setAreaname("不限车型");
        showBeanb.setSelect(1);
        showBeanListb.add(showBeanb);
        for (int i = 0; i < typelistb.size(); i++) {
            AreaShowBean showBeanbb = new AreaShowBean();
            showBeanbb.setAreaname(typelistb.get(i));
            showBeanbb.setSelect(0);
            showBeanListb.add(showBeanbb);
        }

        AreaShowBean showBeanc = new AreaShowBean();
        showBeanc.setAreaname("不限");
        showBeanc.setSelect(1);
        showBeanListc.add(showBeanc);
        for (int i = 0; i < typelistc.size(); i++) {
            AreaShowBean showBeancc = new AreaShowBean();
            showBeancc.setAreaname(typelistc.get(i));
            showBeancc.setSelect(0);
            showBeanListc.add(showBeancc);
        }
        Name_header = Utils.get64String() + "_" + SPUtils.getUserPhone(getActivity()) + "_" + "1";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public static NetworkingPublishFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(title, title);
        NetworkingPublishFragment fragment = new NetworkingPublishFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @OnClick({R.id.load_text, R.id.unload_text, R.id.goods_type, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.load_text:
                alertarea(loadText);
                break;
            case R.id.unload_text:
                alertarea(unloadText);
                break;
            case R.id.goods_type:
                alertTypeSelect();
                break;
            case R.id.login:
                if (emptyText(goodsName, "请输入货物名称")) {
                } else if (emptyText(loadText, "请选择装车地址")) {
                } else if (emptyText(goodsType, "请选择装车类型")) {
                } else if (emptyText(unloadText, "请填写卸车地址")) {
                } else if (emptyText(goodsWeight, "请填写重量")) {
                } else if (emptyText(goodsCount, "请填写数量")) {
                } else if (emptyText(unloadPersonalPhone, "请填写电话")) {
                } else if (emptycount(goodsWeight, "重量不能小于0")) {
                } else if (emptycount(goodsCount, "数量不能小于0")) {
                } else if (decimalcount(goodsWeight, "重量填写完备")) {
                } else if (!StringFormatUtil.phoneCheck(unloadPersonalPhone.getText().toString().trim())) {
                    ToastUtils.showShortToast(getActivity(), "手机号码格式不正确");
                } else {
                    publish_goods(
                            load_province,
                            load_city,
                            unload_province,
                            unload_city,
                            getText(goodsName),
                            getText(unloadPersonalPhone),
                            getText(goodsWeight),
                            adapterc.getData().get(defaultc).getAreaname(),
                            getText(goodsCount),
                            adapterb.getData().get(defaultb).getAreaname(),
                            adaptera.getData().get(defaulta).getAreaname()


                    );
                }
                break;
        }
    }

    private void alertarea(TextView textView) {
        CityConfig cityConfig = new CityConfig.Builder()
                .confirTextColor("#1E90FF")
                .cancelTextColor("#1E90FF")
                .districtCyclic(false)
                .cityCyclic(false)
                .provinceCyclic(false)
                .build();
        mPicker.setConfig(cityConfig);

        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                textView.setText(province + "-" + city + "-" + district);
                int viewId = textView.getId();
                switch (viewId) {
                    case R.id.load_text:
                        load_province = province.getName();
                        load_city = city.getName();
                        load_country = district.getName();
                        break;
                    case R.id.unload_text:
                        unload_province = province.getName();
                        unload_city = city.getName();
                        unload_country = district.getName();
                        break;
                }
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(getActivity(), "已取消");
            }
        });

        mPicker.showCityPicker();
    }


    private void alertTypeSelect() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.alert_mul_type_first, null);
        RecyclerView type1 = view.findViewById(R.id.recyclerview_type1);
        RecyclerView type2 = view.findViewById(R.id.recyclerview_type2);
        RecyclerView type3 = view.findViewById(R.id.recyclerview_type3);
        TextView clean = view.findViewById(R.id.clear_all_conditions);
        TextView confirm = view.findViewById(R.id.confirm);

        adaptera = new VerticalTypeItemAdapter();
        adapterb = new VerticalTypeItemAdapter();
        adapterc = new VerticalTypeItemAdapter();


        type1.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        type1.setAdapter(adaptera);
        adaptera.setNewData(showBeanLista);

        adaptera.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adaptera.getData().get(defaulta).setSelect(0);
                adaptera.getData().get(position).setSelect(1);
                adaptera.notifyItemChanged(defaulta);
                defaulta = position;
                adaptera.notifyItemChanged(position);

            }
        });


        type2.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        type2.setAdapter(adapterb);
        adapterb.setNewData(showBeanListb);
        adapterb.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapterb.getData().get(defaultb).setSelect(0);
                adapterb.getData().get(position).setSelect(1);
                defaultb = position;
                adapterb.notifyDataSetChanged();
            }
        });


        type3.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        type3.setAdapter(adapterc);
        adapterc.setNewData(showBeanListc);
        adapterc.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                adapterc.getData().get(defaultc).setSelect(0);
                adapterc.getData().get(position).setSelect(1);
                defaultc = position;
                adapterc.notifyDataSetChanged();
            }
        });

        builder.setView(view);


//        final Button deletebtn = view.findViewById(R.id.delete_btn);
        final AlertDialog alertDialog = builder.show();

        final Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodsType.setText(
                        adaptera.getData().get(defaulta).getAreaname() + "|" +
                                adapterb.getData().get(defaultb).getAreaname() + "|" +
                                adapterc.getData().get(defaultc).getAreaname()

                );
                alertDialog.dismiss();

            }
        });

        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptera.getData().get(defaulta).setSelect(0);
                adaptera.getData().get(0).setSelect(1);
                adaptera.notifyDataSetChanged();

                adapterb.getData().get(defaultb).setSelect(0);
                adapterb.getData().get(0).setSelect(1);
                adapterb.notifyDataSetChanged();

                adapterc.getData().get(defaultc).setSelect(0);
                adapterc.getData().get(0).setSelect(1);
                adapterc.notifyDataSetChanged();

            }
        });
    }

    private void showMessage(String msg) {
        ToastUtils.showShortToast(getActivity(), msg);
    }

    private boolean emptyText(TextView textView, String msg) {
        if (textView.getText().toString().trim().isEmpty()) {
            showMessage(msg);
            return true;
        }
        return false;
    }

    private boolean emptycount(TextView textView, String msg) {
        if (Double.valueOf(textView.getText().toString()) <= 0) {
            showMessage(msg);
            return true;
        }
        return false;
    }

    private String getText(TextView textView) {
        return textView.getText().toString().trim();
    }

    private void publish_goods(
            String setoutprovince,
            String setoutcity,
            String destinationprovince,
            String destinationcity,
            String info,
            String phonenumber,
            String cweight,
            String ctype,
            String tnum,
            String ttype,
            String tlength
    ) {
        OkGo.<String>post(Constants.Save_Onlive)
                .headers(Name_header_token, Name_header)
                .params("setoutprovince", setoutprovince)
                .params("setoutcity", setoutcity)
                .params("destinationprovince", destinationprovince)
                .params("destinationcity", destinationcity)
                .params("info", info)
                .params("phonenumber", phonenumber)
                .params("cweight", cweight)
                .params("ctype", ctype)
                .params("tnum", tnum)
                .params("ttype", ttype)
                .params("tlength", tlength)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        LoginUserBean s = GsonUtil.parseJsonWithGson(response.body(), LoginUserBean.class);
                        if (s.getCode().equals("0")) {
                            ToastUtils.showShortToast(getActivity(), s.getMsg());
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putInt("typeselect", 1);
                            intent.putExtras(bundle);
                            intent.setClass(getActivity(), ShipperMainActivity.class);
                            startActivity(intent);
                        } else {
                            ToastUtils.showShortToast(getActivity(), s.getMsg());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        ToastUtils.showLongToast(getActivity(), response.body());
                    }
                });
    }

    private boolean decimalcount(TextView textView, String msg) {
        if (textView.getText().toString().trim().endsWith(".")) {
            showMessage(msg);
            return true;
        }
        return false;
    }
}
