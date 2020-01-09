package com.revenant.shipper.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.SuggestionItemAdapter;
import com.revenant.shipper.bean.SuggestionTypeTextBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestionWriteFragment extends Fragment {


    private SuggestionItemAdapter suggestionItemAdapter;

    private List<String> suggestions = Arrays.asList("功能异常", "体验建议", "新功能建议", "诚信分相关", "评价相关", "其他");
    private List<SuggestionTypeTextBean> beanList = new ArrayList<>();

    public SuggestionWriteFragment() {
        // Required empty public constructor
    }

    public static SuggestionWriteFragment newInstance(String param1) {
        SuggestionWriteFragment fragment = new SuggestionWriteFragment();
        Bundle args = new Bundle();
        args.putString(param1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_suggestion_write, container, false);

        RecyclerView recyclerviewSuggType = root.findViewById(R.id.recyclerview_sugg_type);
        suggestionItemAdapter = new SuggestionItemAdapter();
        for (int i = 0; i < suggestions.size(); i++) {
            SuggestionTypeTextBean bean = new SuggestionTypeTextBean();
            bean.setContent(suggestions.get(i));
            bean.setIsselected(false);
            beanList.add(bean);
        }
        suggestionItemAdapter.setNewData(beanList);
        recyclerviewSuggType.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        suggestionItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SuggestionTypeTextBean bean = (SuggestionTypeTextBean) adapter.getData().get(position);
                ((SuggestionTypeTextBean) adapter.getData().get(position)).setIsselected(!bean.isIsselected());
                adapter.refreshNotifyItemChanged(position);

            }
        });
        recyclerviewSuggType.setAdapter(suggestionItemAdapter);
        // Inflate the layout for this fragment
        return root;
    }

}
