package com.revenant.shipper.ui.fragment;


import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkfuns.logutils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.AreaItemAdapter;
import com.revenant.shipper.adapter.AreaSelectItemAdapter;
import com.revenant.shipper.adapter.NewMainAdapter;
import com.revenant.shipper.base.BaseFragment;
import com.revenant.shipper.bean.AreaBeans;
import com.revenant.shipper.bean.AreaShowBean;
import com.revenant.shipper.utils.AreaSelect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlatformFragment extends BaseFragment {


    @BindView(R.id.recyclerviewareaselect)
    RecyclerView recyclerviewareaselect;
    @BindView(R.id.recyclerview_area_from_to)
    RecyclerView recyclerviewAreaFromTo;

    private NewMainAdapter newMainAdapter;

    private AreaItemAdapter areaItemAdapter;
    private int areatype = 0; // areatype=0,省，1市，2区
    private int areaposition = 0;
    private String areainfo;

    private AreaSelectItemAdapter areaSelectItemAdapter;

    public PlatformFragment() {
        // Required empty public constructor
    }

    public static PlatformFragment newInstance(String param1) {
        PlatformFragment fragment = new PlatformFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_platform;
    }

    @Override
    protected void initView() {
        /*
         * demo 地址选择期
         * */
        areaItemAdapter = new AreaItemAdapter();
        recyclerviewareaselect.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        List<AreaShowBean> showBeanList = new ArrayList<>();
        AreaBeans areaBean = AreaSelect.getArea(getActivity());
        for (int i = 0; i < areaBean.getAreas().getArea().size(); i++) {
            AreaShowBean showBean = new AreaShowBean();
            showBean.setAreaname(areaBean.getAreas().getArea().get(i).getName());
            showBeanList.add(showBean);
        }
        areaItemAdapter.addData(showBeanList);
        recyclerviewareaselect.setAdapter(areaItemAdapter);
        areaItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtils.d("AAAA" + adapter.getData().get(position).toString());
                areatype++;
                if (areatype == 1) {
                    areaposition = position;
                    AreaBeans.AreasBean.AreaBean areaBean1 = areaBean.getAreas().getArea().get(position);
                    showBeanList.clear();
                    for (int i = 0; i < areaBean1.getCity().size(); i++) {
                        AreaShowBean showBean = new AreaShowBean();
                        showBean.setAreaname(areaBean1.getCity().get(i).getName());
                        showBeanList.add(showBean);
                    }
                    areaItemAdapter.setNewData(showBeanList);
                } else if (areatype == 2) {
                    List<String> cityBeans = areaBean.getAreas().getArea().get(areaposition).getCity().get(position).getArea();
                    showBeanList.clear();
                    for (int i = 0; i < cityBeans.size(); i++) {
                        AreaShowBean showBean = new AreaShowBean();
                        showBean.setAreaname(cityBeans.get(i));
                        showBeanList.add(showBean);
                    }
                    areaItemAdapter.setNewData(showBeanList);
                }

            }
        });

        /*
         * 顶部选择器
         * */
        areaSelectItemAdapter = new AreaSelectItemAdapter();
        recyclerviewAreaFromTo.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        List<AreaShowBean> showBeanTopList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            AreaShowBean bean = new AreaShowBean();
            bean.setAreaname("选择");
            showBeanTopList.add(bean);
        }

        areaSelectItemAdapter.addData(showBeanTopList);
        recyclerviewAreaFromTo.setAdapter(areaSelectItemAdapter);
        areaSelectItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }

    @Override
    public void initData() {

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_platform, container, false);
//    }

}
