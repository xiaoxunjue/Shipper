package com.revenant.shipper.ui.fragment;


import android.app.Fragment;
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
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.PersonData;
import com.revenant.shipper.bean.ShipperBean.MobileBean;
import com.revenant.shipper.bean.ShipperBean.ShuCheChaKanBean;
import com.revenant.shipper.ui.activity.AddShuCheActivity;
import com.revenant.shipper.ui.activity.ShuCheActivity;
import com.revenant.shipper.ui.view.PinyinComparator;
import com.revenant.shipper.ui.view.SideBar;
import com.revenant.shipper.utils.GsonUtil;
import com.revenant.shipper.utils.PinyinUtils;
import com.revenant.shipper.utils.SPUtils;
import com.revenant.shipper.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversantVehicleDesignateFragment extends BaseFragment {
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
    //    private List<PersonData> listPersonData = new ArrayList<>();
    private List<PersonData> list = new ArrayList<>();
    private boolean isallselect = false;

    public static String[] a = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"
    };

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_conversant_vehicle_designate;
    }

    @Override
    protected void initView() {
//        listPersonData=new ArrayList<>();
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


//        for (int i = 0; i < 1; i++) {
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


//        designatePhoneItemAdapter.setNewData(list);

//        Collections.sort(listPerson, new PinyinComparator());
//        designatePhoneItemAdapter = new DesignatePhoneItemAdapter(listPersonData);
//        designateRecyclerview.setAdapter(designatePhoneItemAdapter);
//        designateRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//        designatePhoneItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                designatePhoneItemAdapter.getData().get(position).setIsselect(!designatePhoneItemAdapter.getData().get(position).isIsselect());
//                designatePhoneItemAdapter.notifyItemChanged(position);
//            }
//        });

//        Test();

        get_shuche_zhipai();
//        designatePhoneItemAdapter.setNewData(list);
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


    public static ConversantVehicleDesignateFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(title, title);
        ConversantVehicleDesignateFragment fragment = new ConversantVehicleDesignateFragment();
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
                        LogUtils.d("数据是" + response.body());
                        if (groupBean.getCode().equals("0")) {
                            if (groupBean.getData() != null) {
                                allConfirm.setVisibility(View.VISIBLE);
                                List<PersonData> listPerson = new ArrayList<>();
                                for (int i = 0; i < groupBean.getData().getAccountList().size(); i++) {
                                    PersonData personData = new PersonData();
                                    String letters = "";
//                                    if (groupBean.getData().getAccountList().get(i).getUsername() != null) {
//                                        letters = PinyinUtils.getPinYin(groupBean.getData().getAccountList().get(i).getUsername().trim());
//
//                                    } else {
//
//                                    }


                                    if (groupBean.getData().getAccountList().get(i).getUsername()!=null){
                                        if (!groupBean.getData().getAccountList().get(i).getUsername().isEmpty()){
                                            letters = PinyinUtils.getPinYin(groupBean.getData().getAccountList().get(i).getUsername().trim());
                                        }else {
                                            letters = groupBean.getData().getAccountList().get(i).getMobile();
                                        }
                                    }else {
                                        letters = groupBean.getData().getAccountList().get(i).getMobile();
                                    }


                                    personData.setLetters(letters);
                                    String name = "";
                                    if (groupBean.getData().getAccountList().get(i).getUsername()!=null){
                                        if (!groupBean.getData().getAccountList().get(i).getUsername().isEmpty()){
                                            name=groupBean.getData().getAccountList().get(i).getUsername();
                                        }else {
                                            name=groupBean.getData().getAccountList().get(i).getMobile();
                                        }
                                    }else {
                                        name=groupBean.getData().getAccountList().get(i).getMobile();
                                    }

                                    personData.setName(name);
                                    personData.setId(String.valueOf(groupBean.getData().getAccountList().get(i).getId()));
                                    personData.setTel(groupBean.getData().getAccountList().get(i).getMobile());
                                    personData.setPhotourl(groupBean.getData().getAccountList().get(i).getPhoto());
                                    listPerson.add(personData);

                                }

//                                LogUtils.d("SSSSSSSSSSSS" + listPerson.size());
//                                for (int i = 0; i < listPerson.size(); i++) {
//                                    LogUtils.d("SSSSSSSSSSSS" + "位置" + i + listPerson.get(i).getName());
//                                }

                                designatePhoneItemAdapter = new DesignatePhoneItemAdapter(listPerson);
                                designateRecyclerview.setAdapter(designatePhoneItemAdapter);
                                designateRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
                                designatePhoneItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        designatePhoneItemAdapter.getData().get(position).setIsselect(!designatePhoneItemAdapter.getData().get(position).isIsselect());
                                        designatePhoneItemAdapter.notifyItemChanged(position);
                                    }
                                });



//                                Collections.sort(listPerson, new PinyinComparator());
                                                                designatePhoneItemAdapter.setNewData(listPerson);
//                                designatePhoneItemAdapter.setNewData(listPerson);
//                                for (int i = 0; i < listPerson.size(); i++) {
//                                    LogUtils.d("SSSSSSSSSSSS"+listPerson.get(i).getName());
//                                }
                                //对联系人进行首字母排序


                                ;
//                                designatePhoneItemAdapter.setNewData(Test());


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

    private void Test() {
        List<PersonData> personDatas = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            char letter = (char) (Math.random() * 26 + 'a');

            int randomJ = new Random().nextInt(10) + 1;
            for (int j = 0; j < randomJ; j++) {
                PersonData personData = new PersonData();
                personData.setLetters(letter + "");
                personData.setName(letter + "");

                int randomK = new Random().nextInt(5) + 3;
                for (int k = 0; k < randomK; k++) {
                    personData.setName(personData.getName() + (char) (Math.random() * 26 + 'a'));
                }
                personDatas.add(personData);
            }
        }
        designatePhoneItemAdapter.setNewData(personDatas);
    }

}
