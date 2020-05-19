package com.revenant.shipper.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
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
import com.revenant.shipper.bean.BaseBean.MessageEvent;
import com.revenant.shipper.bean.ShipperBean.HuoYuanDetailBean;
import com.revenant.shipper.bean.ShipperBean.LoginUserBean;
import com.revenant.shipper.ui.activity.ShipperMainActivity;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.EventBusUtil;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Utils;
import com.revenant.shipper.utils.apkUpdate.StringFormatUtil;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.hutool.core.date.DateUtil;

import static com.revenant.shipper.bean.BaseBean.MyEventCode.Published_Goods_SIGAL;
import static com.revenant.shipper.utils.Constants.Name_header_token;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditGoodsInfoFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.timestart)
    TextView timestart;
    @BindView(R.id.timeend)
    TextView timeend;
    @BindView(R.id.publish_btn_goods)
    Button publishBtnGoods;
    CityPickerView mPicker = new CityPickerView();
    VerticalTypeItemAdapter adaptera;
    VerticalTypeItemAdapter adapterb;
    VerticalTypeItemAdapter adapterc;
    @BindView(R.id.nice_spinner)
    NiceSpinner niceSpinner;
    @BindView(R.id.load_text)
    TextView loadText;
    @BindView(R.id.load_detail_text)
    EditText loadDetailText;
    @BindView(R.id.unload_text)
    TextView unloadText;
    @BindView(R.id.unload_detail_text)
    EditText unloadDetailText;
    @BindView(R.id.unload_personal_phone)
    EditText unloadPersonalPhone;
    @BindView(R.id.unload_personal_name)
    EditText unloadPersonalName;
    @BindView(R.id.goods_name)
    EditText goodsName;
    @BindView(R.id.goods_type)
    TextView goodsType;
    @BindView(R.id.goods_count)
    EditText goodsCount;
    @BindView(R.id.goods_price)
    EditText goodsPrice;
    @BindView(R.id.goods_weight)
    EditText goodsWeight;
    @BindView(R.id.fee_show)
    LinearLayout feeShow;
    @BindView(R.id.show_bills)
    LinearLayout showBills;
    @BindView(R.id.offline_jiesuan)
    SwitchCompat offlineJiesuan;
    @BindView(R.id.auto_confirm_driver)
    SwitchCompat autoConfirmDriver;
    @BindView(R.id.make_out_bills)
    SwitchCompat makeOutBills;
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
    private String loadingCode;
    private String unloadCode;
    private boolean isOnline = false; /*线下结算*/ /*线上*/
    private boolean isAuto = false; /*自动确认*/   /*得改自动*/
    private boolean isInvoice = false;/*不开具发票*/ /*0*/
    private String goodsdanwei = "1";
    private String Name_header;

    @Override

    protected int setLayoutId() {
        return R.layout.fragment_publishplatformfragment;
    }

    @Override
    protected void initView() {
        String goodseditid = getArguments().getString("goodsid");
        geteditgoods(String.valueOf(goodseditid));
        mPicker.init(getActivity());

        List<String> dataset = new LinkedList<>(Arrays.asList("吨", "方", "米", "件"));
        niceSpinner.attachDataSource(dataset);
        Name_header = Utils.get64String() + "_" + SPUtils.getUserPhone(getContext()) + "_" + "1";

//        offlineJiesuan=getActivity().findViewById(R.id.offline_jiesuan);
//        autoConfirmDriver=getActivity().findViewById(R.id.auto_confirm_driver);
//        makeOutBills=getActivity().findViewById(R.id.make_out_bills);

//        Bundle extras = getIntent().getExtras();
//        type = extras.getInt("shuchetype");
//        switch (type) {
//            case 3:
//                setcenterTitle("编辑货源");
//                goodseditid = extras.getInt("goodsid");
//                isPlatform = extras.getInt("isPlatform");
//                geteditgoods(String.valueOf(goodseditid));
//                publishBtnGoods.setText("编辑货源");
//                break;
//        }
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


        niceSpinner.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                goodsdanwei = String.valueOf(position + 1);

            }
        });

        offlineJiesuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isOnline = true;
                    showBills.setVisibility(View.GONE);
                    feeShow.setVisibility(View.GONE);
                } else {
                    isOnline = false;
                    showBills.setVisibility(View.VISIBLE);
                    feeShow.setVisibility(View.VISIBLE);
                }

            }
        });


        autoConfirmDriver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isAuto = true;
                } else {
                    isAuto = false;

                }

            }
        });


        makeOutBills.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isInvoice = true;
                } else {
                    isInvoice = false;
                }

            }
        });

