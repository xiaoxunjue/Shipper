package com.revenant.shipper.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.DesignatePhoneItemAdapter;
import com.revenant.shipper.base.BaseActivity;
import com.revenant.shipper.bean.PersonData;
import com.revenant.shipper.bean.ShipperBean.MobileBean;
import com.revenant.shipper.bean.ShipperBean.ShuCheChaKanBean;
import com.revenant.shipper.ui.view.PinyinComparator;
import com.revenant.shipper.ui.view.SideBar;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.PinyinUtils;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineShuCheActivity extends BaseActivity {
    @BindView(R.id.publish_search_phone)
    EditText publishSearchPhone;
    @BindView(R.id.add_shuche_click)
    TextView addShucheClick;
    @BindView(R.id.designate_recyclerview)
    RecyclerView designateRecyclerview;
    @BindView(R.id.tv_dialog)
    TextView tvDialog;
    @BindView(R.id.sideBar)
    SideBar sideBar;
    @BindView(R.id.all_select)
    RadioButton allSelect;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.all_confirm)
    RelativeLayout allConfirm;
    private Context context;
    private DesignatePhoneItemAdapter designatePhoneItemAdapter;
    private List<PersonData> list;
    public final static int ShuCheRESULT_CODE = 102;
    private boolean isallselect = false;
    public static String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"
    };

    @Override
    public int getContentViewResId() {
        context = this;
        return
                R.layout.activity_mine_shuche;
    }

    @Override
    public void initView() {
        setcenterTitle("我的熟车");

        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = designatePhoneItemAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    designateRecyclerview.scrollToPosition(position);
                }
                Log.e("onTouchingLetterChanged", "  s=   " + s);
            }
        });

    }

    @Override
    public void leftbarclick() {
        super.leftbarclick();
        finish();
    }

    @Override
    public void initData() {
        list = new ArrayList<>();
        get_shuche_zhipai();
        //对联系人进行首字母排序
        Collections.sort(list, new PinyinComparator());
        designatePhoneItemAdapter = new DesignatePhoneItemAdapter(list);
        designateRecyclerview.setAdapter(designatePhoneItemAdapter);
        designateRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        designatePhoneItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                designatePhoneItemAdapter.getData().get(position).setIsselect(!designatePhoneItemAdapter.getData().get(position).isIsselect());
                designatePhoneItemAdapter.notifyItemChanged(position);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void get_shuche_zhipai() {
        OkGo.<String>get(Constants.Shuche_List)
                .params("accountId", SPUtils.getAccounId(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ShuCheChaKanBean groupBean = GsonUtil.parseJsonWithGson(response.body(), ShuCheChaKanBean.class);
                        if (groupBean.getCode().equals("0")) {
                            if (groupBean.getData() != null) {
                                allConfirm.setVisibility(View.VISIBLE);
                                if (list.size() > 0) {
                                    list.clear();
                                }
                                for (int i = 0; i < groupBean.getData().getAccountList().size(); i++) {
                                    PersonData personData = new PersonData();
                                    String letters = "";
                                    if (groupBean.getData().getAccountList().get(i).getUsername() == null) {
                                        letters = groupBean.getData().getAccountList().get(i).getMobile();
                                    } else {
                                        letters = PinyinUtils.getPinYin(groupBean.getData().getAccountList().get(i).getUsername().trim());
                                    }
                                    personData.setLetters(letters);
                                    personData.setName(groupBean.getData().getAccountList().get(i).getUsername() == null ? groupBean.getData().getAccountList().get(i).getMobile() : groupBean.getData().getAccountList().get(i).getUsername());
                                    personData.setId(String.valueOf(groupBean.getData().getAccountList().get(i).getId()));
                                    personData.setTel(groupBean.getData().getAccountList().get(i).getMobile());
                                    personData.setPhotourl(groupBean.getData().getAccountList().get(i).getPhoto());
                                    list.add(personData);

                                }
                                designatePhoneItemAdapter.setNewData(list);
                                allConfirm.setVisibility(View.VISIBLE);

                            } else {
                                allConfirm.setVisibility(View.GONE);
                            }
                        }

                    }
                });
    }

    @OnClick({R.id.add_shuche_click, R.id.all_select, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_shuche_click:
                if (publishSearchPhone.getText().toString().isEmpty()) {
                    ToastUtils.showShortToast(this, "请输入电话号码");
                } else {
                    search_phone(publishSearchPhone.getText().toString().trim());
                }

                break;
            case R.id.all_select:
                isallselect = !isallselect;
                allSelect.setChecked(isallselect);
                for (int i = 0; i < designatePhoneItemAdapter.getData().size(); i++) {
                    designatePhoneItemAdapter.getData().get(i).setIsselect(isallselect);
                }
                designatePhoneItemAdapter.notifyDataSetChanged();
                break;
            case R.id.confirm:
                ArrayList personDataList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isIsselect()) {
                        personDataList.add(list.get(i));
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("ShuCheList", personDataList);
                setResult(ShuCheRESULT_CODE, intent);
                finish();
                break;
        }
    }

    private void search_phone(String phone) {
        OkGo.<String>get(Constants.Search_Phone)
                .params("mobile", phone)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        MobileBean s = GsonUtil.parseJsonWithGson(response.body(), MobileBean.class);
                        if (s.getCode().equals("0")) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("driverid", s.getData());
                            bundle.putString("driverphone", phone);
                            Intent intent = new Intent();
                            intent.putExtras(bundle);
                            intent.setClass(context, AddShuCheActivity.class);
                            startActivity(intent);
                        } else {
                            ToastUtils.showShortToast(context, s.getMsg());
                        }
                    }
                });

    }

}
