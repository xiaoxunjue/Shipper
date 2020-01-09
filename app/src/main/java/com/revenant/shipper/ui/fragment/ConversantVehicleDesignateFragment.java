package com.revenant.shipper.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.DesignatePhoneItemAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.PersonData;
import com.revenant.shipper.ui.view.PinyinComparator;
import com.revenant.shipper.ui.view.SideBar;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversantVehicleDesignateFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.publish_search_phone)
    SearchView publishSearchPhone;
    @BindView(R.id.designate_recyclerview)
    RecyclerView designateRecyclerview;
    @BindView(R.id.tv_dialog)
    TextView tvDialog;
    @BindView(R.id.sideBar)
    SideBar sideBar;
    private DesignatePhoneItemAdapter designatePhoneItemAdapter;
    private List<PersonData> list;

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
                list.add(personData);
            }
        }

        //对联系人进行首字母排序
        Collections.sort(list, new PinyinComparator());
        designatePhoneItemAdapter = new DesignatePhoneItemAdapter(list);
        designatePhoneItemAdapter.addData(list);
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


    public static ConversantVehicleDesignateFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(title, title);
        ConversantVehicleDesignateFragment fragment = new ConversantVehicleDesignateFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
