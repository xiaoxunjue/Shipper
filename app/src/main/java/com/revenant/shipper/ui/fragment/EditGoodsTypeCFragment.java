package com.revenant.shipper.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.DesignatePhoneItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.PersonData;
import com.revenant.shipper.bean.ShipperBean.MobileBean;
import com.revenant.shipper.bean.ShipperBean.ShuCheChaKanBean;
import com.revenant.shipper.ui.activity.AddShuCheActivity;
import com.revenant.shipper.ui.activity.ShuCheActivity;
import com.revenant.shipper.ui.view.PinyinComparator;
import com.revenant.shipper.ui.view.SideBar;
import com.revenant.shipper.utils.Constants;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.PinyinUtils;
import com.revenant.shipper.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditGoodsTypeCFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.publish_search_phone)
    EditText publishSearchPhone;
    @BindView(R.id.designate_recyclerview)
    RecyclerView designateRecyclerview;
    @BindView(R.id.tv_dialog)
    TextView tvDialog;
    @BindView(R.id.sideBar)
    SideBar sideBar;
    @BindView(R.id.add_shuche_click)
    TextView addShucheClick;
    @BindView(R.id.all_select)
    RadioButton allSelect;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.all_confirm)
    RelativeLayout allConfirm;
    private DesignatePhoneItemAdapter designatePhoneItemAdapter;
    private List<PersonData> list;
    private boolean isallselect = false;

    public static String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"
    };

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_goodstypec;
    }

    @Override
    protected void initView() {

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
    public void initData() {
        list = new ArrayList<>();
        get_shuche_zhipai();

//        for (int i = 0; i < 25; i++) {
//            char letter = (char) (Math.random() * 26 + 'a');
//
//            int randomJ = new Random().nextInt(10) + 1;
//            for (int j = 0; j < randomJ; j++) {
//                PersonData personData = new PersonData();
//                personData.setLetters(letter + "");
//                personData.setName(letter + "");
//
//                int randomK = new Random().nextInt(5) + 3;
//                for (int k = 0; k < randomK; k++) {
//                    personData.setName(personData.getName() + (char) (Math.random() * 26 + 'a'));
//                }
//                list.add(personData);
//            }
//        }

        //对联系人进行首字母排序
        Collections.sort(list, new PinyinComparator());
        designatePhoneItemAdapter = new DesignatePhoneItemAdapter(list);
        designateRecyclerview.setAdapter(designatePhoneItemAdapter);
        designateRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        designatePhoneItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                designatePhoneItemAdapter.getData().get(position).setIsselect(!designatePhoneItemAdapter.getData().get(position).isIsselect());
                designatePhoneItemAdapter.notifyItemChanged(position);
            }
        });
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


    public static EditGoodsTypeCFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(title, title);
        EditGoodsTypeCFragment fragment = new EditGoodsTypeCFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        get_shuche_zhipai();
    }

    private void get_shuche_zhipai() {
        OkGo.<String>get(Constants.Shuche_List)

                .params("accountId", SPUtils.getAccounId(getActivity()))
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
                                    }else {
                                        letters=PinyinUtils.getPinYin(groupBean.getData().getAccountList().get(i).getUsername().trim());
                                    }
                                    personData.setLetters(letters);
                                    personData.setName(groupBean.getData().getAccountList().get(i).getUsername()==null?groupBean.getData().getAccountList().get(i).getMobile():groupBean.getData().getAccountList().get(i).getUsername());
                                    personData.setId(String.valueOf(groupBean.getData().getAccountList().get(i).getId()));
                                    personData.setTel(groupBean.getData().getAccountList().get(i).getMobile());
                                    personData.setPhotourl(groupBean.getData().getAccountList().get(i).getPhoto());
                                    list.add(personData);

                                }
                                designatePhoneItemAdapter.setNewData(list);


                            } else {
                                allConfirm.setVisibility(View.GONE);
                            }
                        }

                    }
                });
    }

    @OnClick({R.id.add_shuche_click, R.id.confirm, R.id.all_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_shuche_click:
                if (publishSearchPhone.getText().toString().isEmpty()) {
                    ToastUtils.showShortToast(getActivity(), "请输入电话号码");
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
                List<PersonData> data = designatePhoneItemAdapter.getData();
                String ids = "";
                for (int i = 0, j = 0; i < data.size(); i++) {
                    if (data.get(i).isIsselect()) {
                        if (j == 0) {
                            ids = String.valueOf(data.get(i).getId());
                            j++;
                        } else {
                            ids += "," + data.get(i).getId();
                        }
                    }
                }

                Intent intentshuche = new Intent();
                Bundle bundleshuche = new Bundle();
                bundleshuche.putInt("shuchetype", 0);
                bundleshuche.putString("shucheids", ids);
                intentshuche.putExtras(bundleshuche);
                intentshuche.setClass(getActivity(), ShuCheActivity.class);
                startActivity(intentshuche);
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
                            intent.setClass(getActivity(), AddShuCheActivity.class);
                            startActivity(intent);
                        } else {
                            ToastUtils.showShortToast(getActivity(), s.getMsg());
                        }
                    }
                });

    }





}