//        offlineJiesuan.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
//            @Override
//            public void toggleToOn(SwitchView view) {
//                isOnline = true;
//                showBills.setVisibility(View.GONE);
//                feeShow.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void toggleToOff(SwitchView view) {
//                isOnline = false;
//                showBills.setVisibility(View.VISIBLE);
//                feeShow.setVisibility(View.VISIBLE);
//            }
//        });
//
//
//        autoConfirmDriver.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
//            @Override
//            public void toggleToOn(SwitchView view) {
//                isAuto = true;
//
//            }
//
//            @Override
//            public void toggleToOff(SwitchView view) {
//                isAuto = false;
//
//            }
//        });
//
//        makeOutBills.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
//            @Override
//            public void toggleToOn(SwitchView view) {
//                isInvoice = true;
//
//            }
//
//            @Override
//            public void toggleToOff(SwitchView view) {
//                isInvoice = false;
//            }
//        });

    }


    public static EditGoodsInfoFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(title, title);
        EditGoodsInfoFragment fragment = new EditGoodsInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


//    @OnClick({R.id.timestart, R.id.timeend, R.id.publish_btn_goods})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.timestart:
//                alertTypeSelect();
//                break;
//            case R.id.timeend:
//                break;
//            case R.id.publish_btn_goods:
//                break;
//            default:
//        }
//    }

    private void alerttime(TextView textView) {


        Calendar startDate = Calendar.getInstance();
        int year = startDate.get(Calendar.YEAR);
        int month = startDate.get(Calendar.MONTH);
        int day = startDate.get(Calendar.DAY_OF_MONTH);

        startDate.set(year, month, day);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2050, 12, 31);

        TimePickerView pvTime = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView.setText(getTime(date));
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setRangDate(startDate, endDate)
                .build();
        pvTime.show();

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
                        loadingCode = district.getId();
                        break;
                    case R.id.unload_text:
                        unloadCode = district.getId();
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
//        deletebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LogUtils.d("AAAAAAAA");
//                alertDialog.dismiss();
//            }
//        });
    }

    @OnClick({R.id.load_text, R.id.unload_text, R.id.goods_type, R.id.timestart, R.id.timeend, R.id.publish_btn_goods})
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
            case R.id.timestart:
                alerttime(timestart);
                break;
            case R.id.timeend:
                alerttime(timeend);
                break;
            case R.id.publish_btn_goods:
                if (emptyText(goodsName, "请输入货物名称")) {
                } else if (emptyText(goodsType, "请选择类型")) {
                } else if (emptyText(loadText, "请选择装车地址")) {
                } else if (emptyText(loadDetailText, "请填写装车详细地址")) {
                } else if (emptyText(unloadText, "请填写卸车地址")) {
                } else if (emptyText(unloadDetailText, "请填写卸车详细地址")) {
                } else if (emptyText(goodsWeight, "请填写重量")) {
                } else if (emptyText(goodsCount, "请填写数量")) {
                } else if (emptyText(timestart, "请填写起始时间")) {
                } else if (emptyText(timeend, "请填写结束时间")) {
                } else if (emptyText(unloadPersonalName, "请填写姓名")) {
                } else if (emptyText(unloadPersonalPhone, "请填写电话")) {
                } else if (emptyText(goodsPrice, "请填写价格")) {
                } else if (emptycount(goodsPrice, "价格不能小于0")) {
                } else if (emptycount(goodsWeight, "重量不能小于0")) {
                } else if (emptycount(goodsPrice, "数量不能小于0")) {
                } else if (decimalcount(goodsPrice, "价格填写完备")) {
                } else if (decimalcount(goodsWeight, "重量填写完备")) {
                } else if (!StringFormatUtil.phoneCheck(unloadPersonalPhone.getText().toString().trim())) {
                    ToastUtils.showShortToast(getActivity(), "手机号码格式不正确");
                } else {
                    publish_goods(
                            getText(goodsName),
                            getText(loadText),
                            loadingCode,
                            getText(unloadText),
                            unloadCode,
                            getText(goodsWeight),
                            "",
                            getText(goodsCount),
                            String.valueOf(SPUtils.getAccounId(getActivity())),
                            "0",
                            "0",
                            "1",
                            getCurrentTime(),
                            getText(unloadPersonalName),
                            getText(unloadPersonalPhone),
                            String.valueOf(!isAuto),
                            String.valueOf(isOnline),
                            getText(goodsPrice),
                            goodsdanwei,
                            getText(goodsType),
                            getText(loadDetailText),
                            getText(unloadDetailText),
                            String.valueOf(getText(timestart) + "-" + getText(timeend)),
                            String.valueOf(isInvoice ? 1 : 0),
                            "",
                            ""
                    );
                }
                break;
        }
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    private String getCurrentTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        String str = sdf.format(d);
        return str;
    }

    private void publish_goods(
            String name,
            String loading,
            String loadingCode,
            String unload,
            String unloadCode,
            String weight,
            String cube,
            String total,
            String accountId,
            String status,
            String isDel,
            String isPublish,
            String despatchDate,
            String recieveName,
            String recieveMobile,
            String isAuto,
            String isOnline,
            String price,
            String danWei,
            String vehicleLeader,
            String loadingDetail,
            String unloadingDetail,
            String loadDate,
            String isInvoice,
            String remark,
            String ids
    ) {
        OkGo.<String>post(Constants.Publish_Goods)
                .headers(Name_header_token, Name_header)
                .params("name", name)
                .params("loading", loading)
                .params("loadingCode", loadingCode)
                .params("unload", unload)
                .params("unloadCode", unloadCode)
                .params("weight", weight)
                .params("cube", cube)
                .params("total", total)
                .params("accountId", accountId)
                .params("status", status)
                .params("isDel", isDel)
                .params("isPublish", isPublish)
                .params("despatchDate", despatchDate)
                .params("recieveName", recieveName)
                .params("recieveMobile", recieveMobile)
                .params("isAuto", isAuto)
                .params("isOnline", isOnline)
                .params("price", price)
                .params("danWei", danWei)
                .params("vehicleLeader", vehicleLeader)
                .params("loadingDetail", loadingDetail)
                .params("unloadingDetail", unloadingDetail)
                .params("loadDate", loadDate)
                .params("isInvoice", isInvoice)
                .params("remark", remark)
                .params("isPlatform", "1")
                .params("mtime", DateUtil.currentSeconds())
//                .params("groupIds", "1")
//                .params("ids", "1")
//                .params("ids", ids)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        LoginUserBean s = GsonUtil.parseJsonWithGson(response.body(), LoginUserBean.class);
                        if (s.getCode().equals("0")) {
                            MessageEvent event = new MessageEvent(Published_Goods_SIGAL);
                            EventBusUtil.sendEvent(event);
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


    private boolean decimalcount(TextView textView, String msg) {
        if (textView.getText().toString().endsWith(".")) {
            showMessage(msg);
            return true;
        }
        return false;
    }

    private String getText(TextView textView) {
        return textView.getText().toString().trim();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
//        if ((view.getId() == R.id.goods_weight && canVerticalScroll(goodsWeight))) {
//            view.getParent().requestDisallowInterceptTouchEvent(true);
//            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                view.getParent().requestDisallowInterceptTouchEvent(false);
//            }
//        }
//        return false;
//    }
//
//    private boolean canVerticalScroll(EditText editText) {
//        //滚动的距离
//        int scrollY = editText.getScrollY();
//        //控件内容的总高度
//        int scrollRange = editText.getLayout().getHeight();
//        //控件实际显示的高度
//        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
//        //控件内容总高度与实际显示高度的差值
//        int scrollDifference = scrollRange - scrollExtent;
//
//        if (scrollDifference == 0) {
//            return false;
//        }
//
//        return (scrollY > 0) || (scrollY < scrollDifference - 1);
//    }

    private void geteditgoods(String id) {
        OkGo.<String>get(Constants.Look_Goods)
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.d("CCCC" + response.body());


                        HuoYuanDetailBean bean = GsonUtil.parseJsonWithGson(response.body(), HuoYuanDetailBean.class);
                      if (Integer.valueOf(bean.getCode())==0){
                          loadText.setText(bean.getData().getInfo().getLoading());
                          loadDetailText.setText(bean.getData().getInfo().getLoadingDetail());
                          loadingCode = bean.getData().getInfo().getLoadingCode();

                          unloadText.setText(bean.getData().getInfo().getUnload());
                          unloadDetailText.setText(bean.getData().getInfo().getUnloadingDetail());
                          unloadCode = bean.getData().getInfo().getUnloadCode();


                          unloadPersonalPhone.setText(bean.getData().getInfo().getRecieveMobile());

                          unloadPersonalName.setText(bean.getData().getInfo().getRecieveName());


                          goodsName.setText(bean.getData().getInfo().getName());

                          goodsType.setText(bean.getData().getInfo().getVehicleLeader());

                          goodsCount.setText(String.valueOf(bean.getData().getInfo().getTotal()));

                          goodsPrice.setText(String.valueOf(bean.getData().getInfo().getPrice()));

//                        goodsCount.setText(bean.getData().);

                          goodsWeight.setText(String.valueOf(bean.getData().getInfo().getWeight()));
//                        goodsdanwei = String.valueOf(bean.getData().getInfo().getDanWei());
                          niceSpinner.setSelectedIndex(bean.getData().getInfo().getDanWei() - 1);

                          LogUtils.d("卸货时间:" + bean.getData().getInfo().getLoadDate());
                          String time = bean.getData().getInfo().getLoadDate();
                          timestart.setText(time.substring(0, time.length() / 2));
                          timeend.setText(time.substring(time.length() / 2 + 1, time.length()));

                          isAuto = bean.getData().getInfo().isIsAuto();

                          isInvoice = bean.getData().getInfo().equals("0") ? false : true;

                          isOnline = bean.getData().getInfo().isIsOnline();

                          if (isOnline) {
                              offlineJiesuan.setChecked(true);
                              showBills.setVisibility(View.GONE);
                              feeShow.setVisibility(View.GONE);
                              autoConfirmDriver.setChecked(!isAuto);
                              makeOutBills.setChecked(isInvoice);
                          } else {
                              offlineJiesuan.setChecked(false);
                              autoConfirmDriver.setChecked(!isAuto);
                              makeOutBills.setChecked(isInvoice);
                          }

                      }else {
                          ToastUtils.showShortToast(getActivity(),bean.getMsg());
                      }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}

