package com.revenant.shipper.ui.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.revenant.shipper.R;
import com.revenant.shipper.adapter.MineBigItemAdapter;
import com.revenant.shipper.bean.BigItemBean;
import com.revenant.shipper.ui.activity.InitActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: Shipper
 * @Package: com.revenant.shipper.ui.view
 * @ClassName: MineBigItem
 * @Description: java类作用描述
 * @Author: revenant
 * @CreateDate: 2019-12-30 13:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2019-12-30 13:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MineBigItem extends RelativeLayout {
    private TextView mine_bigitem_titleone;
    private TextView mine_bigitem_titletwo;
    private LinearLayout item_mine_right_click;
    private RecyclerView mine_bigitem_recyclerview;
    private MineBigItemAdapter mIneBigItemAdapter;
    private List<Class> mclassList;


    public MineBigItem(Context context) {
        super(context);
    }

    public MineBigItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.template_mine_bigitem, this);
        mine_bigitem_titleone = findViewById(R.id.mine_bigitem_titleone);
        mine_bigitem_titletwo = findViewById(R.id.mine_bigitem_titletwo);
        item_mine_right_click = findViewById(R.id.item_mine_right_click);
        mine_bigitem_recyclerview = findViewById(R.id.mine_bigitem_recyclerview);

        mIneBigItemAdapter = new MineBigItemAdapter();
        mine_bigitem_recyclerview.setLayoutManager(new GridLayoutManager(context,4));
        mine_bigitem_recyclerview.setAdapter(mIneBigItemAdapter);

        mclassList = new ArrayList<>();
        mIneBigItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(context, mclassList.get(position));
                context.startActivity(intent);
            }
        });

    }

    public void setActivity(List<Class> classList) {
        mclassList = classList;
    }

    public void item_mine_right_click(OnClickListener listener) {
        item_mine_right_click.setOnClickListener(listener);
    }

    public void setData(List<BigItemBean> beanList) {

        mIneBigItemAdapter.addData(beanList);
    }

    public void titletwo(String title) {
        mine_bigitem_titletwo.setText(title);
    }

    public void titleone(String title) {
        mine_bigitem_titleone.setText(title);
    }

}
