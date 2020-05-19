package com.revenant.shipper.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.revenant.shipper.R;
import com.revenant.shipper.App;
import com.revenant.shipper.adapter.MineBigItemAdapter;
import com.revenant.shipper.bean.BigItemBean;
import com.revenant.shipper.ui.activity.NewSecondImproveDriverInformationActivity;
import com.revenant.shipper.ui.activity.OrderActivity;
import com.revenant.shipper.ui.activity.SettingActivity;
import com.revenant.shipper.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import static com.revenant.shipper.utils.Constants.Type_shipper_message;

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
        mine_bigitem_recyclerview.setLayoutManager(new GridLayoutManager(context, 4));
        mine_bigitem_recyclerview.setAdapter(mIneBigItemAdapter);

        mclassList = new ArrayList<>();
        mIneBigItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mclassList.get(position) != null) {
                    if (mclassList.get(position) == SettingActivity.class) {
                        Intent intent = new Intent(context, mclassList.get(position));
                        context.startActivity(intent);
                    } else {
                        App applicationContext = App.getApplication();
                        int userStatus = applicationContext.getUserStatus();
                        switch (userStatus) {
                            case 0:
                                Toast.makeText(context, "请去认证", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(context, NewSecondImproveDriverInformationActivity.class);
                                context.startActivity(intent);
                                break;
                            case 1:
                                if (mclassList.get(position) == OrderActivity.class) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("orderselect", position);
                                    Intent intent1 = new Intent(context, mclassList.get(position));
                                    intent1.putExtras(bundle);
                                    context.startActivity(intent1);
                                } else {
                                    Intent intent1 = new Intent(context, mclassList.get(position));
                                    context.startActivity(intent1);
                                }

                                break;
                            case 2:
                                if (mclassList.get(position) == OrderActivity.class) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("orderselect", position);
                                    Intent intent2 = new Intent(context, mclassList.get(position));
                                    intent2.putExtras(bundle);
                                    context.startActivity(intent2);
                                } else {
                                    Intent intent1 = new Intent(context, mclassList.get(position));
                                    context.startActivity(intent1);
                                }
                                break;
                            case 3:
                                if (mclassList.get(position) == OrderActivity.class) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("orderselect", position);
                                    Intent intent3 = new Intent(context, mclassList.get(position));
                                    intent3.putExtras(bundle);
                                    context.startActivity(intent3);
                                } else {
                                    Intent intent1 = new Intent(context, mclassList.get(position));
                                    context.startActivity(intent1);
                                }

                                break;

                            case 4:
                                if (mclassList.get(position) == OrderActivity.class) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("orderselect", position);
                                    Intent intent4 = new Intent(context, mclassList.get(position));
                                    intent4.putExtras(bundle);
                                    context.startActivity(intent4);
                                } else {
                                    Intent intent1 = new Intent(context, mclassList.get(position));
                                    context.startActivity(intent1);
                                }
                                break;
                            case 9:
                                ToastUtils.showShortToast(context, Type_shipper_message);
                                break;
                        }
//                        if (mclassList.get(position) == SettingActivity.class) {
//                            Intent intent = new Intent(context, mclassList.get(position));
//                            context.startActivity(intent);
//                        } else {
//                            Toast.makeText(context, "请去认证", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent();
//                            intent.setClass(context, NewSecondImproveDriverInformationActivity.class);
//                            context.startActivity(intent);
//                        }

                    }
                } else {
                    ToastUtils.showShortToast(context, "该功能暂未开放");
                }


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
